package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.CitiesDto;
import com.takeaway.modular.dao.dto.MerchantsDto;
import com.takeaway.modular.dao.model.MerchantPictures;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.Merchants;


public interface MerchantsMapper {
	
	Merchants getById(String id);
	
	PageList<MerchantsDto> findPage(PageBounds bounds,MerchantsDto dto);
	
	List<Merchants> getAll();
	
	List<Merchants> getByItemId(String itemId);
	
	List<MerchantsDto> appIndex();
	
	int save(Merchants merchants);
	
	int update(Merchants merchants);
	
	int delete(String id);
	
	
}