package com.takeaway.core.netpay.wxpay.api;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.takeaway.core.netpay.wxpay.bean.PayCallbackNotify;
import com.takeaway.core.netpay.wxpay.bean.PayNativeInput;
import com.takeaway.core.netpay.wxpay.bean.PayPackage;
import com.takeaway.core.netpay.wxpay.bean.PayQrCode;
import com.takeaway.core.netpay.wxpay.bean.PrePayInfo;
import com.takeaway.core.netpay.wxpay.utils.Configure;
import com.takeaway.core.netpay.wxpay.utils.HttpsRequest;
import com.takeaway.core.netpay.wxpay.utils.MapUtil;
import com.takeaway.core.netpay.wxpay.utils.Signature;

public class PayUtils {
	private static final Logger logger = Logger.getLogger(PayUtils.class);

	public static String generateMchPayNativeRequestURL(String productid) {
		PayQrCode qrCode = new PayQrCode(productid);
		Map<String, String> map = new HashMap<String, String>();
		map.put("sign", qrCode.getSign());
		map.put("appid", qrCode.getAppid());
		map.put("mch_id", qrCode.getMch_id());
		map.put("product_id", qrCode.getProduct_id());
		map.put("time_stamp", qrCode.getTime_stamp());
		map.put("nonce_str", qrCode.getNonce_str());

		return "weixin://wxpay/bizpayurl?" + MapUtil.mapJoin(map, false, false);
	}

