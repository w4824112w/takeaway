package com.takeaway.modular.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.mapper.OrderCouponRecordsMapper;
import com.takeaway.modular.dao.mapper.UserCouponsMapper;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.UserCoupons;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserCouponsService {
	@Autowired
	private UserCouponsMapper userCouponsMapper;
	
	@Autowired
	private CouponsMapper couponsMapper;

	public PageResult<UserCoupons> findPage(PageBounds bounds, UserCouponsDto dto) {
		PageList<UserCoupons> userCoupons = userCouponsMapper.findPage(bounds, dto);
		return new PageResult<UserCoupons>(userCoupons);
	}

	@Transactional
	public JSONObject save(UserCoupons userCoupons) {
		int result;
		
		CouponsDto coupons=couponsMapper.getById(userCoupons.getCouponId().toString());
		
		UserCouponsDto dto=new UserCouponsDto();
		dto.setUserId(userCoupons.getUserId().toString());
		dto.setCouponId(userCoupons.getCouponId().toString());
		dto.setCouponSendType("1"); // 优惠券发放类型(1:首页领取普通优惠券2:实付满多少送券3:后台操作送券4:首次点餐成功送券5:满多少直接减钱（就是上面的第二种）6:首次进入小程序送券（不重复）)
		List<UserCoupons> old_userCoupons=userCouponsMapper.getByUserIdAndCouponSendType(dto);
		if(old_userCoupons!=null&&old_userCoupons.size()>0){
			return ErrorEnums.getResult(ErrorEnums.ERROR, "已领取过首页优惠券,", null);
		}
		
		userCoupons.setCouponName(coupons.getName());
		Integer effectiveTime=Integer.parseInt(coupons.getEffectiveTime());	// 有效时间(单位：月)
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); //制定日期格式
		Calendar c=Calendar.getInstance();
		Date date=new Date();
		c.setTime(date);
		c.add(Calendar.MONTH,effectiveTime); //将当前日期加n个月
		String validityDate=df.format(c.getTime());  //返回String型的时间
		userCoupons.setEndDate(DateUtil.parseDateString(validityDate));
		
	//	userCoupons.setEndDate(DateUtil.parseDatetimeString(coupons.getEndDate()));
		
		userCoupons.setAmount(1);
		userCoupons.setStartDate(new Date());
		userCoupons.setGainTime(new Date());
		userCoupons.setStatus(1); // 使用状态(1:未用，0：已用 -1:删除)
		userCoupons.setIsFlag(1); // 有效状态(1:有效 -1:删除)
		result = userCouponsMapper.save(userCoupons);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员优惠券", null);
		}
	}

	@Transactional
	public JSONObject update(UserCoupons userCoupons) {
		int result;
		result = userCouponsMapper.update(userCoupons);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员优惠券", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员优惠券", null);
		}
	}

	public UserCoupons getById(String id) {
		return userCouponsMapper.getById(id);
	}

	public List<UserCoupons> getByUserId(String userId) {
		return userCouponsMapper.getByUserId(userId);
	}
	
	public List<UserCouponsDto> getCoupons(String userId) {
		UserCouponsDto dto=new UserCouponsDto();
		dto.setUserId(userId);
		List<UserCouponsDto> userCoupons=userCouponsMapper.getCoupons(dto);
		return userCoupons;
	}
	
	
	@Transactional
	public int delete(String id) {
		return userCouponsMapper.delete(id);
	}

}
