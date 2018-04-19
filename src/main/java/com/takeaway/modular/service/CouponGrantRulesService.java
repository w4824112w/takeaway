package com.takeaway.modular.service;

import java.io.File;
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
import com.takeaway.modular.controller.web.UploadController;
import com.takeaway.modular.dao.dto.CouponGrantRulesDto;
import com.takeaway.modular.dao.mapper.ItemMerchantsMapper;
import com.takeaway.modular.dao.mapper.ItemPicturesMapper;
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.CouponGrantRulesMapper;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.CouponGrantRules;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class CouponGrantRulesService {
	@Autowired
	private CouponGrantRulesMapper couponGrantRulesMapper;
	
	public PageResult<CouponGrantRules> findPage(PageBounds bounds, CouponGrantRulesDto dto) {
		PageList<CouponGrantRules> couponGrantRules = couponGrantRulesMapper.findPage(bounds, dto);
		return new PageResult<CouponGrantRules>(couponGrantRules);
	}

	@Transactional
	public JSONObject save(CouponGrantRules couponGrantRules, Integer[] coupons) {
		int result = 0;
		couponGrantRules.setCreatedAt(new Date());
		
		for(Integer couponId:coupons){
			couponGrantRules.setCouponId(couponId);
			result = couponGrantRulesMapper.save(couponGrantRules);
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增代金卷发放规则", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增代金卷发放规则", result);
		}
	}

	@Transactional
	public JSONObject update(CouponGrantRules couponGrantRules, Integer[] coupons) {
		int result = 0;
		couponGrantRules.setUpdatedAt(new Date());

		for(Integer couponId:coupons){
			couponGrantRules.setCouponId(couponId);
			result = couponGrantRulesMapper.update(couponGrantRules);
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改代金卷发放规则", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改代金卷发放规则", result);
		}
	}

	public CouponGrantRules getById(String id) {
		CouponGrantRules couponGrantRules = couponGrantRulesMapper.getById(id);
		return couponGrantRules;
	}

	@Transactional
	public int delete(String id) {
		return couponGrantRulesMapper.delete(id);
	}

	public List<CouponGrantRules> getAll() {
		List<CouponGrantRules> couponGrantRules = couponGrantRulesMapper.getAll();
		return couponGrantRules;
	}

}
