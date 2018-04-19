package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.OrderReservesDto;
import com.takeaway.modular.dao.model.OrderReserves;

public interface OrderReservesMapper {
	OrderReserves getById(String id);
	
	PageList<OrderReserves> findPage(PageBounds bounds, OrderReservesDto dto);
	
	int save(OrderReserves orderReserves);

	int update(OrderReserves orderReserves);

	int delete(String id);

}