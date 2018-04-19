package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserAddressDto;
import com.takeaway.modular.dao.model.UserAddress;


public interface UserAddressMapper {
	
	UserAddress getById(String id);
	
	PageList<UserAddress> findPage(PageBounds bounds,UserAddressDto dto);
	
	List<UserAddress> getAll();
	
	List<UserAddress> getByUserId(String userId);
	
	int save(UserAddress userAddress);
	
	int update(UserAddress userAddress);
	
	int delete(String id);
	
}