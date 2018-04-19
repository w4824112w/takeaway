package com.takeaway.modular.controller.api;

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
import com.takeaway.modular.dao.dto.OrdersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.UsersService;

/**
 * 会员信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/orders")
@Api(value = "我的订单信息---小程序接口", description = "OrdersApiController")
public class OrdersApiController {
	private static final Logger log = Logger
			.getLogger(OrdersApiController.class);

	@Autowired
	private OrdersService ordersService;

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取用户所有订单")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		List<Orders> orders = ordersService.getAllByUserId(userId);
		JSONObject result = new JSONObject();
		result.put("orders", orders);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商户店铺信息查询", result);

	}

	@ApiOperation(value = "详情", httpMethod = "GET", notes = "订单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public JSONObject details(HttpServletRequest request,
			HttpServletResponse response, String id) {

		Orders orders = ordersService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orders);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}
}
