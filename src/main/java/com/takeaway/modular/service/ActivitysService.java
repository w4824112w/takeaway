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
import com.takeaway.modular.dao.dto.ActivitysDto;
import com.takeaway.modular.dao.mapper.ActivitysMapper;
import com.takeaway.modular.dao.mapper.CouponMerchantsMapper;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.CouponMerchants;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ActivitysService {
	@Autowired
	private ActivitysMapper activitysMapper;

	@Autowired
	private CouponMerchantsMapper couponMerchantsMapper;
	
	public PageResult<Activitys> findPage(PageBounds bounds, ActivitysDto dto) {
		PageList<Activitys> activitys = activitysMapper.findPage(bounds, dto);
		return new PageResult<Activitys>(activitys);
	}

	@Transactional
	public JSONObject save(Activitys activitys,Integer[] merchants) {
		int result;
		activitys.setCreatedAt(new Date());
		result = activitysMapper.save(activitys);
		
		Integer  activitysId = activitys.getId();
		couponMerchantsMapper.delByTargetId(activitysId.toString());
		if(merchants.length>0){
			for(Integer merchantId:merchants){
				CouponMerchants couponMerchants=new CouponMerchants();
				couponMerchants.setType(0);
				couponMerchants.setTargetId(activitysId);
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
	public JSONObject update(Activitys activitys,Integer[] merchants) {
		int result;
		activitys.setUpdatedAt(new Date());
		result = activitysMapper.update(activitys);
		
		Integer  activitysId = activitys.getId();
		couponMerchantsMapper.delByTargetId(activitysId.toString());
		if(merchants.length>0){
			for(Integer merchantId:merchants){
				CouponMerchants couponMerchants=new CouponMerchants();
				couponMerchants.setType(0);
				couponMerchants.setTargetId(activitysId);
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

	public Activitys getById(String id) {
		Activitys activitys = activitysMapper.getById(id);
		return activitys;
	}

	public List<Activitys> getByMerchantId(String merchantId) {
		return activitysMapper.getByMerchantId(merchantId);
	}
	
	@Transactional
	public int delete(String id) {
		couponMerchantsMapper.delByTargetId(id.toString());
		return activitysMapper.delete(id);
		
	}

	public List<Activitys> getAll() {
		List<Activitys> activitys = activitysMapper.getAll();
		return activitys;
	}

}
