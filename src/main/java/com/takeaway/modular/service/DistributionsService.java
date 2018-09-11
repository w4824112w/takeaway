package com.takeaway.modular.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.Orders;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class DistributionsService {
	private static final Logger log = Logger
			.getLogger(DistributionsService.class);

	private static String partnerNo = "";
	private static String md5Key = "";
	private static String mchId = "";
	private static String mchPhone = "";
	private static String mchToken = "";
	private static String devUrl = "";
	private static String prodUrl = "";

	public static Properties prop = new Properties();

	static {
		InputStream inputStream = DistributionsService.class
				.getResourceAsStream("/distribution.properties");
		try {
			prop.load(inputStream);
			partnerNo = prop.getProperty("partner_no");
			md5Key = prop.getProperty("md5_key");
			mchId = prop.getProperty("mch_id");
			mchPhone = prop.getProperty("mch_phone");
			mchToken = prop.getProperty("mch_token");
			devUrl = prop.getProperty("dev_url");
			prodUrl = prop.getProperty("prod_url");
		} catch (IOException e) {
			log.error("加载distribution.properties失败", e);
		}
	}

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;

	public Map<String, Object> computePrice(Orders orders,Merchants merchants) {
		String url = prodUrl + "openapi/order/v3/calc";
		String orderNo=orders.getOrderNo();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerNo", partnerNo);
		String parameter=partnerNo+orderNo+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("orderNo", orderNo);
		StringBuffer goods=new StringBuffer();
		for (OrderItems orderItem : orders.getOrderItems()) {
			ItemsDto items = itemsMapper.getById(orderItem.getItemId()
					.toString()); // 查询数据库对应商品
			goods.append(items.getName());
		}
		order.put("goods", goods);
		order.put("weight", 1);
		order.put("addition", 0);
		order.put("appointTime", null);
		order.put("remark", orders.getRemark());

		Map<String, String> merchant = new HashMap<String, String>();
		merchant.put("id", mchId);
		merchant.put("mobile", mchPhone);
		merchant.put("name", merchants.getName());
		merchant.put("token", mchToken);

		order.put("merchant", merchant);

		Map<String, String> sender = new HashMap<String, String>();
		sender.put("mobile", "13575240810");
		sender.put("name", merchants.getManagerName());
		sender.put("city", "衡阳市");
		sender.put("addr", merchants.getAddress());
		sender.put("addrDetail", null);
		sender.put("lng", merchants.getLat());
		sender.put("lat", merchants.getLng());

		order.put("sender", sender);

		List<Map<String, String>> receiverList = new ArrayList<Map<String, String>>();
		Map<String, String> receiver = new HashMap<String, String>();
		receiver.put("mobile", orders.getUserPhone());
		receiver.put("name", orders.getUserName());
		receiver.put("city", "衡阳市");
		receiver.put("addr", orders.getUserAddress());
		receiver.put("addrDetail", null);
		receiver.put("lng", orders.getLat());
		receiver.put("lat", orders.getLng());
		receiverList.add(receiver);

		order.put("receiverList", receiverList);

		param.put("order", order);

		String json = JSONObject.toJSON(param).toString();
		log.info("url------" + url + "---json------" + json);
		Map<String, Object> result=postForJson(url, param);
		return result;
	}
	
	public Map<String, Object> saveOrder(Orders orders,Merchants merchants) {
		String url = prodUrl + "openapi/order/v3/save";
		String orderNo=orders.getOrderNo();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerNo", partnerNo);
		String parameter=partnerNo+orderNo+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("orderNo", orderNo);
		StringBuffer goods=new StringBuffer();
		for (OrderItems orderItem : orders.getOrderItems()) {
			ItemsDto items = itemsMapper.getById(orderItem.getItemId()
					.toString()); // 查询数据库对应商品
			goods.append(items.getName());
		}
		order.put("goods", goods);
		order.put("weight", 1);
		order.put("addition", 0);
		order.put("appointTime", null);
		order.put("remark", orders.getRemark());

		Map<String, String> merchant = new HashMap<String, String>();
		merchant.put("id", mchId);
		merchant.put("mobile", mchPhone);
		merchant.put("name", merchants.getName());
		merchant.put("token", mchToken);

		order.put("merchant", merchant);

		Map<String, String> sender = new HashMap<String, String>();
		sender.put("mobile", "13575240810");
		sender.put("name", merchants.getManagerName());
		sender.put("city", "衡阳市");
		sender.put("addr", merchants.getAddress());
		sender.put("addrDetail", null);
		sender.put("lng", merchants.getLat());
		sender.put("lat", merchants.getLng());

		order.put("sender", sender);

		List<Map<String, String>> receiverList = new ArrayList<Map<String, String>>();
		Map<String, String> receiver = new HashMap<String, String>();
		receiver.put("mobile", orders.getUserPhone());
		receiver.put("name", orders.getUserName());
		receiver.put("city", "衡阳市");
		receiver.put("addr", orders.getUserAddress());
		receiver.put("addrDetail", null);
		receiver.put("lng", orders.getLat());
		receiver.put("lat", orders.getLng());
		receiverList.add(receiver);

		order.put("receiverList", receiverList);

		param.put("order", order);

		String json = JSONObject.toJSON(param).toString();
		log.info("url------" + url + "---json------" + json);
		Map<String, Object> result=postForJson(url, param);
		return result;
	}
	
	public Map<String, Object> queryOrder(String orderno,String issorderno){
		String url = prodUrl + "openapi/order/v3/info?partnerno={partnerno}&orderno={orderno}&mobile={mobile}&signature={signature}&issorderno={issorderno}";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerno", partnerNo);
		param.put("orderno", orderno);
		param.put("mobile", mchPhone);
		
		String parameter=partnerNo+orderno+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);
		param.put("issorderno", issorderno);
		
		Map<String, Object> result=getForJson(url, param);
		return result; 
	}
	
	public Map<String, Object> queryTrail(String orderno,String issorderno){
		String url = prodUrl + "openapi/order/v3/trail?partnerno={partnerno}&orderno={orderno}&mobile={mobile}&signature={signature}&issorderno={issorderno}";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerno", partnerNo);
		param.put("orderno", orderno);
		param.put("mobile", mchPhone);
		
		String parameter=partnerNo+orderno+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);
		param.put("issorderno", issorderno);
		
		Map<String, Object> result=getForJson(url, param);
		return result; 
	}

	public Map<String, Object> cancel(String orderno,String issorderno){
		String url = prodUrl + "openapi/order/v3/cancel?partnerno={partnerno}&orderno={orderno}&mobile={mobile}&signature={signature}&issorderno={issorderno}";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerno", partnerNo);
		param.put("orderno", orderno);
		param.put("mobile", mchPhone);
		
		String parameter=partnerNo+orderno+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);
		param.put("issorderno", issorderno);
		
		Map<String, Object> result=getForJson(url, param);
		return result; 
	}
	
	public static Map<String, Object> postForJson(String url,
			Map<String, Object> param) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1,
				new StringHttpMessageConverter(StandardCharsets.UTF_8));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(param,
				headers);

		String json = restTemplate.postForEntity(url, entity, String.class)
				.getBody();

		Map<String, Object> result = (Map<String, Object>) JSONObject
				.parse(json);
		log.info("result-------" + result);
		return result;
	}

	public static Map<String, Object> getForJson(String url,
			Map<String, Object> params) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1,
				new StringHttpMessageConverter(StandardCharsets.UTF_8));

		String json = restTemplate.getForObject(url, String.class, params);

		Map<String, Object> result = (Map<String, Object>) JSONObject
				.parse(json);
		return result;
	}

	public static String MD5(String parameter) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestResult = md.digest(StringUtils.defaultString(parameter).getBytes("GBK"));
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < digestResult.length; i++) {
				int val = ((int) digestResult[i]) & 0xff;
				hexValue.append(StringUtils.leftPad(Integer.toHexString(val), 2, '0'));
			}
			return hexValue.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameter;
	}

	public static void main(String args[]) {
		String url=devUrl+"openapi/order/v3/calc";
	//	String url = prodUrl + "openapi/order/v3/save";
		String orderNo="20180830093503194QKA51";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerNo", partnerNo);
		String parameter=partnerNo+orderNo+mchPhone+md5Key;
		String signature=MD5(parameter);
		param.put("signature", signature);

		Map<String, Object> order = new HashMap<String, Object>();
		order.put("orderNo", orderNo);
		order.put("goods", "test");
		order.put("weight", 1);
		order.put("addition", 0);
		order.put("appointTime", null);
		order.put("remark", "123456");

		Map<String, String> merchant = new HashMap<String, String>();
		merchant.put("id", mchId);
		merchant.put("mobile", mchPhone);
		merchant.put("name", "葵花里居委会");
		merchant.put("token", mchToken);

		order.put("merchant", merchant);

		Map<String, String> sender = new HashMap<String, String>();
		sender.put("mobile", "13575240810");
		sender.put("name", "徐总");
		sender.put("city", "衡阳市");
		sender.put("addr", "衡阳市珠晖区葵花里居委会");
	//	sender.put("addrDetail", "D座403室");
		sender.put("lng", "116.40");
		sender.put("lat", "39.91");

		order.put("sender", sender);

		List<Map<String, String>> receiverList = new ArrayList<Map<String, String>>();
		Map<String, String> receiver = new HashMap<String, String>();
		receiver.put("mobile", "17773433888");
		receiver.put("name", "string");
		receiver.put("city", "衡阳市");
		receiver.put("addr", "string");
	//	receiver.put("addrDetail", "D座403室");
		receiver.put("lng", "116.28307342529294");
		receiver.put("lat", "40.04860781805171");
		receiverList.add(receiver);

		order.put("receiverList", receiverList);

		// String json_order = JSONObject.toJSON(order).toString();
		param.put("order", order);

		String json = JSONObject.toJSON(param).toString();
		System.out.println("url------" + url + "---json------" + json);
		Map<String, Object> result=postForJson(url, param);
	}
	
}
