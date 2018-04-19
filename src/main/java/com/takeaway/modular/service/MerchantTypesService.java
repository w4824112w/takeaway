package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.MerchantTypesDto;
import com.takeaway.modular.dao.mapper.MerchantTypesMapper;
import com.takeaway.modular.dao.model.MerchantTypes;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class MerchantTypesService {
	@Autowired
	private MerchantTypesMapper merchantTypesMapper;

	public PageResult<MerchantTypes> findPage(PageBounds bounds, MerchantTypesDto dto) {
		PageList<MerchantTypes> merchantTypes = merchantTypesMapper.findPage(bounds, dto);
		return new PageResult<MerchantTypes>(merchantTypes);
	}

	@Transactional
	public JSONObject save(MerchantTypes merchantTypes) {
		int result;
		result = merchantTypesMapper.save(merchantTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商户类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商户类型", result);
		}
	}

	@Transactional
	public JSONObject update(MerchantTypes merchantTypes) {
		int result;
		result = merchantTypesMapper.update(merchantTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商户类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商户类型", result);
		}
	}

	public MerchantTypes getById(String id) {
		MerchantTypes merchantTypes = merchantTypesMapper.getById(id);
		return merchantTypes;
	}

	@Transactional
	public int delete(String id) {
		return merchantTypesMapper.delete(id);
	}

	public List<MerchantTypes> getAll() {
		List<MerchantTypes> merchantTypes = merchantTypesMapper.getAll();
		return merchantTypes;
	}

}
