package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.model.Coupons;


public interface CouponsMapper {
	
	CouponsDto getById(String id);
	
	PageList<CouponsDto> findPage(PageBounds bounds,CouponsDto dto);
	
	List<Coupons> getAll();
	
	List<Coupons> getByCouponSendType(CouponsDto dto);
	
	List<CouponsDto> getIndexAll(String userId);
	
	List<Coupons> getBackAll();
	
	List<Coupons> getByMerchantId(String merchantId);
	
	int save(Coupons coupons);
	
	int update(Coupons coupons);
	
	int delete(String id);
	
	
}