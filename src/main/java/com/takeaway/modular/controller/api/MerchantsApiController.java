package com.takeaway.modular.controller.api;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.MerchantsDto;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.UserLogs;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.ActivitysService;
import com.takeaway.modular.service.CouponsService;
import com.takeaway.modular.service.FeedbacksService;
import com.takeaway.modular.service.MerchantsService;
import com.takeaway.modular.service.UserLogsService;
import com.takeaway.modular.service.UsersService;

/**
 * 会员信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/merchants")
@Api(value = "店铺信息---小程序接口", description = "MerchantsApiController")
public class MerchantsApiController {
	private static final Logger log = Logger
			.getLogger(MerchantsApiController.class);

	@Autowired
	private MerchantsService merchantsService;

	@Autowired
	private CouponsService couponsService;

	@Autowired
	private ActivitysService activitysService;

	@Autowired
	private FeedbacksService feedbacksService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UserLogsService userLogsService;

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取商户店铺")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "lat", value = "经度", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String lat,String lng,String openid) {
		Users user=usersService.getByOpenid(openid);
		List<MerchantsDto> merchants = merchantsService.getAllByUserId(lat,lng,user.getId().toString());
		JSONObject result = new JSONObject();
		result.put("merchants", merchants);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商户店铺信息查询", result);

	}

	@ApiOperation(value = "详情", httpMethod = "GET", notes = "商户信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "id", value = "商户id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public JSONObject details(HttpServletRequest request,
			HttpServletResponse response, String openid,String id) {
		Merchants merchants = merchantsService.getById(id);
		
		Users user=usersService.getByOpenid(openid);
		UserLogs userLogs = new UserLogs();
		userLogs.setLoginfo("会员app登录");
		userLogs.setCreatedAt(new Date());
		userLogs.setUserId(user.getId());
		userLogs.setMerchantId(merchants.getId());
		userLogsService.save(userLogs);
		
		String merchantId = merchants.getId().toString();
		/*List<Feedbacks> feedbacks = feedbacksService
				.getByMerchantId(merchantId);*/
		List<Coupons> coupons = couponsService.getByMerchantId(merchantId);
		List<Activitys> activitys = activitysService
				.getByMerchantId(merchantId);
		JSONObject result = new JSONObject();
		result.put("merchants", merchants);
	//	result.put("feedbacks", feedbacks);
		result.put("coupons", coupons);
		result.put("activitys", activitys);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑商户", result);
	}

}
