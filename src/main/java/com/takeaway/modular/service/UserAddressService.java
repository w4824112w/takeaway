package com.takeaway.modular.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.commons.utils.MapDistance;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UserAddressDto;
import com.takeaway.modular.dao.mapper.UserAddressMapper;
import com.takeaway.modular.dao.model.UserAddress;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserAddressService {
	@Autowired
	private UserAddressMapper userAddressMapper;

	public PageResult<UserAddress> findPage(PageBounds bounds, UserAddressDto dto) {
		PageList<UserAddress> userAddress = userAddressMapper.findPage(bounds, dto);
		return new PageResult<UserAddress>(userAddress);
	}

	@Transactional
	public JSONObject save(UserAddress userAddress) {
		int result;
		userAddress.setCreatedAt(new Date());
		result = userAddressMapper.save(userAddress);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员收货地址", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员收货地址", null);
		}
	}

	@Transactional
	public JSONObject update(UserAddress userAddress) {
		int result;
		result = userAddressMapper.update(userAddress);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员收货地址", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员收货地址", null);
		}
	}

	public UserAddress getById(String id) {
		return userAddressMapper.getById(id);
	}

	public List<UserAddress> getByUserId(String lat,String lng,Integer distributionScope,String userId) {
		List<UserAddress> userAddress=userAddressMapper.getByUserId(userId);
		List<UserAddress> result=new ArrayList<UserAddress>();
		List<UserAddress> front=new ArrayList<UserAddress>();
		List<UserAddress> back=new ArrayList<UserAddress>();
		for(UserAddress userAddres:userAddress){
			if(StringUtils.isBlank(lat)||StringUtils.isBlank(lng)||StringUtils.isBlank(userAddres.getLng())||StringUtils.isBlank(userAddres.getLat())){
				userAddres.setIsDistributionScope(0);
				back.add(userAddres);
				continue;
			}
			String distance=MapDistance.getDistance(lng,lat,userAddres.getLng(),userAddres.getLat());
			if(Integer.parseInt(distance)<=distributionScope){
				userAddres.setIsDistributionScope(1); // 是否在范围内(0:不在范围内 1:在范围内)
				front.add(userAddres);
			}else{
				userAddres.setIsDistributionScope(0);
				back.add(userAddres);
			}
		}
		result.addAll(front);
		result.addAll(back);
		return result;
	}
	
	@Transactional
	public int delete(String id) {
		return userAddressMapper.delete(id);
	}

}
