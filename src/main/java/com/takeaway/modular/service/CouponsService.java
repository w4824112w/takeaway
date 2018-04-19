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
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.mapper.CouponMerchantsMapper;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.model.CouponMerchants;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.ItemMerchants;

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
	private CouponMerchantsMapper couponMerchantsMapper;
	
	public PageResult<Coupons> findPage(PageBounds bounds, CouponsDto dto) {
		PageList<Coupons> coupons = couponsMapper.findPage(bounds, dto);
		return new PageResult<Coupons>(coupons);
	}

	@Transactional
	public JSONObject save(Coupons coupons,Integer[] merchants) {
		int result;
		coupons.setCreatedAt(new Date());
		result = couponsMapper.save(coupons);
		
		Integer  couponsId = coupons.getId();
		couponMerchantsMapper.delByTargetId(couponsId.toString());
		if(merchants.length>0){
			for(Integer merchantId:merchants){
				CouponMerchants couponMerchants=new CouponMerchants();
				couponMerchants.setType(1);
				couponMerchants.setTargetId(couponsId);
				couponMerchants.setMerchantId(merchantId);
				couponMerchantsMapper.save(couponMerchants);
			}
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增优惠券", result);
		}
	}

	@Transactional
	public JSONObject update(Coupons coupons,Integer[] merchants) {
		int result;
		coupons.setUpdatedAt(new Date());
		result = couponsMapper.update(coupons);
		
		Integer  couponsId = coupons.getId();
		couponMerchantsMapper.delByTargetId(couponsId.toString());
		if(merchants.length>0){
			for(Integer merchantId:merchants){
				CouponMerchants couponMerchants=new CouponMerchants();
				couponMerchants.setType(1);
				couponMerchants.setTargetId(couponsId);
				couponMerchants.setMerchantId(merchantId);
				couponMerchantsMapper.save(couponMerchants);
			}
		}
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改优惠券", result);
		}
	}

	public Coupons getById(String id) {
		Coupons coupons = couponsMapper.getById(id);
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

}
