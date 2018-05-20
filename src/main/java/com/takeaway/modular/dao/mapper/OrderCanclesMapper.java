package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.OrderCanclesDto;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.Orders;

public interface OrderCanclesMapper {
	OrderCancles getById(String id);
	
	OrderCancles getByRefundNo(String refundNo);
	
	PageList<OrderCancles> findPage(PageBounds bounds, OrderCanclesDto dto);
	
	int save(OrderCancles orderCancles);

	int update(OrderCancles orderCancles);

	int delete(String id);

}