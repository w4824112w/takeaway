package com.takeaway.core.netpay.wxpay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HttpsRequest {

	private static final Logger logger = Logger.getLogger(HttpsRequest.class);

	// 表示请求器是否已经做了初始化工作
	private boolean hasInit = false;

	// 连接超时时间，默认10秒
	private int socketTimeout = 10000;

	// 传输超时时间，默认30秒
	private int connectTimeout = 30000;

	// 请求器的配置
	private RequestConfig requestConfig;

	// HTTP请求器
	private CloseableHttpClient httpClient;

	public HttpsRequest() throws UnrecoverableKeyException,
			KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, IOException {
		init();
		// noCert();
	}

	public HttpsRequest(String noCert) throws UnrecoverableKeyException,
			KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, IOException {
		noCert();
	}

	private void noCert() throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyManagementException {

		httpClient = HttpClients.custom().build();

		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();

		hasInit = true;
	}

	private void init() throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyManagementException {

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(
				Configure.getCertLocalPath()));// 加载本地的证书进行https加密传输
		try {
			keyStore.load(instream, Configure.getCertPassword().toCharArray());// 设置证书密码
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			instream.close();
		}

		@SuppressWarnings("deprecation")
		SSLContext sslcontext = SSLContexts
				.custom()
				.loadKeyMaterial(keyStore,
						Configure.getCertPassword().toCharArray()).build();
		@SuppressWarnings("deprecation")
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();

		hasInit = true;
	}

	/**
	 * 通过Https往API post xml数据
	 *
	 * @param url
	 *            API地址
	 * @param xmlObj
	 *            要提交的XML数据对象
	 * @return API回包的实际数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */

	public String sendPost(String url, String postDataXML) throws IOException,
			KeyStoreException, UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyManagementException {

		if (!hasInit) {
			init();
		}

		String result = null;

		HttpPost httpPost = new HttpPost(url);

		StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);

		httpPost.setConfig(requestConfig);

		try {
			HttpResponse response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();

			result = EntityUtils.toString(entity, "UTF-8");

		} catch (ConnectionPoolTimeoutException e) {
			logger.info("http get throw ConnectionPoolTimeoutException(wait time out)");

		} catch (ConnectTimeoutException e) {
			logger.info("http get throw ConnectTimeoutException");

		} catch (SocketTimeoutException e) {
			logger.info("http get throw SocketTimeoutException");

		} catch (Exception e) {
			logger.info("http get throw Exception");

		} finally {
			httpPost.abort();
		}

		return result;
	}

	public String sendGet(String url,String origins,String destinations) throws Exception {
		String srtResult = "";
		
		List<NameValuePair> params  = new ArrayList<NameValuePair>(); 
		
		params.add(new BasicNameValuePair("output", "json"));  //请求参数
		params.add(new BasicNameValuePair("origins", origins)); //请求参数
		params.add(new BasicNameValuePair("destinations", destinations)); //请求参数
		params.add(new BasicNameValuePair("ak", "MwAgf1hgh6GMEtmIy2xT1ecA5GxkjE1c")); //请求参数
		
        String str = "";  
        str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));  
        
        HttpGet httpGet = new HttpGet(url+"?"+str);  
        
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				srtResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
				System.out.println(srtResult);
			} else if (httpResponse.getStatusLine().getStatusCode() == 400) {
				// ..........
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				// .............
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return srtResult;
	}

	/**
	 * 设置连接超时时间
	 *
	 * @param socketTimeout
	 *            连接时长，默认10秒
	 */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
		resetRequestConfig();
	}

	/**
	 * 设置传输超时时间
	 *
	 * @param connectTimeout
	 *            传输时长，默认30秒
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		resetRequestConfig();
	}

	private void resetRequestConfig() {
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
	}

	/**
	 * 允许商户自己做更高级更复杂的请求器配置
	 *
	 * @param requestConfig
	 *            设置HttpsRequest的请求器配置
	 */
	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	public static void main(String args[]) throws Exception {
		// HttpsRequest httpsRequest = new HttpsRequest();
	/*	String str = "<xml><appid>wx49abba5920cc5f4d</appid><mch_id>1501745051</mch_id><nonce_str>4062A41E77CaAE6ACUe6CuFc5uu04089</nonce_str><sign>9ED9708E710F560A87BF840826166248</sign><out_trade_no>MWA9z2G88BK59JZO</out_trade_no><out_refund_no>jXqc54b9276u4057</out_refund_no><total_fee>3</total_fee><refund_fee>3</refund_fee><notify_url>https://pandax.mofasion.com/takeaway/api/weixin/callback</notify_url></xml>";
		String temp = str
				.replace(
						str.substring(str.indexOf("<notify_url>"),
								str.indexOf("</notify_url>")), "")
				.replace("<notify_url>", "").replace("</notify_url>", "");
		System.out.println("temp---" + temp);*/
		
	//	HttpsRequest httpsRequest = new HttpsRequest("noCert");
	//	String ret=httpsRequest.sendGet("http://api.map.baidu.com/routematrix/v2/driving","28.228863,112.994224","26.907657,112.558938");

		
	//	System.out.println("ret---"+ret);
	//	JSONObject.parse("{\"status":0,"result":[{"distance":{"text":"176公里","value":176008},"duration":{"text":"4小时","value":14223}}],"message":"成功"}");
	}

}
