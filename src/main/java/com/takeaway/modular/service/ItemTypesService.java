package com.takeaway.modular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ItemTypesDto;
import com.takeaway.modular.dao.mapper.ItemTypesMapper;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Orders;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ItemTypesService {
	@Autowired
	private ItemTypesMapper itemTypesMapper;

	public PageResult<ItemTypes> findPage(PageBounds bounds, ItemTypesDto dto) {
		PageList<ItemTypes> itemTypes = itemTypesMapper.findPage(bounds, dto);
		return new PageResult<ItemTypes>(itemTypes);
	}

	@Transactional
	public JSONObject save(ItemTypes itemTypes) {
		int result;
		result = itemTypesMapper.save(itemTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商品类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商品类型", result);
		}
	}

	@Transactional
	public JSONObject update(ItemTypes itemTypes) {
		int result;
		result = itemTypesMapper.update(itemTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品类型", result);
		}
	}

	public ItemTypes getById(String id) {
		ItemTypes itemTypes = itemTypesMapper.getById(id);
		return itemTypes;
	}

	@Transactional
	public int delete(String id) {
		return itemTypesMapper.delete(id);
	}

	public List<ItemTypes> getAll() {
		List<ItemTypes> itemTypes = itemTypesMapper.getAll();
		return itemTypes;
	}

	public List<ItemTypes> getAllByMerchantId(String merchantId) {
		return itemTypesMapper.getByMerchantId(merchantId);
	}
	
}
