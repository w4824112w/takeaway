package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.dto.CouponPicturesDto;
import com.takeaway.modular.dao.model.CouponPictures;


public interface CouponPicturesMapper {
	
	List<CouponPicturesDto> getByCouponId(String couponId);
	
	int delByCouponId(String couponId);
	
	int save(CouponPictures couponPictures);
	
}