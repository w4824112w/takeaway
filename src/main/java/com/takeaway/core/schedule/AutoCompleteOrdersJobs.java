package com.takeaway.core.schedule;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.service.OrdersService;
@Component
public class AutoCompleteOrdersJobs {
	Logger log = Logger.getLogger(AutoCompleteOrdersJobs.class);
	@Autowired
	private OrdersService ordersService;
	
	//每天2点开始获取到店已支付超过24小时但未确认完成的订单
	@Scheduled(cron = "0 0 2 * * ?")
	public void cronJob() {
		  List<Orders> orders=ordersService.getAllByNotComplete();
		  for(Orders order:orders){
			  long cha = new Date().getTime()-order.getPayDate().getTime();
		        double result = cha * 1.0 / (1000 * 60 * 60); 
		        if(result>=24){
		        	order.setStatus(5);	// 1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后 7 已退款 8 超时未支付作废 9 已删除
		        	order.setIsReceipt(1);	// 0：未收货;1：已收货;
					order.setReceiptDate(new Date());	// 收货时间
		        	order.setUpdatedAt(new Date());
		        	ordersService.update(order);
		        }
		  }
	}
	
}
