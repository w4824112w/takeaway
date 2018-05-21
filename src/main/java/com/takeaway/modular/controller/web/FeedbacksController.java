package com.takeaway.modular.controller.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.FeedbacksDto;
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.FeedbacksService;

/**
 * 会员订单评价信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/feedbacks")
@Api(value = "会员订单评价管理", description = "FeedbacksController")
public class FeedbacksController {
	private static final Logger log = Logger
			.getLogger(FeedbacksController.class);

	@Autowired
	private FeedbacksService feedbacksService;

	@ApiOperation(value = "新增会员订单评价", httpMethod = "POST", notes = "新增会员订单评价信息")
/*	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "orderId", value = "订单id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "merchantId", value = "店铺商户id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "itemId", value = "商品id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "goodsScore", value = "商品评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "foodScore", value = "食物评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "distributionScore", value = "配送评分(1~5)", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "content", value = "收货人地址", required = false, dataType = "String", paramType = "form") })*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response,@ApiParam @RequestBody Feedbacks feedbacks) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		try {
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
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<Feedbacks> feedbacks = feedbacksService.getByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("feedbacks", feedbacks);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员订单评价信息查询", result);

	}
	
	
	@ApiOperation(value = "店铺查询", httpMethod = "GET", notes = "获取自己所在店铺订单评价")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		
		PageBounds bounds = new PageBounds(page, rows);
		FeedbacksDto dto=new FeedbacksDto();
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		PageResult<FeedbacksDto> feedbacks = feedbacksService.findPage(bounds,dto);
		JSONObject result = new JSONObject();
		result.put("merchantId", merchantId);
		result.put("page", feedbacks.getPaginator().getPage());
		result.put("rows", feedbacks.getPaginator().getLimit());
		result.put("totalCount", feedbacks.getPaginator().getTotalCount());
		result.put("feedbacks", feedbacks.getPageList());

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员订单评价信息查询", result);

	}
	
	
}
