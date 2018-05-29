package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.ActivitysDto;
import com.takeaway.modular.dao.model.Activitys;


public interface ActivitysMapper {
	
	Activitys getById(String id);
	
	PageList<Activitys> findPage(PageBounds bounds,ActivitysDto dto);
	
	List<Activitys> getAll();
	
	List<Activitys> getByMerchantId(String merchantId);
	
	String getTotalCount(String activityId);
	
	String getTotalPrice(String activityId);
	
	int save(Activitys activitys);
	
	int update(Activitys activitys);
	
	int delete(String id);
	
	
}