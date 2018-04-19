package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.model.ItemShopcars;


public interface ItemShopcarsMapper {
	
	List<ItemShopcars> getByBatchNo(String batchNo);
	
	int delByBatchNo(String batchNo);
	
	int save(ItemShopcars itemMerchants);
	
	int delete(String id);
}