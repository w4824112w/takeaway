package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Items;


public interface ItemsMapper {
	
	ItemsDto getById(String id);
	
	PageList<ItemsDto> findPage(PageBounds bounds,ItemsDto dto);
	
	List<Items> getAll();
	
	List<Items> getByMerchantId(String merchantId);
	
	int save(Items items);
	
	int update(Items items);
	
	int delete(String id);
	
	
}