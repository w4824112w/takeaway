package com.takeaway.modular.controller.api;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.service.UserAddressService;

/**
 * 会员收货地址信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/user_address")
@Api(value = "会员收货地址信息---小程序接口", description = "UserAddressApiController")
public class UserAddressApiController {
	private static final Logger log = Logger
			.getLogger(UserAddressApiController.class);

	@Autowired
	private UserAddressService userAddressService;

	@ApiOperation(value = "新增会员收货地址收货地址", httpMethod = "POST", notes = "新增会员收货地址信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserAddress userAddress) {
		try {
			return userAddressService.save(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		List<UserAddress> userAddress = userAddressService.getByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("userAddress", userAddress);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员收货地址信息查询", result);

	}
}
