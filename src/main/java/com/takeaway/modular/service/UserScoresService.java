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
import com.takeaway.modular.dao.dto.UserScoresDto;
import com.takeaway.modular.dao.mapper.UserScoresMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.UserScores;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserScoresService {
	@Autowired
	private UserScoresMapper userScoresMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	public PageResult<UserScores> findPage(PageBounds bounds, UserScoresDto dto) {
		PageList<UserScores> userScores = userScoresMapper.findPage(bounds, dto);
		return new PageResult<UserScores>(userScores);
	}

	@Transactional
	public JSONObject save(UserScores userScores) {
		int result;
		userScores.setCreatedAt(new Date());
		result = userScoresMapper.save(userScores);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员积分明细", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员积分明细", null);
		}
	}

	@Transactional
	public JSONObject update(UserScores userScores) {
		int result;
		result = userScoresMapper.update(userScores);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员积分明细", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员积分明细", null);
		}
	}

	public UserScores getById(String id) {
		return userScoresMapper.getById(id);
	}

	public List<UserScores> getByUserId(String userId) {
		List<UserScores> userScores=userScoresMapper.getByUserId(userId);
		for(UserScores userScore:userScores){
			userScore.setUsers(usersMapper.getById(userScore.getUserId().toString()));
		}
		return userScores;
	}
	
	@Transactional
	public int delete(String id) {
		return userScoresMapper.delete(id);
	}

}
