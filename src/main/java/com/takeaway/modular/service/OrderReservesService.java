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
import com.takeaway.modular.dao.dto.OrderReservesDto;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrderReservesMapper;
import com.takeaway.modular.dao.model.OrderReserves;
import com.takeaway.modular.dao.model.Orders;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class OrderReservesService {
	@Autowired
	private OrderReservesMapper orderReservesMapper;
	
	@Autowired
	private OrderItemsMapper orderItemsMapper;

	public PageResult<OrderReserves> findPage(PageBounds bounds, OrderReservesDto dto) {
		PageList<OrderReserves> orderReserves = orderReservesMapper.findPage(bounds, dto);
		for(OrderReserves orderReserve:orderReserves){
			orderReserve.setOrderItems(orderItemsMapper.getByOrderId(orderReserve.getOrderId().toString()));
		}
		return new PageResult<OrderReserves>(orderReserves);
	}
	
	@Transactional
	public JSONObject save(OrderReserves orderReserves) {
		int result;

		orderReserves.setCreatedAt(new Date());
		result = orderReservesMapper.save(orderReserves);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单预定单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单预定单", null);
		}
	}

	@Transactional
	public JSONObject update(OrderReserves orderReserves) {
		int result;
		result = orderReservesMapper.update(orderReserves);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单预定单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单预定单", null);
		}
	}

	public OrderReserves getById(String id) {
		return orderReservesMapper.getById(id);
	}
	
	@Transactional
	public int delete(String id) {
		return orderReservesMapper.delete(id);
	}

}
