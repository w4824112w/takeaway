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
import com.takeaway.modular.dao.dto.CouponSendTypesDto;
import com.takeaway.modular.dao.mapper.CouponSendTypesMapper;
import com.takeaway.modular.dao.model.CouponSendTypes;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class CouponSendTypesService {
	@Autowired
	private CouponSendTypesMapper couponSendTypesMapper;

	public PageResult<CouponSendTypes> findPage(PageBounds bounds, CouponSendTypesDto dto) {
		PageList<CouponSendTypes> couponTypes = couponSendTypesMapper.findPage(bounds, dto);
		return new PageResult<CouponSendTypes>(couponTypes);
	}

	@Transactional
	public JSONObject save(CouponSendTypes couponTypes) {
		int result;
		result = couponSendTypesMapper.save(couponTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增优惠券发放类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增优惠券发放类型", result);
		}
	}

	@Transactional
	public JSONObject update(CouponSendTypes couponTypes) {
		int result;
		result = couponSendTypesMapper.update(couponTypes);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改优惠券发放类型", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改优惠券发放类型", result);
		}
	}

	public CouponSendTypes getById(String id) {
		CouponSendTypes couponTypes = couponSendTypesMapper.getById(id);
		return couponTypes;
	}

	@Transactional
	public int delete(String id) {
		return couponSendTypesMapper.delete(id);
	}

	public List<CouponSendTypes> getAll() {
		List<CouponSendTypes> couponTypes = couponSendTypesMapper.getAll();
		return couponTypes;
	}

}
