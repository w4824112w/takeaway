package com.takeaway.commons.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    public static String Http_post(String url, Map<String, String> paramMap) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> m : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(m.getKey(), ((Object)m.getValue()).toString()));
        }
        String result = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpPost);
            // if(httpResponse.getStatusLine().getStatusCode() == 200)
            // {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);// 取出应答字符串
            System.out.println(result);
            // }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    
    
    public static String Http_get(String host,String paras) {
        String url = null;
        url = host+((paras==null||paras.equals(""))?"":("?"+ paras ));
        System.out.println(url);
        HttpGet httpGet =null;
        httpGet = new HttpGet(url);
        //httpGet.addHeader("Content-Type","application/x-www-form-urlencoded;charset=GBK");
        String result = "";
        try {
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpGet);
            // if(httpResponse.getStatusLine().getStatusCode() == 200)
            // {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            //result = EntityUtils.toString(httpEntity,"UTF-8");// 取出应答字符串
            //System.out.println(result );
            //System.out.println(.getBytes("GBK")));
            // }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

	public static void testRest()throws Exception {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		for(int i=0;i<6;i++){
			//调用get方法请求服务
			HttpGet httpget=new HttpGet("http://localhost:9000/getinfo");
			//获取响应
			HttpResponse response=httpclient.execute(httpget);
			//根据响应解析出字符串
			System.out.println(EntityUtils.toString(response.getEntity()));
		}
		
	}
    
    public static void main(String[] args) throws UnsupportedEncodingException{
        HttpUtil http = new HttpUtil();
        //URLEncoder.encode("杭州", "utf-8");
        HashMap  hm = new HashMap();
        HashMap  hm1 = new HashMap();
        hm.put("cid", "1000083");
        hm.put("date", "2015-06-04");
        hm.put("mid", "89");
        hm.put("cid", "1000083");
        hm.put("ttid", "1430623");
        hm1.put("Content-type", "text/html; charset=UTF-8");
        //String rs =HttpKit.get("http://127.0.0.1:8080/api/teetime", hm,hm1);
  /*     String rs = http.Http_get("http://test.golfton.com/api/teetime","cid=1000083&date=2015-06-04&mid=89&ttid=1430623");*/
        //  String   str   =   "\u53d6";
        //  System.out.println(str);

        String t = "hfjkds中国中国 dsjalf123A";
        //  思路：先转为Unicode，然后转为GBK
        //String utf8 = new String(t.getBytes( "UTF-8"));
        // System.out.println(utf8);
        //String unicode = new String(utf8.getBytes(),"UTF-8");
        // System.out.println(unicode);
  //      String gbk =    UTF2GBK.Unicode2GBK(rs);

    //    System.out.println(gbk);

        //  byte[] bs = rs.getBytes("ISO8859_1");
        //用新的字符编码生成字符串

        //System.out.println("----------"+rs);
        //System.out.println(http.Http_get("http://test.golfton.com/api/teetime","cid=1000083&date=2015-06-04&mid=89&ttid=1430623"));
        
        
        
    }
    
    public static void test(){
    	System.out.println("发送短信开始......");
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("targetNum", "120");
    	paramMap.put("sentNum", "1001016");
    	paramMap.put("channelId", "10086");
    	paramMap.put("smsContent", "测试短信1001016");
    	paramMap.put("tagId", "");
    	String url="http://10.62.6.177:8081/cac-provider/sms/send";
    	System.out.println("短信发送地址----"+url);
    	String result=Http_post(url,paramMap);
    	System.out.println("result------"+result);
    	System.out.println("发送短信结束......");
    	
    	System.out.println("短信回执开始......");
    	Date date=new Date();
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
    	String time=sdf.format(date);
    	Map<String, String> paramMap1 = new HashMap<String, String>();
    //	paramMap1.put("dateStr", time);
    	
    }
    
    
    public static String getIpAddr(HttpServletRequest request) {  
        String ipAddress = null;  
        //ipAddress = request.getRemoteAddr();  
        ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
         ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
         ipAddress = request.getRemoteAddr();  
         if(ipAddress.equals("127.0.0.1")){  
          //根据网卡取本机配置的IP  
         InetAddress inet=null;  
         try {  
        	 inet = InetAddress.getLocalHost();  
         } catch (UnknownHostException e) {  
        	 e.printStackTrace();  
         }  
         ipAddress= inet.getHostAddress();  
        }  
             
        }  
     
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress;  
   } 
}
