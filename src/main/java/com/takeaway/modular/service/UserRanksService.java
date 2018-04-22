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
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UserRanksDto;
import com.takeaway.modular.dao.mapper.UserRanksMapper;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.UserRanks;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserRanksService {
	@Autowired
	private UserRanksMapper userRanksMapper;

	public PageResult<UserRanks> findPage(PageBounds bounds, UserRanksDto dto) {
		PageList<UserRanks> userRanks = userRanksMapper.findPage(bounds, dto);
		return new PageResult<UserRanks>(userRanks);
	}

	@Transactional
	public JSONObject save(UserRanks userRanks) {
		int result;
		userRanks.setCreatedAt(new Date());
		result = userRanksMapper.save(userRanks);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员等级", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员等级", null);
		}
	}

	@Transactional
	public JSONObject update(UserRanks userRanks) {
		int result;
		result = userRanksMapper.update(userRanks);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员等级", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员等级", null);
		}
	}

	public UserRanks getById(String id) {
		return userRanksMapper.getById(id);
	}

	public List<UserRanks> getByUserId(String userId) {
		return userRanksMapper.getByUserId(userId);
	}
	
	public List<UserRanks> getAll() {
		List<UserRanks> userRanks = userRanksMapper.getAll();
		return userRanks;
	}
	
	@Transactional
	public int delete(String id) {
		return userRanksMapper.delete(id);
	}

}
