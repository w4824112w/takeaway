package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.dto.BusinessReportDto;
import com.takeaway.modular.dao.dto.OrderItemsDto;
import com.takeaway.modular.dao.dto.OrdersDto;
import com.takeaway.modular.dao.dto.ReportDto;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.Orders;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderItemsMapper orderItemsMapper;

	public PageResult<OrdersDto> findPage(PageBounds bounds, OrdersDto dto) {
		PageList<OrdersDto> orders = ordersMapper.findPage(bounds, dto);
		for(OrdersDto order:orders){
			order.setOrderItems(orderItemsMapper.getByOrderId(order.getId().toString()));
		}
		return new PageResult<OrdersDto>(orders);
	}
	
	public PageResult<ReportDto> reportQuery(PageBounds bounds, ReportDto dto) {
		PageList<ReportDto> report = ordersMapper.reportQuery(bounds, dto);
		return new PageResult<ReportDto>(report);
	}
	
	public PageResult<BusinessReportDto> businessReport(PageBounds bounds, BusinessReportDto dto) {
		PageList<BusinessReportDto> report = ordersMapper.businessReport(bounds, dto);
		return new PageResult<BusinessReportDto>(report);
	}
	
	public List<ReportDto> reportExport(ReportDto dto) {
		List<ReportDto> report = ordersMapper.reportExport(dto);
		return report;
	}
	
	public PageResult<OrderItemsDto> salesReportQuery(PageBounds bounds, OrderItemsDto dto) {
		PageList<OrderItemsDto> report = orderItemsMapper.reportQuery(bounds, dto);
		return new PageResult<OrderItemsDto>(report);
	}
	
	public PageResult<Orders> findReminderPage(PageBounds bounds, OrdersDto dto) {
		PageList<Orders> orders = ordersMapper.findReminderPage(bounds, dto);
		for(Orders order:orders){
			order.setOrderItems(orderItemsMapper.getByOrderId(order.getId().toString()));
		}
		return new PageResult<Orders>(orders);
	}

	@Transactional
	public JSONObject save(Orders orders) {
		int result;

		String orderNo = RandomSequence.getSixteenRandomVal(); // 订单编号
		orders.setOrderNo(orderNo);
		orders.setStatus(0); // 待处理
		orders.setIsPay(0); // 未支付
		orders.setIsShip(0); // 未发货
		orders.setIsReceipt(0); // 未收货
		orders.setIsReceived(0); // 未接单
		orders.setIsRefund(0); // 未退款
		orders.setIsReservation(0); // 未预定
		orders.setIsReminder(0); // 未催单
		orders.setIsDistribution(0); // 配送中
		orders.setIsInvoice(0); // 是否需要发票
		orders.setIsAppraises(0); // 是否点评
		orders.setCreatedAt(new Date());
		result = ordersMapper.save(orders);
		
		Integer orderId = orders.getId();
		
		for(OrderItems orderItems:orders.getOrderItems()){
			orderItems.setOrderId(orderId);
			orderItemsMapper.save(orderItems);
		}
		
		JSONObject ret = new JSONObject();
		ret.put("orders", orders);
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单", ret);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单", null);
		}
	}

	@Transactional
	public JSONObject update(Orders orders) {
		int result;
		orders.setUpdatedAt(new Date());
		result = ordersMapper.update(orders);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单", null);
		}
	}

	public Orders getById(String id) {
		Orders orders= ordersMapper.getById(id);
		orders.setOrderItems(orderItemsMapper.getByOrderId(id));
		return orders;
	}
	
	public Orders getByOrderNo(String orderNo) {
		return ordersMapper.getByOrderNo(orderNo);
	}
	
	public List<Orders> getAllByUserId(String userId) {
		return ordersMapper.getByUserId(userId);
	}
	
	public List<Orders> getAllByPay(String userId) {
		return ordersMapper.getByPay(userId);
	}
	
	public List<Orders> getAllByShip(String userId) {
		return ordersMapper.getByShip(userId);
	}
	
	public List<Orders> getAllByAppraises(String userId) {
		return ordersMapper.getByAppraises(userId);
	}
	
	public List<Orders> getAllByRefund(String userId) {
		return ordersMapper.getByRefund(userId);
	}
	
	public List<OrderItems> getByOrderId(String orderId) {
		return orderItemsMapper.getByOrderId(orderId);
	}
	
	public JSONObject OrderIndex() {
		JSONObject result = new JSONObject();
		result.put("processing", ordersMapper.getProcessing());
		result.put("refunding", ordersMapper.getRefunding());
		result.put("todayOrders", ordersMapper.getTodayOrders());
		result.put("todayTurnover", ordersMapper.getTodayTurnover());
		return result;
	}

	@Transactional
	public int delete(String id) {
		return ordersMapper.delete(id);
	}

}
