package com.takeaway.modular.controller.api;

import java.util.List;

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
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.websocket.WebSocketServer;
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
		List<Orders> orders = ordersService.getAllByUserId(userId);	// 所有订单
		List<Orders> payOrders = ordersService.getAllByPay(userId);	// 待付款
		List<Orders> shipOrders = ordersService.getAllByShip(userId);	// 待收货
		List<Orders> appraisesOrders = ordersService.getAllByAppraises(userId);	// 待收货
		List<Orders> refundOrders = ordersService.getAllByRefund(userId);	// 退款/售后
		JSONObject result = new JSONObject();
		result.put("orders", orders);	
		result.put("payOrders", payOrders);	
		result.put("shipOrders", shipOrders);	
		result.put("appraisesOrders", appraisesOrders);	
		result.put("refundOrders", refundOrders);	

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商户店铺信息查询", result);

	}

	@ApiOperation(value = "详情", httpMethod = "GET", notes = "订单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public JSONObject details(HttpServletRequest request,
			HttpServletResponse response, String id) {

		Orders orders = ordersService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orders);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}
	
	@ApiOperation(value = "下单", httpMethod = "GET", notes = "新增会员订单信息")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response,@RequestBody Orders orders) {
		try {
			JSONObject result= ordersService.save(orders);
			WebSocketServer.sendInfo(result.toJSONString());
			return result;
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}
	
}
