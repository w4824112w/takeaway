package com.takeaway.modular.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.CouponsService;
import com.takeaway.modular.service.UserCouponsService;
import com.takeaway.modular.service.UsersService;

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

	@Autowired
	private CouponsService couponsService;
	
	@Autowired
	private UsersService usersService;
	
	@ApiOperation(value = "新增会员优惠券", httpMethod = "POST", notes = "新增会员优惠券信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserCoupons userCoupons) {
		try {
			log.info("couponId:"+userCoupons.getCouponId()+" userId:"+userCoupons.getUserId());
			return userCouponsService.save(userCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "查询所有优惠卷列表", httpMethod = "GET", notes = "查询所有优惠卷列表信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "openid", value = "openid", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public JSONObject all(HttpServletRequest request,
			HttpServletResponse response,String openid) {
		Users user=usersService.getByOpenid(openid);
		String userId=user.getId().toString();
		List<CouponsDto> coupons = couponsService.getIndexAll(userId);

		JSONObject result = new JSONObject();
		result.put("coupons", coupons);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}
	
	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员优惠券")
	@ApiImplicitParams({ @ApiImplicitParam(name = "openid", value = "openid", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String openid) {
		Users user=usersService.getByOpenid(openid);
		String userId=user.getId().toString();
		List<UserCouponsDto> userCoupons = userCouponsService
				.getCoupons(userId);
		JSONObject result = new JSONObject();
		result.put("userCoupons", userCoupons);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员优惠券信息查询", result);

	}
}
