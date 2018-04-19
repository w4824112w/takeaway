package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.model.Users;


public interface UsersMapper {
	
	Users login(UsersDto dto);
	
	int checkLoginName(String loginName);
	
	Users getById(String id);
	
	PageList<UsersDto> findPage(PageBounds bounds,UsersDto dto);
	
	int save(Users user);
	
	int update(Users user);
	
	int delete(String id);
	
}