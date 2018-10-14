package com.takeaway.modular.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.HttpUtil;
import com.takeaway.commons.utils.OrderUtils;
import com.takeaway.commons.utils.PreciseCompute;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.enums.PaymentType;
import com.takeaway.core.netpay.wxpay.api.PayUtils;
import com.takeaway.core.netpay.wxpay.bean.PayPackage;
import com.takeaway.core.netpay.wxpay.bean.PrePayInfo;
import com.takeaway.core.netpay.wxpay.utils.Configure;
import com.takeaway.core.netpay.wxpay.utils.MapUtil;
import com.takeaway.core.netpay.wxpay.utils.Signature;
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.dao.model.WxPayNotifys;
import com.takeaway.modular.service.DistributionsService;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.PayRequestsService;
import com.takeaway.modular.service.UsersService;
import com.takeaway.modular.service.WxPayNotifysService;

@RestController
@RequestMapping("/api/weixin")
@Api(value = "微信支付接口---小程序接口", description = "WeixinApiController")
public class WeixinApiController {
	private static final Logger log = Logger
			.getLogger(WeixinApiController.class);

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private OrderItemsMapper orderItemsMapper;

	@Autowired
	private MerchantsMapper merchantsMapper;

	@Autowired
	private PayRequestsService payRequestsService;

	@Autowired
	private WxPayNotifysService wxPayNotifysService;

