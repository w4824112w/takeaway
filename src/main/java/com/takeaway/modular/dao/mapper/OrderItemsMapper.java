package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.model.OrderItems;


public interface OrderItemsMapper {
	
	List<OrderItems> getByOrderId(String orderId);
	
	int delByOrderId(String orderId);
	
	int save(OrderItems orderItems);
	
}