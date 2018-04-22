package com.takeaway.core.netpay.wxpay.utils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Util {
    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }
}

