package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.dto.ItemPicturesDto;
import com.takeaway.modular.dao.model.ItemPictures;


public interface ItemPicturesMapper {
	
	List<ItemPicturesDto> getByItemId(String itemId);
	
	int delByItemId(String itemId);
	
	int save(ItemPictures itemPictures);
	
}