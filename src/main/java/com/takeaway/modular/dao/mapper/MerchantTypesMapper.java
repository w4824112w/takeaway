package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.MerchantTypesDto;
import com.takeaway.modular.dao.model.MerchantTypes;


public interface MerchantTypesMapper {
	
	MerchantTypes getById(String id);
	
	PageList<MerchantTypes> findPage(PageBounds bounds,MerchantTypesDto dto);
	
	List<MerchantTypes> getAll();
	
	int save(MerchantTypes merchantTypes);
	
	int update(MerchantTypes merchantTypes);
	
	int delete(String id);
	
	
}