package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.modular.dao.model.MerchantPictures;


public interface MerchantPicturesMapper {
	
	List<MerchantPictures> getByMerchantId(String merchantId);
	
	int delByMerchantId(String merchantId);
	
	int save(MerchantPictures merchantPictures);
	
}