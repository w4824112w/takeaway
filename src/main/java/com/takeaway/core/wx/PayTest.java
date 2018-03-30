package com.takeaway.core.wx;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.takeaway.core.wx.api.PayUtils;
import com.takeaway.core.wx.bean.PayPackage;
import com.takeaway.core.wx.bean.PrePayInfo;
import com.takeaway.core.wx.utils.Configure;
import com.takeaway.core.wx.utils.RandomStringGenerator;

public class PayTest {
	private static final Logger log = Logger
			.getLogger(PayTest.class);
	
	public static void main_bak(String[] args) {
		PayPackage payPackage = new PayPackage();
		payPackage.setAppid(Configure.getAppid());
		payPackage.setMch_id(Configure.getMchid());
		payPackage.setNotify_url(Configure.getNotifyCallbackUrl());
/*		payPackage.setBody(payReqeust.getStr("item_name"));
		payPackage.setOut_trade_no("2872"+payReqeust.getLong("id"));
		payPackage.setTotal_fee(Integer.toString(payReqeust.getInt("amount")));*/
		payPackage.setBody("weixin");
		payPackage.setOut_trade_no("123456");
		payPackage.setTotal_fee("2");
		payPackage.setSpbill_create_ip("8.8.8.8");
		payPackage.setTrade_type("APP");
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
					System.out.println("成功:"+map.toString());
				//	SUCCESS(map);						
				}else{
					System.out.println("失败:"+prePayInfo.getErr_code_des());
				//	ERROR(prePayInfo.getErr_code_des());						
				}
			}else{
				System.out.println("失败:"+prePayInfo.getReturn_msg());
			//	ERROR(prePayInfo.getReturn_msg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	public static void main(String args[])throws Exception{
		test_wx_pay();
	}
	
	public static void  test_wx_pay() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException{
		PayPackage payPackage = new PayPackage();
		payPackage.setAppid("wx4973a8b575999262");
		payPackage.setMch_id("1320273701");
		payPackage.setBody("weixin测试");
		payPackage.setOut_trade_no("201412051510");
		payPackage.setTotal_fee("1");
		payPackage.setSpbill_create_ip("8.8.8.8");
		payPackage.setTrade_type("APP");
		payPackage.setNotify_url("http://10.10.10.120:8888/ywgk-app/callback/wxpay");
		payPackage.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		
		String back = PayUtils.generatePayNativeReplyXML(payPackage);
		System.out.println("result----"+back);
	}
	
}
