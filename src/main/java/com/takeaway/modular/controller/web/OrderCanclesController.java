package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.Order;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrderCanclesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.service.OrderCanclesService;

/**
 * 订单退单管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order_cancles")
@Api(value = "订单退单管理", description = "OrderCanclesController")
public class OrderCanclesController {
	private static final Logger log = Logger
			.getLogger(OrderCanclesController.class);

	@Autowired
	private OrderCanclesService orderCanclesService;

	/**
	 * 分页查询订单退单
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询订单退单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")})
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

		OrderCanclesDto dto = new OrderCanclesDto();
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<OrderCancles> orderCancles = orderCanclesService.findPage(
				bounds, dto);

		JSONObject result = new JSONObject();
		result.put("page", orderCancles.getPaginator().getPage());
		result.put("rows", orderCancles.getPaginator().getLimit());
		result.put("totalCount", orderCancles.getPaginator().getTotalCount());
		result.put("orders", orderCancles.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑订单退单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑订单退单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单退单id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		OrderCancles orderCancles = orderCanclesService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orderCancles);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}

	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增订单退单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "size", value = "数量", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "下单人名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "totalPrice", value = "订单总金额", required = true, dataType = "Double", paramType = "form")
			})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer orderId, Integer size,
			String name, Double totalPrice) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			OrderCancles orderCancles = new OrderCancles();
			orderCancles.setOrderId(orderId);
			orderCancles.setSize(size);
			orderCancles.setName(name);
			orderCancles.setTotalPrice(totalPrice);
			return orderCanclesService.save(orderCancles);
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}


}
