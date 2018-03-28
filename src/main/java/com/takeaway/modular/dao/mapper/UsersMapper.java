package com.takeaway.modular.dao.mapper;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.model.Users;

@Repository
public interface UsersMapper{
	
	UsersDto isSysLogin(UsersDto dto);
	
	UsersDto login(UsersDto dto);
	
	Users getUsersByConditions(UsersDto dto);
	
	int checkLoginName(String loginName);
	
	Users getById(String id);
	
	PageList<Users> findPage(PageBounds bounds,UsersDto dto);
	
	List<Users> getAll();
	
	int save(Users menu);
	
	int update(Users menu);
	
	int delete(String id);

	int deleteUser(Integer id, Date updateAt);

	PageList<Users> getUserList(PageBounds bounds, Map map);
}