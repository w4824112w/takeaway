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
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrdersDto;
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

	public PageResult<Orders> findPage(PageBounds bounds, OrdersDto dto) {
		PageList<Orders> orders = ordersMapper.findPage(bounds, dto);
		for(Orders order:orders){
			order.setOrderItems(orderItemsMapper.getByOrderId(order.getId().toString()));
		}
		return new PageResult<Orders>(orders);
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

		orders.setCreatedAt(new Date());
		result = ordersMapper.save(orders);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单", result);
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
		return ordersMapper.getById(id);
	}
	
	public Orders getByOrderNo(String id) {
		return ordersMapper.getByOrderNo(id);
	}
	
	public List<Orders> getAllByUserId(String userId) {
		return ordersMapper.getByUserId(userId);
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
