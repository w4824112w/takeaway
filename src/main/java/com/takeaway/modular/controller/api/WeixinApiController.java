package com.takeaway.modular.controller.api;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.HttpUtil;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.enums.PaymentType;
import com.takeaway.core.netpay.wxpay.api.PayUtils;
import com.takeaway.core.netpay.wxpay.bean.PayPackage;
import com.takeaway.core.netpay.wxpay.bean.PrePayInfo;
import com.takeaway.core.netpay.wxpay.utils.Configure;
import com.takeaway.core.netpay.wxpay.utils.MapUtil;
import com.takeaway.core.netpay.wxpay.utils.Signature;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.WxPayNotifys;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.PayRequestsService;
import com.takeaway.modular.service.WxPayNotifysService;

@RestController
@RequestMapping("/api/weixin")
@Api(value="微信支付接口---小程序接口",description="WeixinApiController")
public class WeixinApiController {
	private static final Logger log = Logger
			.getLogger(WeixinApiController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private PayRequestsService payRequestsService;
	
	@Autowired
	private WxPayNotifysService wxPayNotifysService;
	
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public void callback(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		log.info("测试回信支付回调成功");
		
		String result = readIncommingRequestData(request);
		log.info("微信支付成功返回参数:"+result);
		Map<String, Object> map = MapUtil.getMapFromXML(result);
		/**
		 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
		 * @param responseString API返回的XML数据字符串
		 * @return API签名是否合法
		 */
		boolean is_valid = Signature.checkIsSignValidFromResponseString(map);
		log.info("签名验证返回:"+is_valid);
		if(is_valid){
			log.info("微信支付成功返回result_code参数:"+map.get("result_code"));
			if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				String orderNo=map.get("out_trade_no").toString();
				Orders order=ordersService.getByOrderNo(orderNo);
				if(order.getIsPay()==1){	// 防止订单修改支付成功状态后服务挂掉出现微信重复回调情况
					response.getWriter().write(PayUtils.generatePaySuccessReplyXML());
				}
				
				Integer isPay=1;
				Integer isNotify=1;
				String successTime=map.get("time_end").toString();
				String total_fee=map.get("total_fee").toString();
				String notifyTime=DateUtil.parseDate(new Date(), "yyyy-MM-dd HH:mm:sss");
				payRequestsService.update(orderNo, isPay, isNotify, successTime, notifyTime);//更改支付状态和支付成功时间
				
				WxPayNotifys wxPayNotifys=WxPayNotifys.setWxPayNotify(map);
				wxPayNotifys.setIsVerify(1);
				wxPayNotifys.setVerifyDate(new Date());
				wxPayNotifysService.save(wxPayNotifys);
				
				order.setIsPay(1);	// 0：未支付;1：已支付;
				order.setPayDate(new Date());
				ordersService.update(order);
				response.getWriter().write(PayUtils.generatePaySuccessReplyXML());
			}else{
				response.getWriter().write(PayUtils.generateReplyXML("FAIL", "支付结果失败"));
			}
		}else{
			response.getWriter().write(PayUtils.generateReplyXML("FAIL", "数据签名异常"));
		}
		
	}
	
	@ApiOperation(value = "支付", httpMethod = "POST", notes = "微信支付")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "form"),
		//	@ApiImplicitParam(name = "payTypeId", value = "支付类型id", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "itemName", value = "商品名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "amount", value = "订单总金额(单位：元)", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "num", value = "数量", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "form")
	})
	@RequestMapping(value = "/wxpay", method = RequestMethod.POST)
	public JSONObject callbackForWxpay(HttpServletRequest request,
			HttpServletResponse response,String orderNo,String openid,String itemName,String amount,String num,String itemId) {

		amount=new BigDecimal(amount).multiply(new BigDecimal(100)).intValue()+"";	//元转分
		payRequestsService.save(null, PaymentType.weixin.getName(), orderNo, itemName, amount, Configure.getNotifyCallbackUrl(), null);
		
		PayPackage payPackage = new PayPackage();
		payPackage.setAppid(Configure.getAppid());
		payPackage.setMch_id(Configure.getMchid());
		payPackage.setNotify_url(Configure.getNotifyCallbackUrl());
		payPackage.setBody(itemName);
		payPackage.setOut_trade_no(orderNo);
		payPackage.setTotal_fee(amount);
		String ip=HttpUtil.getIpAddr(request);
		payPackage.setSpbill_create_ip(ip);
		payPackage.setTrade_type("JSAPI");
		payPackage.setOpenid(openid);
		payPackage.setNonce_str(PayUtils.random_str());
		String back = PayUtils.generatePayNativeReplyXML(payPackage);
		log.info("统一下单后返回:"+back);
		XmlMapper xmlMapper = new XmlMapper();
		try {
			PrePayInfo prePayInfo = xmlMapper.readValue(back, PrePayInfo.class);
			log.info("转换成实体:"+JSONObject.toJSONString(prePayInfo));
			if(prePayInfo.getReturn_code().equals("SUCCESS")){//通信标识，非交易标识
				if(prePayInfo.getResult_code().equals("SUCCESS")){//业务结果
					Map<String, String> map = PayUtils.generateAppPay(prePayInfo);
					log.info("成功:"+map.toString());
					return ErrorEnums.getResult(ErrorEnums.SUCCESS, "微信支付", map);
				}else{
					log.info("失败:"+prePayInfo.getErr_code_des());
					return ErrorEnums.getResult(ErrorEnums.ERROR, "微信支付", prePayInfo.getErr_code_des());
				}
			}else{
				log.info("失败:"+prePayInfo.getReturn_msg());
				return ErrorEnums.getResult(ErrorEnums.ERROR, "微信支付", prePayInfo.getReturn_msg());
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
