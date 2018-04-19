package com.takeaway.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String s) {

        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes("utf-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
    public static void main(String[] args) {
//    	ORDERS.add("payType");
//		ORDERS.add("responseMode");
//		ORDERS.add("merchantId");
//		ORDERS.add("subMerchantId");
//		ORDERS.add("subMerchantName");
//		ORDERS.add("storeId");
//		ORDERS.add("orderId");
//		ORDERS.add("orderDate");
//		ORDERS.add("amount");
//		ORDERS.add("merchantUrl");
//		ORDERS.add("retUrl");
//		ORDERS.add("key");
    	
//    	payType=1
//    			&responseMode=1
//    			&merchantId=999073154110001
//    			&subMerchantId=999073170110005
//    			&subMerchantName=%E5%9B%A2%E5%A4%B4%E6%B9%96%E7%94%9F%E6%80%81%E5%85%BB%E6%AE%96%E4%B8%93%E4%B8%9A%E5%90%88%E4%BD%9C%E7%A4%BE
//    			&storeId=
//    			&orderId=95bc0248d316eed681621726fa
//    			&orderDate=20150723154112
//    			&amount=100.00
//    			&merchantUrl=http%3A%2F%2Fhsk.hsk.la%2Ffrontend_notice_reagle_pay_web
//    			&retUrl=http%3A%2F%2Fhsk.hsk.la%2Fbackend_notice_reagle_pay_web
//    			&key=zSZV07kFaI
    			
    	String s = "payType=1"
    			+ "&responseMode=1"
    			+ "&merchantId=999073154110001"
    			+ "&subMerchantId=999073170110005"
    			+ "&subMerchantName=%E5%9B%A2%E5%A4%B4%E6%B9%96%E7%94%9F%E6%80%81%E5%85%BB%E6%AE%96%E4%B8%93%E4%B8%9A%E5%90%88%E4%BD%9C%E7%A4%BE"
    			+ "&storeId="
    			+ "&orderId=67392eecfd7befa79283b8c408"
    			+ "&orderDate=20150723151516"
    			+ "&amount=100.00"
    			+ "&merchantUrl=http%3A%2F%2Fhsk.hsk.la%2Ffrontend_notice_reagle_pay_web"
    			+ "&retUrl=http%3A%2F%2Fhsk.hsk.la%2Fbackend_notice_reagle_pay_web"
    			+ "&key=zSZV07kFaI";
    	try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	System.out.println("123456______"+MD5Util.MD5("123456"));
        System.out.println(MD5Util.MD5(s));
        //bd854be7a1c571dda9678c371aee1b58
        System.out.println(MD5Util.MD5("加密"));
    }
}
