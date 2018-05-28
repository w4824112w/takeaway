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
import com.takeaway.modular.dao.dto.CouponMerchantsDto;
import com.takeaway.modular.dao.dto.CouponPicturesDto;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.dto.ItemPicturesDto;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.mapper.CouponMerchantsMapper;
import com.takeaway.modular.dao.mapper.CouponPicturesMapper;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.model.CouponMerchants;
import com.takeaway.modular.dao.model.CouponPictures;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class CouponsService {
	@Autowired
	private CouponsMapper couponsMapper;
	
	@Autowired
	private CouponPicturesMapper couponPicturesMapper;
	
	@Autowired
	private CouponMerchantsMapper couponMerchantsMapper;
	
	@Autowired
	private MerchantsMapper merchantsMapper;
	
	public PageResult<CouponsDto> findPage(PageBounds bounds, CouponsDto dto) {
		PageList<CouponsDto> coupons = couponsMapper.findPage(bounds, dto);
		for (CouponsDto coupon : coupons) {
			List<CouponPicturesDto> pictures = couponPicturesMapper.getByCouponId(coupon
					.getId().toString());
			coupon.setPictures(pictures);
		}
		return new PageResult<CouponsDto>(coupons);
	}

	@Transactional
	public JSONObject save(Coupons coupons) {
		int result;
		coupons.setSendNum(0);
		coupons.setReceiveNum(0);
	//	coupons.setSendStartTime(new Date());
	//	coupons.setSendEndTime(new Date());
		coupons.setStatus(1);
		coupons.setCreatedAt(new Date());
		result = couponsMapper.save(coupons);
		
		Integer  couponsId = coupons.getId();
		
		for (CouponMerchantsDto dto : coupons.getMerchants()) {
			CouponMerchants couponMerchant=new CouponMerchants();
			couponMerchant.setTargetId(couponsId);
			couponMerchant.setType(Integer.parseInt(dto.getType()));
			couponMerchant.setMerchantId(Integer.parseInt(dto.getMerchantId()));
			couponMerchantsMapper.save(couponMerchant);
		}
		
		for (CouponPictures couponPictures : coupons.getPictures()) {
			couponPictures.setCouponId(couponsId);
			couponPictures.setCreatedAt(new Date());
			couponPicturesMapper.save(couponPictures);
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增优惠券", result);
		}
	}

	@Transactional
	public JSONObject update(Coupons coupons) {
		int result;
		coupons.setSendNum(0);
		coupons.setReceiveNum(0);
	//	coupons.setSendStartTime(new Date());
	//	coupons.setSendEndTime(new Date());
		coupons.setStatus(1);
		coupons.setUpdatedAt(new Date());
		result = couponsMapper.update(coupons);
		
		Integer  couponsId = coupons.getId();
		
		List<CouponPicturesDto> oldPictures = couponPicturesMapper.getByCouponId(couponsId
				.toString());
		
		couponMerchantsMapper.delByTargetId(couponsId.toString());
		couponPicturesMapper.delByCouponId(couponsId.toString());
		
		
		for (CouponMerchantsDto dto : coupons.getMerchants()) {
			CouponMerchants couponMerchant=new CouponMerchants();
			couponMerchant.setTargetId(couponsId);
			couponMerchant.setType(Integer.parseInt(dto.getType()));
			couponMerchant.setMerchantId(Integer.parseInt(dto.getMerchantId()));
			couponMerchantsMapper.save(couponMerchant);
		}
		
		for (CouponPictures couponPictures : coupons.getPictures()) {
			couponPictures.setCouponId(couponsId);
			couponPictures.setCreatedAt(new Date());
			couponPicturesMapper.save(couponPictures);
		}

		// 更新背景图片
		for (CouponPicturesDto picture : oldPictures) {
			File file = new File(picture.getUrl());
			file.delete();
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改优惠券", result);
		}
	}

	public CouponsDto getById(String id) {
		CouponsDto coupons = couponsMapper.getById(id);
		List<CouponMerchants> merchants=couponMerchantsMapper.getByTargetId(id);
		for(CouponMerchants couponMerchants:merchants){
			couponMerchants.setMerchant(merchantsMapper.getById(couponMerchants.getMerchantId().toString()));
		}
		coupons.setMerchants(merchants);
		coupons.setPictures(couponPicturesMapper.getByCouponId(id));
		return coupons;
	}

	public List<Coupons> getByMerchantId(String merchantId) {
		return couponsMapper.getByMerchantId(merchantId);
	}
	
	@Transactional
	public int delete(String id) {
		couponMerchantsMapper.delByTargetId(id.toString());
		return couponsMapper.delete(id);
		
	}

	public List<Coupons> getAll() {
		List<Coupons> coupons = couponsMapper.getAll();
		return coupons;
	}
	
	public List<Coupons> getBackAll() {
		List<Coupons> coupons = couponsMapper.getBackAll();
		return coupons;
	}
	
	public List<CouponsDto> getIndexAll(String userId) {
		List<CouponsDto> coupons = couponsMapper.getIndexAll(userId);
		for (CouponsDto coupon : coupons) {
			List<CouponPicturesDto> pictures = couponPicturesMapper.getByCouponId(coupon
					.getId().toString());
			coupon.setPictures(pictures);
		}
		return coupons;
	}


}
