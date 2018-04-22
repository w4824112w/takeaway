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
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.mapper.UserCouponsMapper;
import com.takeaway.modular.dao.model.UserCoupons;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserCouponsService {
	@Autowired
	private UserCouponsMapper userCouponsMapper;

	public PageResult<UserCoupons> findPage(PageBounds bounds, UserCouponsDto dto) {
		PageList<UserCoupons> userCoupons = userCouponsMapper.findPage(bounds, dto);
		return new PageResult<UserCoupons>(userCoupons);
	}

	@Transactional
	public JSONObject save(UserCoupons userCoupons) {
		int result;
		
		userCoupons.setStatus(1); // 使用状态(1:未用，0：已用 -1:删除)
		userCoupons.setIsFlag(1); // 有效状态(1:有效 -1:删除)
		result = userCouponsMapper.save(userCoupons);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员优惠券", null);
		}
	}

	@Transactional
	public JSONObject update(UserCoupons userCoupons) {
		int result;
		result = userCouponsMapper.update(userCoupons);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员优惠券", null);
		}
	}

	public UserCoupons getById(String id) {
		return userCouponsMapper.getById(id);
	}

	public List<UserCoupons> getByUserId(String userId) {
		return userCouponsMapper.getByUserId(userId);
	}
	
	public List<UserCouponsDto> getCoupons(String userId) {
		UserCouponsDto dto=new UserCouponsDto();
		dto.setUserId(userId);
		return userCouponsMapper.getCoupons(dto);
	}
	
	
	@Transactional
	public int delete(String id) {
		return userCouponsMapper.delete(id);
	}

}
