package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.mapper.ManagersMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.model.Managers;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ManagersService {
	@Autowired
	private ManagersMapper managersMapper;
	
	@Autowired
	private MerchantsMapper merchantsMapper;

	public Managers login(ManagersDto dto) {
		dto.setPasswordHash(MD5Util.MD5(dto.getPasswordHash()));
		Managers u = managersMapper.login(dto);
		return u;
	}

	public PageResult<ManagersDto> findPage(PageBounds bounds, ManagersDto dto) {
		PageList<ManagersDto> managers = managersMapper.findPage(bounds, dto);
		return new PageResult<ManagersDto>(managers);
	}

	@Transactional
	public JSONObject save(Managers user) {
		int result;

		// 默认密码，md5加密
		user.setPasswordHash(MD5Util.MD5(user.getPasswordHash()));

		int count = managersMapper.checkLoginName(user.getName());
		if (count > 0) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账户名称已经存在", null);
		}

		user.setCreatedAt(new Date());
		result = managersMapper.save(user);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增用户", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增用户", null);
		}

	}

	@Transactional
	public JSONObject update(Managers user) {
		int result;
		user.setUpdatedAt(new Date());
		result = managersMapper.update(user);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新用户", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新用户", null);
		}
	}

	public Managers getById(String id) {
		Managers managers=managersMapper.getById(id);
		if(managers.getType()!=1){
			managers.setMerchants(merchantsMapper.getById(managers.getMerchantId().toString()));
		}
		return managers;
	}

	@Transactional
	public int delete(String id) {
		return managersMapper.delete(id);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret = 0;
		for (String id : ids) {
			int count = managersMapper.delete(id);
			ret = ret + count;
		}
		if (ret == ids.length) {
			return true;
		} else {
			return false;
		}
	}

}
