package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.PropertysDto;
import com.takeaway.modular.dao.model.Propertys;


public interface PropertysMapper {
	
	Propertys getById(String id);
	
	PageList<Propertys> findPage(PageBounds bounds,PropertysDto dto);
	
	List<Propertys> getAll();
	
	List<Propertys> getByItemId(String itemId);
	
	List<Propertys> getByPid(String pid);
	
	int save(Propertys propertys);
	
	int update(Propertys Propertys);
	
	int delete(String id);
	
}