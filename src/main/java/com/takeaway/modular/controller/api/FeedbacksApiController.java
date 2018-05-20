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
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.FeedbacksService;
import com.takeaway.modular.service.UsersService;

/**
 * 订单评价信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/feedbacks")
@Api(value = "订单评价信息---小程序接口", description = "FeedbacksApiController")
public class FeedbacksApiController {
	private static final Logger log = Logger
			.getLogger(FeedbacksApiController.class);

	@Autowired
	private FeedbacksService feedbacksService;
	
	@Autowired
	private UsersService usersService;

	@ApiOperation(value = "新增会员订单评价", httpMethod = "POST", notes = "新增会员订单评价信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Feedbacks feedbacks) {
		try {
			return feedbacksService.save(feedbacks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员订单评价")
	@ApiImplicitParams({ @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/userFeedbacks", method = RequestMethod.GET)
	public JSONObject userFeedbacks(HttpServletRequest request,
			HttpServletResponse response, String openid) {
		Users user=usersService.getByOpenid(openid);
		List<Feedbacks> feedbacks = feedbacksService.getByUserId(user.getId().toString());
		JSONObject result = new JSONObject();
		result.put("feedbacks", feedbacks);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员订单评价信息查询", result);

	}
	
	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取店铺订单评价")
	@ApiImplicitParams({ @ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/merchantFeedbacks", method = RequestMethod.GET)
	public JSONObject merchantFeedbacks(HttpServletRequest request,
			HttpServletResponse response,String merchantId) {
		List<Feedbacks> feedbacks = feedbacksService.getByMerchantId(merchantId);
		JSONObject result = new JSONObject();
		result.put("feedbacks", feedbacks);
		
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "店铺订单评价信息查询", result);
	}
}
