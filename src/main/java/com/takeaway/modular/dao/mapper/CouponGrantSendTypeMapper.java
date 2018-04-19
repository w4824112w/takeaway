package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponGrantSendTypeDto;
import com.takeaway.modular.dao.model.CouponGrantSendType;


public interface CouponGrantSendTypeMapper {
	
	CouponGrantSendType getById(String id);
	
	PageList<CouponGrantSendType> findPage(PageBounds bounds,CouponGrantSendTypeDto dto);
	
	List<CouponGrantSendType> getAll();
	
	int save(CouponGrantSendType couponGrantSendType);
	
	int update(CouponGrantSendType couponGrantSendType);
	
	int delete(String id);
	
	
}