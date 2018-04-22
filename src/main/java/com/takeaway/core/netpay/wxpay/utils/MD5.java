package com.takeaway.core.netpay.wxpay.utils;

import java.security.MessageDigest;

public class MD5 {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(resultString.getBytes("UTF-8"));
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static void main(String args[]){
    	String info="appid=wx4973a8b575999262&body=weixintest&mch_id=1320273701&nonce_str=z8plqho5d7ialhnl9az5ej63848y4wey&notify_url=http://127.0.0.1/api/weixin/callback&out_trade_no=201412051510&spbill_create_ip=8.8.8.8&total_fee=1&trade_type=APP&key=d75699d893882dea526ea05e9c7a4090";
    	System.out.println("info___"+MD5Encode(info));
    }
    
    
}

