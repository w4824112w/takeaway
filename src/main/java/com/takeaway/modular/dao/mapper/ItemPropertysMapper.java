package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.dto.ItemPropertysDto;
import com.takeaway.modular.dao.model.ItemPropertys;


public interface ItemPropertysMapper {
	
	List<ItemPropertys> getByItemId(String itemId);
	
	ItemPropertys getById(String id);
	
	ItemPropertys getByItemIdAndPropertyId(ItemPropertys temPropertys);
	
	List<ItemPropertys> getByPropertyId(ItemPropertys temPropertys);
	
	int delByItemId(String itemId);
	
	int save(ItemPropertys temPropertys);
	
}