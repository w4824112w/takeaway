package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.MenusDto;
import com.takeaway.modular.dao.model.Menus;


public interface MenusMapper {
	
	Menus getById(String id);
	
	PageList<Menus> findPage(PageBounds bounds,MenusDto dto);
	
	List<Menus> getAll();
	
	List<Menus> getSubmenu(MenusDto dto);
	
	List<Menus> getOneLevelMenu();
	
	List<Menus> getByRoleId(String roleId);
	
	int save(Menus menu);
	
	int update(Menus menu);
	
	int delete(String id);
	
	void deleteMenuRole(String menuId);
	
}