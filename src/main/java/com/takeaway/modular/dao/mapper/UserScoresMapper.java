package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserScoresDto;
import com.takeaway.modular.dao.model.UserScores;


public interface UserScoresMapper {
	
	UserScores getById(String id);
	
	PageList<UserScores> findPage(PageBounds bounds,UserScoresDto dto);
	
	List<UserScores> getAll();
	
	List<UserScores> getByUserId(String userId);
	
	int save(UserScores userScores);
	
	int update(UserScores userScores);
	
	int delete(String id);
	
}