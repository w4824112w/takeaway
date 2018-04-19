package com.takeaway.modular.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CouponExchangesDto;
import com.takeaway.modular.dao.mapper.CouponMerchantsMapper;
import com.takeaway.modular.dao.mapper.CouponExchangesMapper;
import com.takeaway.modular.dao.model.CouponMerchants;
import com.takeaway.modular.dao.model.CouponExchanges;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class CouponExchangesService {
	@Autowired
	private CouponExchangesMapper couponExchangesMapper;

	public PageResult<CouponExchangesDto> findPage(PageBounds bounds, CouponExchangesDto dto) {
		PageList<CouponExchangesDto> couponExchanges = couponExchangesMapper.findPage(bounds, dto);
		return new PageResult<CouponExchangesDto>(couponExchanges);
	}

	@Transactional
	public JSONObject save(CouponExchanges couponExchanges, Integer[] coupons) {
		int result = 0;
		couponExchanges.setCreatedAt(new Date());

		for(Integer couponId:coupons){
			couponExchanges.setCouponId(couponId);
			result = couponExchangesMapper.save(couponExchanges);
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增优惠活动", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增优惠活动", result);
		}
	}

	@Transactional
	public JSONObject update(CouponExchanges couponExchanges, Integer[] coupons) {
		int result = 0;
		couponExchanges.setUpdatedAt(new Date());
		
		for(Integer couponId:coupons){
			couponExchanges.setCouponId(couponId);
			result = couponExchangesMapper.update(couponExchanges);
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改优惠活动", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改优惠活动", result);
		}
	}

	public CouponExchanges getById(String id) {
		CouponExchanges couponExchanges = couponExchangesMapper.getById(id);
		return couponExchanges;
	}

	@Transactional
	public int delete(String id) {
		return couponExchangesMapper.delete(id);
		
	}

	public List<CouponExchanges> getAll() {
		List<CouponExchanges> couponExchanges = couponExchangesMapper.getAll();
		return couponExchanges;
	}

}
