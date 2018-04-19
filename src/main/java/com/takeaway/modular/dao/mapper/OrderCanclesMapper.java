package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.OrderCanclesDto;
import com.takeaway.modular.dao.model.OrderCancles;

public interface OrderCanclesMapper {
	OrderCancles getById(String id);
	
	PageList<OrderCancles> findPage(PageBounds bounds, OrderCanclesDto dto);
	
	int save(OrderCancles orderCancles);

	int update(OrderCancles orderCancles);

	int delete(String id);

}