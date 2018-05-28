package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.HttpUtil;
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
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.dao.model.WxPayNotifys;
import com.takeaway.modular.service.OrderCanclesService;
import com.takeaway.modular.service.OrderHistorysService;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.PayRequestsService;
import com.takeaway.modular.service.UsersService;
import com.takeaway.modular.service.WxPayNotifysService;

@RestController
@RequestMapping("/weixin")
@Api(value="微信申请退款管理",description="RefundController")
public class RefundController {
	private static final Logger log = Logger
			.getLogger(RefundController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderCanclesService orderCanclesService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PayRequestsService payRequestsService;
	
	@Autowired
	private WxPayNotifysService wxPayNotifysService;
	
	@Autowired
	private OrderHistorysService orderHistorysService;
	
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public void callback(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		log.info("测试微信申请退款回调成功");
		
		String result = readIncommingRequestData(request);
		log.info("微信申请退款成功返回参数:"+result);
		Map<String, Object> map = MapUtil.getMapFromXML(result);
		/**
		 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
		 * @param responseString API返回的XML数据字符串
		 * @return API签名是否合法
		 */
		boolean is_valid = Signature.checkIsSignValidFromResponseString(map);
		log.info("签名验证返回:"+is_valid);
		if(is_valid){
			log.info("微信申请退款成功返回result_code参数:"+map.get("result_code"));
			if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				String orderNo=map.get("out_trade_no").toString();
				String refundNo=map.get("out_refund_no").toString();
				Orders order=ordersService.getByOrderNo(orderNo);
				if(order.getIsRefund()==1){	// 防止订单修改退款成功状态后服务挂掉出现微信重复回调情况
					response.getWriter().write(PayUtils.generatePaySuccessReplyXML());
				}
				
				Integer isRefund=1;
				Integer isNotify=1;
				String successTime=map.get("time_end").toString();
				String total_fee=map.get("total_fee").toString();	// 订单金额
				String refundFee=map.get("refund_fee").toString();	// 申请退款金额 
				String settlementRefundFee=map.get("settlement_refund_fee ").toString();	// 实际退款金额 
				String notifyTime=DateUtil.parseDate(new Date(), "yyyy-MM-dd HH:mm:sss");
				payRequestsService.update(refundNo, isRefund, isNotify, successTime, notifyTime);//更改退款状态和退款成功时间
				
				WxPayNotifys wxPayNotifys=WxPayNotifys.setWxPayNotify(map);
				wxPayNotifys.setIsVerify(1);
				wxPayNotifys.setVerifyDate(new Date());
				wxPayNotifysService.save(wxPayNotifys);
				
				order.setStatus(6);	// 1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后
				order.setIsRefund(1);	// 0：未退款;1：已退款;
				order.setRefundDate(new Date());
				order.setRefundMoney(Double.parseDouble(refundFee));
				ordersService.update(order);
				
				JSONObject json = new JSONObject();
				json.put("code", 200);
				json.put("type", 1);
				json.put("order", order);
				log.info("开始发送websocket消息........"+json.toJSONString());
				WebSocketServer.sendInfo(order.getMerchantId().toString(),json.toJSONString());
				response.getWriter().write(PayUtils.generatePaySuccessReplyXML());
			}else{
				response.getWriter().write(PayUtils.generateReplyXML("FAIL", "退款结果失败"));
			}
		}else{
			response.getWriter().write(PayUtils.generateReplyXML("FAIL", "数据签名异常"));
		}
		
	}
	
	/**
	 * @param request
	 * @param response
	 * @param orderCancles
	 * @return
	 */
	@ApiOperation(value = "退款", httpMethod = "POST", notes = "微信申请退款")
/*	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "refundNo", value = "退款单号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "totalFee", value = "订单总金额(单位：元)", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "refundFee", value = "退款总金额(单位：元)", required = true, dataType = "String", paramType = "form"),
	})*/
	@RequestMapping(value = "/wxrefund", method = RequestMethod.POST)
	public JSONObject callbackForWxpay(HttpServletRequest request,
			HttpServletResponse response,@RequestBody OrderCancles orderCancles) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		
		Orders orders=ordersService.getById(orderCancles.getOrderId().toString());
		
