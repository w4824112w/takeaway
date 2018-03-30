package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;


public interface ManagersMapper {
	
	Managers login(ManagersDto dto);
	
	int checkLoginName(String loginName);
	
	Managers getById(String id);
	
	PageList<Managers> findPage(PageBounds bounds,ManagersDto dto);
	
	int save(Managers user);
	
	int update(Managers user);
	
	int delete(String id);
	
}