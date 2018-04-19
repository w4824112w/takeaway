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
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderReserves;

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

	public PageResult<OrderCancles> findPage(PageBounds bounds, OrderCanclesDto dto) {
		PageList<OrderCancles> orderCancles = OrderCanclesMapper.findPage(bounds, dto);
		for(OrderCancles orderCancle:orderCancles){
			orderCancle.setOrderItems(orderItemsMapper.getByOrderId(orderCancle.getOrderId().toString()));
		}
		return new PageResult<OrderCancles>(orderCancles);
	}
	
	@Transactional
	public JSONObject save(OrderCancles orderCancles) {
		int result;

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
	
	@Transactional
	public int delete(String id) {
		return OrderCanclesMapper.delete(id);
	}

}
