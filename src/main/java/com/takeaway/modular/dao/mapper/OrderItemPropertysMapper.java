package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.model.OrderItemPropertys;


public interface OrderItemPropertysMapper {
	
	List<OrderItemPropertys> getByOrderItemId(String orderItemId);
	
	int save(OrderItemPropertys orderItemPropertys);
	
}