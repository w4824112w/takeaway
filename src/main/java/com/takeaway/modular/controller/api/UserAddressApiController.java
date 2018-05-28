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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UserAddressService;
import com.takeaway.modular.service.UsersService;

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

	@Autowired
	private UsersService usersService;

	@ApiOperation(value = "新增会员收货地址", httpMethod = "POST", notes = "新增会员收货地址信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response,
			@ApiParam @RequestBody UserAddress userAddress) {
		try {
			return userAddressService.save(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "openid", value = "openid", required = false, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String openid) {
		Users user = usersService.getByOpenid(openid);
		String userId = user.getId().toString();
		List<UserAddress> userAddress = userAddressService.getByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("userAddress", userAddress);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员收货地址信息查询", result);

	}

	@ApiOperation(value = "编辑会员收货地址", httpMethod = "GET", notes = "编辑会员收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {

		UserAddress userAddress = userAddressService.getById(id);

		JSONObject result = new JSONObject();
		result.put("userAddress", userAddress);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑会员收货地址", result);
	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新会员收货地址")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			@ApiParam @RequestBody UserAddress userAddress) {
		try {

			log.info("id:"+userAddress.getId()+" userId:"+userAddress.getUserId()+"  name:"+userAddress.getName()+"  phone:"+userAddress.getPhone()+" address:"+userAddress.getAddress());
			return userAddressService.update(userAddress);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	@ApiOperation(value = "删除", httpMethod = "GET", notes = "删除会员收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "会员收货地址id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		log.info("id:"+id);
		try {
			int result = userAddressService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除会员收货地址",
						null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除会员收货地址", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除会员收货地址", null);
		}

	}

}
