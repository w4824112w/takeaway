package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrderCanclesDto;
import com.takeaway.modular.dao.mapper.OrderCanclesMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderReserves;
import com.takeaway.modular.dao.model.Orders;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class OrderCanclesService {
	@Autowired
	private OrderCanclesMapper OrderCanclesMapper;
	
	@Autowired
	private OrderItemsMapper orderItemsMapper;
	
	@Autowired
	private OrdersMapper ordersMapper;

	public PageResult<OrderCancles> findPage(PageBounds bounds, OrderCanclesDto dto) {
		PageList<OrderCancles> orderCancles = OrderCanclesMapper.findPage(bounds, dto);
		for(OrderCancles orderCancle:orderCancles){
			orderCancle.setOrders(ordersMapper.getById(orderCancle.getOrderId().toString()));
			orderCancle.setOrderItems(orderItemsMapper.getByOrderId(orderCancle.getOrderId().toString()));
		}
		return new PageResult<OrderCancles>(orderCancles);
	}
	
	@Transactional
	public JSONObject save(OrderCancles orderCancles) {
		int result;

		Orders orders=ordersMapper.getById(orderCancles.getOrderId().toString());
		orders.setStatus(6);// 1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后 7 已退款 8 超时未支付作废
		orders.setRefundApplyDate(new Date());
		ordersMapper.update(orders);
		
		orderCancles.setCreatedAt(new Date());
		result = OrderCanclesMapper.save(orderCancles);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单退单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单退单", null);
		}
	}

	@Transactional
	public JSONObject update(OrderCancles orderCancles) {
		int result;
		result = OrderCanclesMapper.update(orderCancles);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单退单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单退单", null);
		}
	}

	public OrderCancles getById(String id) {
		return OrderCanclesMapper.getById(id);
	}
	
	public OrderCancles getByRefundNo(String refundNo) {
		return OrderCanclesMapper.getByRefundNo(refundNo);
	}
}
