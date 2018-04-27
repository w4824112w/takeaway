package com.takeaway.modular.controller.web;

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
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UserCouponsService;

/**
 * 会员优惠券信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/user_coupons")
@Api(value = "会员优惠券管理", description = "UserCouponsController")
public class UserCouponsController {
	private static final Logger log = Logger
			.getLogger(UserCouponsController.class);

	@Autowired
	private UserCouponsService userCouponsService;

	@ApiOperation(value = "新增会员优惠券", httpMethod = "POST", notes = "新增会员优惠券信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserCoupons userCoupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		try {
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
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<UserCouponsDto> userCoupons = userCouponsService
				.getCoupons(userId);
		JSONObject result = new JSONObject();
		result.put("userCoupons", userCoupons);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员优惠券信息查询", result);

	}
}
