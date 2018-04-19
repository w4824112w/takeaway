package com.takeaway.modular.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UserFavoritesDto;
import com.takeaway.modular.dao.mapper.UserFavoritesMapper;
import com.takeaway.modular.dao.model.UserFavorites;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserFavoritesService {
	@Autowired
	private UserFavoritesMapper userFavoritesMapper;

	public PageResult<UserFavorites> findPage(PageBounds bounds, UserFavoritesDto dto) {
		PageList<UserFavorites> userFavorites = userFavoritesMapper.findPage(bounds, dto);
		return new PageResult<UserFavorites>(userFavorites);
	}

	@Transactional
	public JSONObject save(UserFavorites userFavorites) {
		int result;
		userFavorites.setCreatedAt(new Date());
		result = userFavoritesMapper.save(userFavorites);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员收藏", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员收藏", null);
		}
	}

	@Transactional
	public JSONObject update(UserFavorites userFavorites) {
		int result;
		result = userFavoritesMapper.update(userFavorites);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员收藏", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员收藏", null);
		}
	}

	public UserFavorites getById(String id) {
		return userFavoritesMapper.getById(id);
	}

	public List<UserFavorites> getByUserId(String userId) {
		return userFavoritesMapper.getByUserId(userId);
	}
	
	@Transactional
	public int delete(String id) {
		return userFavoritesMapper.delete(id);
	}

}
