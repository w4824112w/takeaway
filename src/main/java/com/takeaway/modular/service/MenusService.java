package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.MenusDto;
import com.takeaway.modular.dao.mapper.MenusMapper;
import com.takeaway.modular.dao.model.Menus;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class MenusService {
	@Autowired
	private MenusMapper menusMapper;

	public PageResult<Menus> findPage(PageBounds bounds, MenusDto dto) {
		PageList<Menus> menus = menusMapper.findPage(bounds, dto);
		return new PageResult<Menus>(menus);
	}

	@Transactional
	public JSONObject save(Menus menu) {
		int result;
		menu.setCreatedAt(new Date());
		result = menusMapper.save(menu);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增菜单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增菜单", result);
		}
	}

	@Transactional
	public JSONObject update(Menus menu) {
		int result;
		menu.setUpdatedAt(new Date());
		result = menusMapper.update(menu);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改菜单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改菜单", result);
		}
	}

	public Menus getById(String id) {
		Menus menu = menusMapper.getById(id);
		MenusDto dto = new MenusDto();
		dto.setId(id);
		menu.setSubMenus(menusMapper.getSubmenu(dto));
		return menu;
	}

	@Transactional
	public int delete(String id) {
		menusMapper.deleteMenuRole(id);
		return menusMapper.delete(id);
	}

	public List<Menus> getAll() {
		List<Menus> menus = menusMapper.getAll();
		for (Menus menu : menus) {
			MenusDto dto = new MenusDto();
			dto.setId(menu.getId().toString());
			menu.setSubMenus(menusMapper.getSubmenu(dto));
		}
		return menus;
	}

	public List<Menus> getOneLevelMenu() {
		return menusMapper.getOneLevelMenu();
	}

	public List<Menus> getByRoleId(String roleId) {
		return menusMapper.getByRoleId(roleId);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret = 0;
		for (String id : ids) {
			menusMapper.deleteMenuRole(id);
			int count = menusMapper.delete(id);
			ret = ret + count;
		}
		if (ret == ids.length) {
			return true;
		} else {
			return false;
		}
	}

}