		if(orders.getIsRefund()==1){
			return ErrorEnums.getResult(ErrorEnums.ERROR, "该订单已经退款", null);
		}
		
		Users users=usersService.getById(orders.getUserId().toString());
		String openid=users.getOpenId();
		
		if(orders==null){
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "订单不存在",
					null);
		}
		
/*				String refundNo = RandomSequence.getSixteenRandomVal(); // 退单编号
		OrderCancles orderCancles=new OrderCancles();
		orderCancles.setOrderId(orders.getId());
		orderCancles.setRefundNo(refundNo);
		orderCancles.setSize(Integer.parseInt(num));
		orderCancles.setName(users.getUserName());
		orderCancles.setTotalPrice(Double.parseDouble(refundFee));
		orderCancles.setCreatedAt(new Date());
		orderCancles.setOperAt(new Date());
		orderCancles.setOperMan(u.getId());
		orderCanclesService.save(orderCancles);	// 保存申请退款单
*/		
	//	OrderCancles orderCancles=orderCanclesService.getByRefundNo(refundNo);
		
/*		totalFee=new BigDecimal(totalFee).multiply(new BigDecimal(100)).intValue()+"";	//订单总金额元转分
		refundFee=new BigDecimal(refundFee).multiply(new BigDecimal(100)).intValue()+"";	//退款总金额元转分
*/
		String orderNo=orders.getOrderNo();
		String refundNo=orderCancles.getRefundNo();
		String totalFee=(int)(orders.getRealTotalMoney()*100)+"";	//元转分
		String refundFee=(int)(orderCancles.getTotalPrice()*100)+"";	//元转分
		payRequestsService.save(null, PaymentType.refund.getName(), refundNo, "退款", refundFee, Configure.getRefundNotifyCallbackUrl(), null);
		
		PayPackage payPackage = new PayPackage();
		payPackage.setAppid(Configure.getAppid());
		payPackage.setMch_id(Configure.getMchid());
		payPackage.setNonce_str(PayUtils.random_str());
		payPackage.setOut_trade_no(orderNo);
		payPackage.setOut_refund_no(refundNo);
		payPackage.setTotal_fee(totalFee);
		payPackage.setRefund_fee(refundFee);
		String back = PayUtils.generateRefundNativeReplyXML(payPackage);
		log.info("申请退款后返回:"+back);
		XmlMapper xmlMapper = new XmlMapper();
		try {
			PrePayInfo prePayInfo = xmlMapper.readValue(back, PrePayInfo.class);
			log.info("转换成实体:"+JSONObject.toJSONString(prePayInfo));
			if(prePayInfo.getReturn_code().equals("SUCCESS")){//通信标识，非交易标识
				if(prePayInfo.getResult_code().equals("SUCCESS")){//业务结果
					log.info("成功:"+JSONObject.toJSONString(prePayInfo));
					orders.setRefundSrcStatus(orders.getStatus());	// 退款前状态
					orders.setStatus(7);	//  1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后 7 已退款
					orders.setIsRefund(1);	//	0：未退款;1：已退款;
					orders.setRefundDate(new Date());
					orders.setRefundMoney(orderCancles.getTotalPrice());
					ordersService.update(orders);
					
			    	OrderHistorys orderHistorys=new OrderHistorys();
			    	BeanUtils.copyProperties(orders, orderHistorys);
			    	orderHistorysService.save(orderHistorys);
					
					JSONObject result = new JSONObject();
					result.put("data", prePayInfo);
					return ErrorEnums.getResult(ErrorEnums.SUCCESS, "微信申请退款", result);
				}else{
					log.info("失败:"+prePayInfo.getErr_code_des());
					return ErrorEnums.getResult(ErrorEnums.ERROR,prePayInfo.getErr_code_des()+",微信申请退款",null );
				}
			}else{
				log.info("失败:"+prePayInfo.getReturn_msg());
				return ErrorEnums.getResult(ErrorEnums.ERROR, prePayInfo.getReturn_msg()+",微信申请退款",null );
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("微信申请退款异常");
			return ErrorEnums.getResult(ErrorEnums.SERVER_ERROR, "微信申请退款", null);
		} 

	}
	
	public static String readIncommingRequestData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line=null; (line=br.readLine())!=null;) {
				result.append(line).append("\n");
			}
			
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
}
