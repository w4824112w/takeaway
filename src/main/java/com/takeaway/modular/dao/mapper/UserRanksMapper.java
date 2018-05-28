package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserRanksDto;
import com.takeaway.modular.dao.model.UserRanks;


public interface UserRanksMapper {
	
	UserRanks getById(String id);
	
	UserRanks getCurrentUserRanks(UserRanksDto dto);
	
	PageList<UserRanks> findPage(PageBounds bounds,UserRanksDto dto);
	
	List<UserRanks> getAll();
	
	List<UserRanks> getByUserId(String userId);
	
	int save(UserRanks userRanks);
	
	int update(UserRanks userRanks);
	
	int delete(String id);
	
}