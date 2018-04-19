package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.OrderHistorysDto;
import com.takeaway.modular.dao.model.OrderHistorys;

public interface OrderHistorysMapper {
	OrderHistorys getById(String id);
	
	int getProcessing();
	
	int getRefunding();
	
	int getTodayOrderHistorys();
	
	Double getTodayTurnover();

	PageList<OrderHistorys> findPage(PageBounds bounds, OrderHistorysDto dto);

	int save(OrderHistorys orderHistorys);

	int update(OrderHistorys orderHistorys);

	int delete(String id);

}