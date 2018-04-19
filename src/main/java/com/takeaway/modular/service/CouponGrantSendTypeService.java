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
import com.takeaway.modular.dao.dto.CouponGrantSendTypeDto;
import com.takeaway.modular.dao.mapper.CouponGrantSendTypeMapper;
import com.takeaway.modular.dao.model.CouponGrantSendType;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class CouponGrantSendTypeService {
	@Autowired
	private CouponGrantSendTypeMapper couponGrantSendTypeMapper;

	public PageResult<CouponGrantSendType> findPage(PageBounds bounds, CouponGrantSendTypeDto dto) {
		PageList<CouponGrantSendType> couponGrantSendType = couponGrantSendTypeMapper.findPage(bounds, dto);
		return new PageResult<CouponGrantSendType>(couponGrantSendType);
	}

	@Transactional
	public JSONObject save(CouponGrantSendType couponGrantSendType) {
		int result;
		result = couponGrantSendTypeMapper.save(couponGrantSendType);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增代金卷发放规则类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增代金卷发放规则类型", result);
		}
	}

	@Transactional
	public JSONObject update(CouponGrantSendType couponGrantSendType) {
		int result;
		result = couponGrantSendTypeMapper.update(couponGrantSendType);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改代金卷发放规则类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改代金卷发放规则类型", result);
		}
	}

	public CouponGrantSendType getById(String id) {
		CouponGrantSendType couponGrantSendType = couponGrantSendTypeMapper.getById(id);
		return couponGrantSendType;
	}

	@Transactional
	public int delete(String id) {
		return couponGrantSendTypeMapper.delete(id);
	}

	public List<CouponGrantSendType> getAll() {
		List<CouponGrantSendType> couponGrantSendType = couponGrantSendTypeMapper.getAll();
		return couponGrantSendType;
	}

}
