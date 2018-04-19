package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.model.Items;


public interface ItemsMapper {
	
	Items getById(String id);
	
	PageList<Items> findPage(PageBounds bounds,ItemsDto dto);
	
	List<Items> getAll();
	
	int save(Items items);
	
	int update(Items items);
	
	int delete(String id);
	
	
}