package com.takeaway.modular.dao.mapper;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.Users_bakDto;
import com.takeaway.modular.dao.model.Users_bak;

@Repository
public interface UsersMapper{
	
	Users_bakDto isSysLogin(Users_bakDto dto);
	
	Users_bakDto login(Users_bakDto dto);
	
	Users_bak getUsersByConditions(Users_bakDto dto);
	
	int checkLoginName(String loginName);
	
	Users_bak getById(String id);
	
	PageList<Users_bak> findPage(PageBounds bounds,Users_bakDto dto);
	
	List<Users_bak> getAll();
	
	int save(Users_bak menu);
	
	int update(Users_bak menu);
	
	int delete(String id);

	int deleteUser(Integer id, Date updateAt);

	PageList<Users_bak> getUserList(PageBounds bounds, Map map);
}