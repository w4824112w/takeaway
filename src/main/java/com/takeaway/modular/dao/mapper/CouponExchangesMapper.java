package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponExchangesDto;
import com.takeaway.modular.dao.model.CouponExchanges;


public interface CouponExchangesMapper {
	
	CouponExchanges getById(String id);
	
	PageList<CouponExchangesDto> findPage(PageBounds bounds,CouponExchangesDto dto);
	
	List<CouponExchanges> getAll();
	
	int save(CouponExchanges couponExchanges);
	
	int update(CouponExchanges couponExchanges);
	
	int delete(String id);
	
	
}