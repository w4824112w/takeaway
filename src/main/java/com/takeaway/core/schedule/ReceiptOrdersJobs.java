package com.takeaway.core.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.service.DistributionsService;
import com.takeaway.modular.service.OrderHistorysService;
import com.takeaway.modular.service.OrdersService;
@Component
public class ReceiptOrdersJobs {
	Logger log = Logger.getLogger(ReceiptOrdersJobs.class);
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private DistributionsService distributionsService;
	
	//超时未确认收货
	@Scheduled(cron = "0 0/30 * * * ?")
	public void cronJob() {
		  List<Orders> orders=ordersService.getAllByNotReceipt();
		  for(Orders order:orders){
			  long cha = new Date().getTime()-order.getPayDate().getTime();
		        double result = cha * 1.0 / (1000 * 60 * 60); 
		        if(result>=4){ 
					if (StringUtils.isBlank(order.getIssorderno())) {
						continue;
					}
					Map<String, Object> distribution = distributionsService.queryOrder(
							order.getOrderNo(), order.getIssorderno());
					
					if(distribution.get("status").toString().equals("OK")){
						Map<String, Object> data=(Map<String, Object>) distribution.get("data");
						if(Integer.parseInt(data.get("orderStatus").toString())==60){ // 待抢单20; 已抢单(待取件)30; 已就位(到达寄件人地址)42; 派送中44; 已完成60; 已取消64
				        	order.setStatus(4);	// 1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后 7 已退款 8 超时未支付作废 9 已删除
				        	order.setIsReceipt(1);	// 0：未收货;1：已收货;
							order.setReceiptDate(new Date());	// 收货时间
				        	order.setUpdatedAt(new Date());
				        	ordersService.update(order);
						}
					}
		        	
		        }
		  }
	}
	
}
