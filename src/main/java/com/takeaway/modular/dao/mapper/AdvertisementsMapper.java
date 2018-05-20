package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.AdvertisementsDto;
import com.takeaway.modular.dao.model.Advertisements;


public interface AdvertisementsMapper {
	
	Advertisements getById(String id);
	
	PageList<Advertisements> findPage(PageBounds bounds,AdvertisementsDto dto);
	
	List<Advertisements> getAll();
	
	int save(Advertisements advertisements);
	
	int update(Advertisements advertisements);
	
	int delete(String id);
	
}