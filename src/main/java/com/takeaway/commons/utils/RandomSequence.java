package com.takeaway.commons.utils;

import java.util.Random;

/**
 * 产生8位长度字母和数字的组合，要求每次请求都不重复，区分大小写的随机字符串
 * @author Administrator
 *
 */
public class RandomSequence {
	  
	/**
	 * 生成随机数字和字母，长度为8位
	 * @return
	 */
    public static String getEightRandomVal() {  
        //默认产生8位随机值
    	int length=8;
    	
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    
    /**
     * 生成随机数字和字母，长度为16位
     * @return
     */
    public static String getSixteenRandomVal() {  
        //默认产生16位随机值
    	int length=16;
    	
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    public static void  main(String[] args) {  
        //测试  
        System.out.println(getEightRandomVal());  
        System.out.println(getSixteenRandomVal());  
        
    }  
    
}
