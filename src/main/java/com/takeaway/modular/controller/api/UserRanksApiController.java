package com.takeaway.modular.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

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
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.service.UserRanksService;

/**
 * 会员等级信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/user_ranks")
@Api(value = "会员等级信息---小程序接口", description = "UserRanksApiController")
public class UserRanksApiController {
	private static final Logger log = Logger
			.getLogger(UserRanksApiController.class);

	@Autowired
	private UserRanksService userRanksService;

	@ApiOperation(value = "新增会员等级等级", httpMethod = "POST", notes = "新增会员等级信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody UserRanks userRanks) {
		try {
			return userRanksService.save(userRanks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取所有会员等级")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		List<UserRanks> userRanks = userRanksService.getAll();
		JSONObject result = new JSONObject();
		result.put("userRanks", userRanks);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员等级信息查询", result);

	}
}
