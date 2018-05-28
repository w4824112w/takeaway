package com.takeaway.commons.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;

public class OrderUtils {
	private static final Logger logger = Logger.getLogger(OrderUtils.class);
	private static final DateFormat fmt=new SimpleDateFormat("yyyyMMddhhmmsss");
	private static final char[] c = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
	public static String generateTradeNo(){
        StringBuilder stringBuilde = new StringBuilder();
        int length = c.length;
        Random random = new Random();
        for(int i=0;i <7;i++){
            stringBuilde.append(c[random.nextInt(length)]);
        }
        return fmt.format(new Date())+stringBuilde.toString();
    }
	
	public static void main(String[] args) {
		int i=0;
		while(i<10){
			System.out.println(OrderUtils.generateTradeNo());
			i++;
		}
		
 
	}
}
