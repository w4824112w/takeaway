package com.takeaway.core.schedule;

import java.io.IOException;
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
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.service.DistributionsService;
import com.takeaway.modular.service.OrderHistorysService;
import com.takeaway.modular.service.OrdersService;

@Component
public class PrintOrdersJobs {
	Logger log = Logger.getLogger(PrintOrdersJobs.class);
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private DistributionsService distributionsService;
	
	@Autowired
	private MerchantsMapper merchantsMapper;

	// 每1分钟查询闪送订单是否打印
	@Scheduled(cron = "0 0/1 * * * ?")
	public void cronJob() throws IOException{
		List<Orders> orders = ordersService.getAllByNotPrint();
		for (Orders order : orders) {
			if (StringUtils.isNotBlank(order.getIssorderno())) {
				Map<String, Object> shansong_order = distributionsService
						.queryOrder(order.getOrderNo(), order.getIssorderno());

				if (shansong_order != null) {
					log.info("闪送订单查询....." + shansong_order.toString());
					Map shansong_data = (Map) shansong_order.get("data");
					if (shansong_data != null) {
						String pickupPassword = shansong_data
								.get("pickupPassword") == null ? ""
								: shansong_data.get("pickupPassword")
										.toString();
						if (StringUtils.isNotBlank(pickupPassword)) {
							Merchants merchants = merchantsMapper.getById(order
									.getMerchantId().toString());
							
							Orders waitPrintOrder = ordersService.getByOrderNo(order.getOrderNo());
							
							Orders waitDistributionOrder = ordersService.getById(waitPrintOrder.getId().toString());
							waitDistributionOrder.setStatus(3);	//	1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后
							waitDistributionOrder.setIsDistribution(1);
							waitDistributionOrder.setDistributionDate(new Date());
							ordersService.updateDistribution(waitDistributionOrder);
							
							JSONObject json = new JSONObject();
							json.put("code", 200);
							json.put("type", 1);
							json.put("order", waitPrintOrder);
							json.put("pickupPassword", pickupPassword);
							json.put("merchantName", merchants.getName());

							log.info("开始发送websocket消息........"
									+ json.toJSONString());
							WebSocketServer.sendInfo(order.getMerchantId()
									.toString(), json.toJSONString());
							
							order.setIsPrint(1);	// 修改闪送单为已打印
							ordersService.update(order);
						}
					}
				}
			}

		}
	}

}
