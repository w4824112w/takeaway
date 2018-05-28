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
import com.takeaway.modular.dao.dto.UserRanksDto;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.mapper.UserRanksMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.Users;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private UserRanksMapper userRanksMapper;

	public Users login(UsersDto dto) {
		dto.setLoginPwd(MD5Util.MD5(dto.getLoginPwd()));
		Users u = usersMapper.login(dto);
		return u;
	}

	public PageResult<UsersDto> findPage(PageBounds bounds, UsersDto dto) {
		PageList<UsersDto> users = usersMapper.findPage(bounds, dto);
		
		for(UsersDto user:users){
			UserRanksDto query=new UserRanksDto();
			dto.setUserScore(user.getUserScore());
			UserRanks userRanks=userRanksMapper.getCurrentUserRanks(query);
			if(userRanks!=null){
				user.setUserRank(userRanks.getName());
			}
		}
		

		
		return new PageResult<UsersDto>(users);
	}

	@Transactional
	public JSONObject save(Users user) {
		int result;
		int count = usersMapper.checkLoginName(user.getLoginName());
		if (count > 0) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账户名称已经存在", null);
		}

		user.setStatus(1);
		user.setCreatedAt(new Date());
		result = usersMapper.save(user);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员", null);
		}
	}

	@Transactional
	public JSONObject update(Users user) {
		int result;
		user.setUpdatedAt(new Date());
		result = usersMapper.update(user);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员", null);
		}
	}

	public Users getById(String id) {
		Users users=usersMapper.getById(id);
		UserRanksDto dto=new UserRanksDto();
		dto.setUserScore(users.getUserScore().toString());
		UserRanks userRanks=userRanksMapper.getCurrentUserRanks(dto);
		if(userRanks!=null){
			users.setUserRank(userRanks.getName());
		}
		return users;
	}
	
	public Users getByOpenid(String openid) {
		Users users=usersMapper.getByOpenid(openid);
		UserRanksDto dto=new UserRanksDto();
		dto.setUserScore(users.getUserScore().toString());
		UserRanks userRanks=userRanksMapper.getCurrentUserRanks(dto);
		if(userRanks!=null){
			users.setUserRank(userRanks.getName());
		}
		return usersMapper.getByOpenid(openid);
	}

	@Transactional
	public int delete(String id) {
		return usersMapper.delete(id);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret = 0;
		for (String id : ids) {
			int count = usersMapper.delete(id);
			ret = ret + count;
		}
		if (ret == ids.length) {
			return true;
		} else {
			return false;
		}
	}

}
