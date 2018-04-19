package com.takeaway.modular.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.service.UserCouponsService;

/**
 * 会员优惠券信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/user_coupons")
@Api(value = "会员优惠券信息---小程序接口", description = "UserCouponsApiController")
public class UserCouponsApiController {
	private static final Logger log = Logger
			.getLogger(UserCouponsApiController.class);

	@Autowired
	private UserCouponsService userCouponsService;

	@ApiOperation(value = "新增会员优惠券", httpMethod = "POST", notes = "新增会员优惠券信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "couponId", value = "优惠券id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "couponCode", value = "优惠券兑换码", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "amount", value = "数量", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "userName", value = "会员名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "type", value = "优惠券类型", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "startDate", value = "有效开始时间", required = false, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "endDate", value = "有效结束时间", required = false, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "gainTime", value = "获得时间", required = false, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "couponName", value = "优惠券名称", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer couponId, String couponCode,
			Integer amount, Integer userId,String userName,Integer type,Date startDate,Date endDate,Date gainTime,String couponName) {
		try {
			UserCoupons userCoupons = new UserCoupons();
			userCoupons.setCouponId(couponId);
			userCoupons.setCouponCode(couponCode);
			userCoupons.setAmount(amount);
			userCoupons.setUserId(userId);
			userCoupons.setUserName(userName);
			userCoupons.setType(type);
			userCoupons.setStartDate(startDate);
			userCoupons.setEndDate(endDate);
			userCoupons.setGainTime(gainTime);
			userCoupons.setCouponName(couponName);
			userCoupons.setStatus(1); // 使用状态(1:未用，0：已用 -1:删除)
			userCoupons.setIsFlag(1); // 有效状态(1:有效 -1:删除)
			return userCouponsService.save(userCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员优惠券")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		List<UserCouponsDto> userCoupons = userCouponsService
				.getCoupons(userId);
		JSONObject result = new JSONObject();
		result.put("userCoupons", userCoupons);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员优惠券信息查询", result);

	}
}
