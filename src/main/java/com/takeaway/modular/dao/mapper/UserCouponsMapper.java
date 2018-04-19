package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.UserCoupons;


public interface UserCouponsMapper {
	
	UserCoupons getById(String id);
	
	PageList<UserCoupons> findPage(PageBounds bounds,UserCouponsDto dto);
	
	List<UserCoupons> getAll();
	
	List<UserCoupons> getByUserId(String userId);
	
	List<UserCouponsDto> getCoupons(UserCouponsDto dto);
	
	int save(UserCoupons userCoupons);
	
	int update(UserCoupons userCoupons);
	
	int delete(String id);
	
}