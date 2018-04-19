package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.model.CouponMerchants;


public interface CouponMerchantsMapper {
	
	List<CouponMerchants> getByTargetId(String targetId);
	
	int delByTargetId(String targetId);
	
	int save(CouponMerchants couponMerchants);
	
}