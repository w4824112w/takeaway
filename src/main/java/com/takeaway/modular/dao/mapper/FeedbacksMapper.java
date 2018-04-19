package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.FeedbacksDto;
import com.takeaway.modular.dao.model.Feedbacks;


public interface FeedbacksMapper {
	
	Feedbacks getById(String id);
	
	PageList<Feedbacks> findPage(PageBounds bounds,FeedbacksDto dto);
	
	List<Feedbacks> getAll();
	
	List<Feedbacks> getByUserId(String userId);
	
	List<Feedbacks> getByMerchantId(String merchantId);
	
	int save(Feedbacks feedbacks);
	
	int update(Feedbacks feedbacks);
	
	int delete(String id);
	
}