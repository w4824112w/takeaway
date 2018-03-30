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
import com.takeaway.modular.dao.dto.RolesDto;
import com.takeaway.modular.dao.mapper.RolesMapper;
import com.takeaway.modular.dao.model.Roles;


/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class RolesService {
	@Autowired
	private RolesMapper sysroleMapper;

	public PageResult<Roles> findPage(PageBounds bounds,RolesDto dto) {
	    PageList<Roles> roles=sysroleMapper.findPage(bounds,dto);
		return new PageResult<Roles>(roles);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Roles role) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		if(role.getId()!=null){
			role.setUpdatedAt(new Date());
			result = sysroleMapper.update(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改角色成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改角色失败");
			}
		}else{
			role.setCreatedAt(new Date());
			result = sysroleMapper.save(role);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增角色成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增角色失败");
			}
		}
		
		return retData;
	}

	public Roles getById(String id) {
		return sysroleMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		sysroleMapper.deleteRoleMenu(id);
		return sysroleMapper.delete(id);
	}

	public List<Roles> getAll() {
		return sysroleMapper.getAll();
	}

	@Transactional
	public boolean saveRoleMenu(String[] menuIds, String roleId) {
		sysroleMapper.deleteRoleMenu(roleId);
		int ret=0;
		for(String menuId:menuIds){
			Map<String,Object> obj=new HashMap<String,Object>();
			obj.put("menuId", menuId);
			obj.put("roleId", roleId);
			int count=sysroleMapper.addRoleMenu(obj);
			ret=ret+count;
		}
		if(ret==menuIds.length){
			return true;
		}else{
			return false;
		}
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret=0;
		for(String id:ids){
			sysroleMapper.deleteRoleMenu(id);
			int count=sysroleMapper.delete(id);
			ret=ret+count;
		}
		if(ret==ids.length){
			return true;
		}else{
			return false;
		}
	}


}
