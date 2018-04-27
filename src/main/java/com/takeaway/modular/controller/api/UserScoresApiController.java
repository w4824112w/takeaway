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
import com.takeaway.modular.dao.model.UserScores;
import com.takeaway.modular.service.UserScoresService;

/**
 * 会员积分明细信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/user_scores")
@Api(value = "会员积分明细信息---小程序接口", description = "UserScoresApiController")
public class UserScoresApiController {
	private static final Logger log = Logger
			.getLogger(UserScoresApiController.class);

	@Autowired
	private UserScoresService userScoresService;

	@ApiOperation(value = "新增会员积分明细积分明细", httpMethod = "POST", notes = "新增会员积分明细信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserScores userScores) {
		try {
			return userScoresService.save(userScores);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员积分明细")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		List<UserScores> userScores = userScoresService.getByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("userScores", userScores);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员积分明细信息查询", result);

	}
}
