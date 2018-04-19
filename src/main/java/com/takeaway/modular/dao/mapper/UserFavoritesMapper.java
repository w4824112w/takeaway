package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserFavoritesDto;
import com.takeaway.modular.dao.model.UserFavorites;


public interface UserFavoritesMapper {
	
	UserFavorites getById(String id);
	
	PageList<UserFavorites> findPage(PageBounds bounds,UserFavoritesDto dto);
	
	List<UserFavorites> getAll();
	
	List<UserFavorites> getByUserId(String userId);
	
	List<UserFavorites> getfavorites(UserFavoritesDto dto);
	
	int save(UserFavorites userFavorites);
	
	int update(UserFavorites userFavorites);
	
	int delete(String id);
	
}