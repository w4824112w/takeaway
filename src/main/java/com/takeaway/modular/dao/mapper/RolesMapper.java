package com.takeaway.modular.dao.mapper;

import java.util.List;
import java.util.Map;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.RolesDto;
import com.takeaway.modular.dao.model.Roles;


public interface RolesMapper {
	
	Roles getById(String id);
	
	PageList<Roles> findPage(PageBounds bounds,RolesDto dto);
	
	List<Roles> getAll();
	
	int save(Roles role);
	
	int update(Roles role);
	
	int delete(String id);
	
	void deleteRoleMenu(String roleId);
	
	int addRoleMenu(Map<String,Object> map);
	
}