	/**
	 * 
	 * @param inputStream
	 *            request.getInputStream()
	 * @return
	 */
	public static PayNativeInput convertRequest(InputStream inputStream) {
		try {
			String content = IOUtils.toString(inputStream);

			XmlMapper xmlMapper = new XmlMapper();
			PayNativeInput payNativeInput = xmlMapper.readValue(content,
					PayNativeInput.class);

			return payNativeInput;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean validateAppSignature(PayNativeInput payNativeInput) {
		try {
			Map<String, String> map = BeanUtils.describe(payNativeInput);
			map.remove("class");
			map.put("sign", "");

			String sign = Signature.generateSign(map);
			return payNativeInput.getSign().equals(sign) ? true : false;
		} catch (Exception e) {
		}

		return false;
	}

	public static String generatePayNativeReplyXML(PayPackage payPackage) {
		try {

			Map<String, String> map = BeanUtils.describe(payPackage);
			map.remove("class");
			// 尝试微信支付提供的方法
			String dto2sign = Signature.getSign4Object(payPackage);
			payPackage.setSign(dto2sign);

			XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.setSerializationInclusion(Include.NON_EMPTY);

			String xmlContent = xmlMapper.writeValueAsString(payPackage);
			
		//	System.out.println("xml------"+xmlContent);

		//	String xmlContent = "<xml><appid><![CDATA[wx4973a8b575999262]]</appid><mch_id><![CDATA[1320273701]]</mch_id><nonce_str><![CDATA[rc7p6eyhto16qdpzu7e8y43fq567mghv]]</nonce_str><sign><![CDATA[EDE307995AC6F7A9495D077112D7BDE0]]</sign><body><![CDATA[weixintest]]</body><out_trade_no><![CDATA[201412051510]]</out_trade_no><total_fee><![CDATA[1]]</total_fee><spbill_create_ip><![CDATA[8.8.8.8]]</spbill_create_ip><notify_url><![CDATA[http://www.prisonpublic.com/ywgk-app/api/weixin/callback]]</notify_url><trade_type><![CDATA[APP]]</trade_type></xml>";

			// System.out.println("统一下单，以前方法，生成的传输XML数据:"+xmlContent);
			// System.out.println("统一下单，微信提供的方法，生成的传输XML数据:"+xmlContent);
			HttpsRequest httpsRequest = new HttpsRequest();
			// String result = httpsRequest.sendPost(Configure.UNIFY_PAY_API,
			// xmlContent);
			String result = httpsRequest.sendPost(Configure.UNIFY_PAY_API,
					xmlContent);
			 System.out.println("调用统一下单接口后返回数据:"+result);
			return result;
		} catch (Exception e) {
			logger.info("e:" + e);
		}

		return null;
	}

	public static String generateRefundNativeReplyXML(PayPackage payPackage) {
		try {

			Map<String, String> map = BeanUtils.describe(payPackage);
			map.remove("class");
			// 尝试微信支付提供的方法
			String dto2sign = Signature.getSign4Object(payPackage);
			payPackage.setSign(dto2sign);

			XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.setSerializationInclusion(Include.NON_EMPTY);

			String xmlContent = xmlMapper.writeValueAsString(payPackage);
			
		//	System.out.println("xml------"+xmlContent);

		//	String xmlContent = "<xml><appid><![CDATA[wx4973a8b575999262]]</appid><mch_id><![CDATA[1320273701]]</mch_id><nonce_str><![CDATA[rc7p6eyhto16qdpzu7e8y43fq567mghv]]</nonce_str><sign><![CDATA[EDE307995AC6F7A9495D077112D7BDE0]]</sign><body><![CDATA[weixintest]]</body><out_trade_no><![CDATA[201412051510]]</out_trade_no><total_fee><![CDATA[1]]</total_fee><spbill_create_ip><![CDATA[8.8.8.8]]</spbill_create_ip><notify_url><![CDATA[http://www.prisonpublic.com/ywgk-app/api/weixin/callback]]</notify_url><trade_type><![CDATA[APP]]</trade_type></xml>";

			// System.out.println("统一下单，以前方法，生成的传输XML数据:"+xmlContent);
			// System.out.println("统一下单，微信提供的方法，生成的传输XML数据:"+xmlContent);
			HttpsRequest httpsRequest = new HttpsRequest();
			// String result = httpsRequest.sendPost(Configure.UNIFY_PAY_API,
			// xmlContent);
			String result = httpsRequest.sendPost(Configure.REFUND_API,
					xmlContent);
			 System.out.println("调用申请退款接口后返回数据:"+result);
			return result;
		} catch (Exception e) {
			logger.info("e:" + e);
		}

		return null;
	}
	
	public static Map<String, String> generateAppPay(PrePayInfo info) {
		// 提取需要重新签名的map
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("appid", info.getAppid());
		signMap.put("partnerid", info.getMch_id());
		signMap.put("prepayid", info.getPrepay_id());
		signMap.put("noncestr", info.getNonce_str());
		signMap.put("timestamp",
				Long.toString(new Date().getTime()).substring(0, 10));
		signMap.put("package", "Sign=WXPay");

		// String sign = Signature.generateSign(signMap);

		// 尝试微信二次签名
		String sign = Signature.getSign4Map(signMap);

		signMap.put("sign", sign);
		
		signMap.remove("package");
		signMap.put("packageName", "Sign=WXPay");
		return signMap;
	}

	public static PayCallbackNotify payCallbackNotify(InputStream inputStream) {
		try {
			String content = IOUtils.toString(inputStream);

			XmlMapper xmlMapper = new XmlMapper();
			PayCallbackNotify payCallbackNotify = xmlMapper.readValue(content,
					PayCallbackNotify.class);
			if (payCallbackNotify.getResult_code().equals("SUCCESS")
					&& payCallbackNotify.getReturn_code().equals("SUCCESS")) {
				payCallbackNotify.setPaySuccess(true);
			}
			return payCallbackNotify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String generatePaySuccessReplyXML() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>")
				.append("<return_code><![CDATA[SUCCESS]]></return_code>")
				.append("<return_msg><![CDATA[OK]]></return_msg>")
				.append("</xml>");
		return stringBuffer.toString();
	}

	/*
	 * <xml> <return_code><![CDATA[SUCCESS]]></return_code>
	 * <return_msg><![CDATA[OK]]></return_msg> </xml>
	 */
	public static String generateReplyXML(String return_code, String return_msg) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer
				.append("<xml>")
				.append("<return_code><![CDATA[" + return_code
						+ "]]></return_code>")
				.append("<return_msg><![CDATA[" + return_msg
						+ "]]></return_msg>").append("</xml>");
		return stringBuffer.toString();
	}

	public static String random_str() {
		StringBuilder stringBuilde = new StringBuilder();
		char[] c = { 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F',
				'f', 'U', 'u', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int length = c.length;
		Random random = new Random();
		for (int i = 0; i < 32; i++) {
			stringBuilde.append(c[random.nextInt(length)]);
		}
		return stringBuilde.toString();
	}

	public static void main(String[] args) {
		PayPackage payPackage = new PayPackage();
		payPackage.setBody("测试商品");
		payPackage.setOut_trade_no("2015060400001");
		payPackage.setTotal_fee("1");
		payPackage.setSpbill_create_ip("8.8.8.8");
		payPackage.setTrade_type("NATIVE");
		payPackage.setProduct_id("3");
		payPackage.setNonce_str(random_str());
		String back = PayUtils.generatePayNativeReplyXML(payPackage);
		System.out.println(back);
		// XmlMapper xmlMapper = new XmlMapper();
		// try {
		// PrePayInfo prePayInfo = xmlMapper.readValue(back, PrePayInfo.class);
		// if(prePayInfo.getResult_code().equals("SUCCESS")
		// && prePayInfo.getReturn_code().equals("SUCCESS")){
		// System.out.println(PayUtils.generateAppPay(prePayInfo));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
