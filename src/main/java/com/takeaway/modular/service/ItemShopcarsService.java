package com.takeaway.modular.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.controller.web.UploadController;
import com.takeaway.modular.dao.dto.ItemShopcarsDto;
import com.takeaway.modular.dao.mapper.ItemMerchantsMapper;
import com.takeaway.modular.dao.mapper.ItemPicturesMapper;
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.ItemShopcarsMapper;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.ItemShopcars;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ItemShopcarsService {
	@Autowired
	private ItemShopcarsMapper itemShopcarsMapper;

	@Transactional
	public JSONObject save(ItemShopcars itemShopcars, String[] itemPropertyIds) {
		int result = 0;

		if (itemShopcarsMapper.getByBatchNo(itemShopcars.getBatchNo()).size() <= 0) {
			String batchNo = RandomSequence.getSixteenRandomVal();
			itemShopcars.setBatchNo(batchNo);
		}
/*		for (String propertyId : itemPropertyIds) {
			itemShopcars.setItemPropertyId(Integer.parseInt(propertyId));
			result = itemShopcarsMapper.save(itemShopcars);
		}*/
		result = itemShopcarsMapper.save(itemShopcars);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增购物车商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增购物车商品", result);
		}
	}

	@Transactional
	public int delByBatchNo(String batchNo) {
		return itemShopcarsMapper.delByBatchNo(batchNo);
	}

	public List<ItemShopcars> getByBatchNo(String batchNo) {
		List<ItemShopcars> itemShopcars = itemShopcarsMapper
				.getByBatchNo(batchNo);
		return itemShopcars;
	}

	@Transactional
	public int delete(String id) {
		return itemShopcarsMapper.delete(id);
	}

}
