package com.takeaway.modular.controller.api;

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
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.service.FeedbacksService;

/**
 * 会员订单评价信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/feedbacks")
@Api(value = "会员订单评价信息---小程序接口", description = "FeedbacksApiController")
public class FeedbacksApiController {
	private static final Logger log = Logger
			.getLogger(FeedbacksApiController.class);

	@Autowired
	private FeedbacksService feedbacksService;

	@ApiOperation(value = "新增会员订单评价", httpMethod = "POST", notes = "新增会员订单评价信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "orderId", value = "订单id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "merchantId", value = "店铺商户id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "itemId", value = "商品id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "goodsScore", value = "商品评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "foodScore", value = "食物评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "distributionScore", value = "配送评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "content", value = "收货人地址", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer userId, Integer orderId,
			Integer merchantId, Integer itemId, Integer goodsScore,
			Integer foodScore, Integer distributionScore, String content) {
		try {
			Feedbacks feedbacks = new Feedbacks();
			feedbacks.setUserId(userId);
			feedbacks.setOrderId(orderId);
			feedbacks.setMerchantId(merchantId);
			feedbacks.setItemId(itemId);
			feedbacks.setGoodsScore(goodsScore);
			feedbacks.setFoodScore(foodScore);
			feedbacks.setDistributionScore(distributionScore);
			return feedbacksService.save(feedbacks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取会员订单评价")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "会员id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		List<Feedbacks> feedbacks = feedbacksService.getByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("feedbacks", feedbacks);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员订单评价信息查询", result);

	}
}
