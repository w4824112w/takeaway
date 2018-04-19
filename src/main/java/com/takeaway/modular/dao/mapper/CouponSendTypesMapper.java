package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponSendTypesDto;
import com.takeaway.modular.dao.model.CouponSendTypes;


public interface CouponSendTypesMapper {
	
	CouponSendTypes getById(String id);
	
	PageList<CouponSendTypes> findPage(PageBounds bounds,CouponSendTypesDto dto);
	
	List<CouponSendTypes> getAll();
	
	int save(CouponSendTypes couponSendTypes);
	
	int update(CouponSendTypes couponSendTypes);
	
	int delete(String id);
	
	
}