	@Autowired
	private DistributionsService distributionsService;

	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public void callback(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		log.info("测试回信支付回调成功");

		String result = readIncommingRequestData(request);
		log.info("微信支付成功返回参数:" + result);
		Map<String, Object> map = MapUtil.getMapFromXML(result);
		/**
		 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
		 * 
		 * @param responseString
		 *            API返回的XML数据字符串
		 * @return API签名是否合法
		 */
		boolean is_valid = Signature.checkIsSignValidFromResponseString(map);
		log.info("签名验证返回:" + is_valid);
		if (is_valid) {
			log.info("微信支付成功返回result_code参数:" + map.get("result_code"));
			if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				String orderNo = map.get("out_trade_no").toString();
				Orders order = ordersService.getByOrderNo(orderNo);
				if (order.getIsPay() == 1) { // 防止订单修改支付成功状态后服务挂掉出现微信重复回调情况
					response.getWriter().write(
							PayUtils.generatePaySuccessReplyXML());
				}

				Integer isPay = 1;
				Integer isNotify = 1;
				String successTime = map.get("time_end").toString();
				String total_fee = map.get("total_fee").toString();
				String notifyTime = DateUtil.parseDate(new Date(),
						"yyyy-MM-dd HH:mm:sss");
				payRequestsService.update(orderNo, isPay, isNotify,
						successTime, notifyTime);// 更改支付状态和支付成功时间

				WxPayNotifys wxPayNotifys = WxPayNotifys.setWxPayNotify(map);
				wxPayNotifys.setIsVerify(1);
				wxPayNotifys.setVerifyDate(new Date());
				wxPayNotifysService.save(wxPayNotifys);

				order.setIsPay(1); // 0：未支付;1：已支付;
				order.setPayDate(new Date());
				order.setStatus(2); // 1 待支付。2 待发货。 3 待收货 4 待评价 5 已完成 6退款/售后

				// 调用闪送接口开始配送
				Merchants merchants = merchantsMapper.getById(order
						.getMerchantId().toString());
				if (merchants.getDistributionType() == 1) {
					if (StringUtils.isNotBlank(order.getUserAddress())) { // 有收获地址，调用闪送配送
						Map<String, Object> distribution = distributionsService
								.saveOrder(order, merchants);
						if (distribution.get("status").toString().equals("OK")) {
							order.setIssorderno(distribution.get("data")
									.toString());
						} else {
							order.setIssorderno(null);
						}
					}
				}

				ordersService.update(order);

				JSONObject json = new JSONObject();
				json.put("code", 200);
				json.put("type", 1);
				json.put("order", order);

				log.info("开始发送websocket消息........" + json.toJSONString());
				WebSocketServer.sendInfo(order.getMerchantId().toString(),
						json.toJSONString());
				response.getWriter().write(
						PayUtils.generatePaySuccessReplyXML());
			} else {
				response.getWriter().write(
						PayUtils.generateReplyXML("FAIL", "支付结果失败"));
			}
		} else {
			response.getWriter().write(
					PayUtils.generateReplyXML("FAIL", "数据签名异常"));
		}

	}

	@ApiOperation(value = "支付", httpMethod = "POST", notes = "微信支付")
	@RequestMapping(value = "/wxpay", method = RequestMethod.POST)
	public JSONObject callbackForWxpay(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Orders orders) {
		Orders old_orders = ordersService.getByOrderNo(orders.getOrderNo());

		if (StringUtils.isNotBlank(orders.getReservationTime())) {
			String reservationTime = orders.getReservationTime();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				orders.setReservationDate(format.parse(reservationTime));
			} catch (ParseException e1) {
				log.info("格式化预定时间错误");
			}
		}

		Double realPayMoney = 0.00;
		if (old_orders == null) {
			log.info("开始计算价格....orders:" + orders.toString());
			Map money = ordersService.computePrice(orders);
			log.info("开始计算价格结束...." + money.toString());

			log.info("开始判断价格是否正确.....");

			realPayMoney = PreciseCompute.add(realPayMoney,
					Double.parseDouble(money.get("realTotalMoney").toString()));
			if (StringUtils.isNotBlank(orders.getUserAddress())) {
				realPayMoney = PreciseCompute.add(realPayMoney, Double
						.parseDouble(money.get("distributionFee").toString()));
				orders.setDeliverMoney(Double.parseDouble(money.get(
						"distributionFee").toString()));
			} else {
				orders.setDeliverMoney(0.0);
			}
			realPayMoney = PreciseCompute.add(realPayMoney,
					Double.parseDouble(money.get("packingCharge").toString()));
			Double realTotalMoney = orders.getRealTotalMoney();
			log.info("realTotalMoney---" + realTotalMoney
					+ "---realPayMoney----" + realPayMoney);
			/*
			 * if((PreciseCompute.sub(realTotalMoney, realPayMoney)>0)){ return
			 * ErrorEnums.getResult(ErrorEnums.ERROR,
			 * "收货地址--"+orders.getUserAddress
			 * ()+"--前端传的实际付款价格:"+realTotalMoney+"--后端计算后得出的价格: "
			 * +realPayMoney+"--价格不对，下单", money.toString()); }
			 */
			if (realTotalMoney.compareTo(realPayMoney) != 0) {
				return ErrorEnums.getResult(ErrorEnums.ERROR,
						"收货地址--" + orders.getUserAddress() + "--前端传的实际付款价格:"
								+ realTotalMoney + "--后端计算后得出的价格: "
								+ realPayMoney + "--价格不对，下单", money.toString());
			}
		} else {
			realPayMoney = old_orders.getRealTotalMoney();
		}

		String openid = orders.getOpenid();
		Users users = usersService.getByOpenid(openid);

		String orderNo;
		if (old_orders != null) {
			orderNo = orders.getOrderNo();

			if (old_orders.getIsPay() == 1) {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "该订单已经付款", null);
			}
		} else {
			// orderNo = OrderUtils.generateTradeNo(); // 订单编号
			// orders.setOrderNo(orderNo);
			orders.setUserId(users.getId());

			if (realPayMoney <= 0) {
				orders.setIsPay(1); // 0：未支付;1：已支付;
				orders.setPayDate(new Date());
				orders.setStatus(2); // 1 待支付。2 待发货。 3 待收货 4 待评价 5 已完成 6退款/售后
				ordersService.save(orders);
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "微信支付", null);

			} else {
				ordersService.save(orders);
			}

			orderNo = orders.getOrderNo();
		}

		String itemName = "紫竹林外卖" + orderNo;
		String amount = (int) (realPayMoney * 100) + ""; // 元转分
		payRequestsService.save(null, PaymentType.weixin.getName(), orderNo,
				itemName, amount, Configure.getNotifyCallbackUrl(), null);

		PayPackage payPackage = new PayPackage();
		payPackage.setAppid(Configure.getAppid());
		payPackage.setMch_id(Configure.getMchid());
		payPackage.setNotify_url(Configure.getNotifyCallbackUrl());
		payPackage.setBody(itemName);
		payPackage.setOut_trade_no(orderNo);
		payPackage.setTotal_fee(amount);
		String ip = HttpUtil.getIpAddr(request);
		payPackage.setSpbill_create_ip(ip);
		payPackage.setTrade_type("JSAPI");
		payPackage.setOpenid(openid);
		payPackage.setNonce_str(PayUtils.random_str());
		String back = PayUtils.generatePayNativeReplyXML(payPackage);
		log.info("统一下单后返回:" + back);
		XmlMapper xmlMapper = new XmlMapper();
		try {
			PrePayInfo prePayInfo = xmlMapper.readValue(back, PrePayInfo.class);
			log.info("转换成实体:" + JSONObject.toJSONString(prePayInfo));
			if (prePayInfo.getReturn_code().equals("SUCCESS")) {// 通信标识，非交易标识
				if (prePayInfo.getResult_code().equals("SUCCESS")) {// 业务结果
					Map<String, String> map = PayUtils
							.generateAppPay(prePayInfo);
					log.info("成功:" + map.toString());
					return ErrorEnums
							.getResult(ErrorEnums.SUCCESS, "微信支付", map);
				} else {
					log.info("失败:" + prePayInfo.getErr_code_des());
					return ErrorEnums.getResult(ErrorEnums.ERROR, "微信支付",
							prePayInfo.getErr_code_des());
				}
			} else {
				log.info("失败:" + prePayInfo.getReturn_msg());
				return ErrorEnums.getResult(ErrorEnums.ERROR, "微信支付",
						prePayInfo.getReturn_msg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("支付异常");
			return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, "微信支付", null);
		}

	}

	public static String readIncommingRequestData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line = null; (line = br.readLine()) != null;) {
				result.append(line).append("\n");
			}

			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
