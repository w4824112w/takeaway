package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CouponGrantRulesDto;
import com.takeaway.modular.dao.model.CouponGrantRules;


public interface CouponGrantRulesMapper {
	
	CouponGrantRules getById(String id);
	
	PageList<CouponGrantRules> findPage(PageBounds bounds,CouponGrantRulesDto dto);
	
	List<CouponGrantRules> getAll();
	
	int save(CouponGrantRules couponGrantRules);
	
	int update(CouponGrantRules couponGrantRules);
	
	int delete(String id);
	
	
}