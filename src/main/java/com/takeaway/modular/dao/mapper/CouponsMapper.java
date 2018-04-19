package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.model.Coupons;


public interface CouponsMapper {
	
	Coupons getById(String id);
	
	PageList<Coupons> findPage(PageBounds bounds,CouponsDto dto);
	
	List<Coupons> getAll();
	
	List<Coupons> getByMerchantId(String merchantId);
	
	int save(Coupons coupons);
	
	int update(Coupons coupons);
	
	int delete(String id);
	
	
}