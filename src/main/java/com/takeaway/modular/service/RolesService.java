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
	private RolesMapper rolesMapper;

	public PageResult<Roles> findPage(PageBounds bounds, RolesDto dto) {
		PageList<Roles> roles = rolesMapper.findPage(bounds, dto);
		return new PageResult<Roles>(roles);
	}

	@Transactional
	public JSONObject save(Roles role) {
		int result;
		role.setCreatedAt(new Date());
		result = rolesMapper.save(role);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增角色", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增角色", null);
		}
	}

	@Transactional
	public JSONObject update(Roles role) {
		int result;
		role.setUpdatedAt(new Date());
		result = rolesMapper.update(role);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新角色", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新角色", null);
		}
	}

	public Roles getById(String id) {
		return rolesMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		rolesMapper.deleteRoleMenu(id);
		return rolesMapper.delete(id);
	}

	public List<Roles> getAll() {
		return rolesMapper.getAll();
	}

	@Transactional
	public boolean saveRoleMenu(String[] menuIds, String roleId) {
		rolesMapper.deleteRoleMenu(roleId);
		int ret = 0;
		for (String menuId : menuIds) {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("menuId", menuId);
			obj.put("roleId", roleId);
			int count = rolesMapper.addRoleMenu(obj);
			ret = ret + count;
		}
		if (ret == menuIds.length) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret = 0;
		for (String id : ids) {
			rolesMapper.deleteRoleMenu(id);
			int count = rolesMapper.delete(id);
			ret = ret + count;
		}
		if (ret == ids.length) {
			return true;
		} else {
			return false;
		}
	}

}
