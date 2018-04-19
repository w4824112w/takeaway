package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.dto.ItemMerchantsDto;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.Items;


public interface ItemMerchantsMapper {
	
	List<ItemMerchants> getByItemId(String itemId);
	
	int delByItemId(String itemId);
	
	int save(ItemMerchants itemMerchants);
	
	int update(ItemMerchantsDto itemMerchants);
	
	int superUpdate(ItemMerchantsDto itemMerchants);
	
}