package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.OrderItemsDto;
import com.takeaway.modular.dao.dto.UserLogsDto;
import com.takeaway.modular.dao.model.OrderItems;


public interface OrderItemsMapper {
	
	List<OrderItems> getByOrderId(String orderId);
	
	PageList<OrderItemsDto> reportQuery(PageBounds bounds, OrderItemsDto dto);
	
	int delByOrderId(String orderId);
	
	int save(OrderItems orderItems);
	
}