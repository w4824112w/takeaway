package com.takeaway.modular.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
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
public class MenusService{
	@Autowired
	private MenusMapper MenusMapper;

	public PageResult<Menus> findPage(PageBounds bounds,MenusDto dto) {
	    PageList<Menus> menus=MenusMapper.findPage(bounds,dto);
		return new PageResult<Menus>(menus);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Menus menu) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		if(menu.getId()!=null){
			menu.setUpdatedAt(new Date());
			result = MenusMapper.update(menu);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改菜单成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改菜单失败");
			}
		}else{
			menu.setCreatedAt(new Date());
			result = MenusMapper.save(menu);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增菜单成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增菜单失败");
			}
		}
		
		return retData;
	}

	public Menus getById(String id) {
		Menus menu=MenusMapper.getById(id);
		MenusDto dto=new MenusDto();
		dto.setId(id);
		menu.setSubMenus(MenusMapper.getSubmenu(dto));
		return menu;
	}

	@Transactional
	public int delete(String id) {
		MenusMapper.deleteMenuRole(id);
		return MenusMapper.delete(id);
	}

	public List<Menus> getAll() {
		List<Menus> menus = MenusMapper.getAll();
		for(Menus menu:menus){
			MenusDto dto=new MenusDto();
			dto.setId(menu.getId().toString());
			menu.setSubMenus(MenusMapper.getSubmenu(dto));
		}
		return menus;
	}

	public List<Menus> getOneLevelMenu() {
		return MenusMapper.getOneLevelMenu();
	}

	public List<Menus> getByRoleId(String roleId) {
		return MenusMapper.getByRoleId(roleId);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret=0;
		for(String id:ids){
			MenusMapper.deleteMenuRole(id);
			int count=MenusMapper.delete(id);
			ret=ret+count;
		}
		if(ret==ids.length){
			return true;
		}else{
			return false;
		}
	}


}
