package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.ItemTypesDto;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Orders;


public interface ItemTypesMapper {
	
	ItemTypes getById(String id);
	
	PageList<ItemTypes> findPage(PageBounds bounds,ItemTypesDto dto);
	
	List<ItemTypes> getAll();
	
	int save(ItemTypes itemTypes);
	
	int update(ItemTypes itemTypes);
	
	int delete(String id);
	
	List<ItemTypes> getByMerchantId(String merchantId);
}