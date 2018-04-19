package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.dto.ItemPropertysDto;
import com.takeaway.modular.dao.model.ItemPropertys;


public interface ItemPropertysMapper {
	
	List<ItemPropertys> getByItemId(String itemId);
	
	int delByItemId(String itemId);
	
	int save(ItemPropertys temPropertys);
	
}