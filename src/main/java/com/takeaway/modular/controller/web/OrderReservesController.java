package com.takeaway.modular.controller.web;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrderReservesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.OrderReserves;
import com.takeaway.modular.service.OrderReservesService;

/**
 * 订单预订单管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order_reserves")
@Api(value = "订单预订单管理", description = "OrderReservesController")
public class OrderReservesController {
	private static final Logger log = Logger
			.getLogger(OrderReservesController.class);

	@Autowired
	private OrderReservesService orderReservesService;

	/**
	 * 分页查询订单预订单
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询订单预订单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "orderNo", value = "订单预订单编号", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String orderNo) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		OrderReservesDto dto = new OrderReservesDto();
		dto.setOrderNo(orderNo);
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<OrderReserves> orderReserves = orderReservesService.findPage(
				bounds, dto);

		JSONObject result = new JSONObject();
		result.put("orderNo", orderNo);
		result.put("page", orderReserves.getPaginator().getPage());
		result.put("rows", orderReserves.getPaginator().getLimit());
		result.put("totalCount", orderReserves.getPaginator().getTotalCount());
		result.put("orders", orderReserves.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑订单预订单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑订单预订单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单预订单id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		OrderReserves orderReserves = orderReservesService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orderReserves);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}

	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增订单预订单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "size", value = "数量", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "下单人名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "totalPrice", value = "订单总金额", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "startDeliveryTime", value = "预定开始时间", required = true, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "hopeDeliveryTime", value = "期望送达时间", required = true, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "limitDeliveryTime", value = "送达限制时间", required = true, dataType = "Date", paramType = "form"),
			})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer orderId, Integer size,
			String name, Double totalPrice,Date startDeliveryTime,Date hopeDeliveryTime,Date limitDeliveryTime) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			OrderReserves orderReserves = new OrderReserves();
			orderReserves.setOrderId(orderId);
			orderReserves.setSize(size);
			orderReserves.setName(name);
			orderReserves.setTotalPrice(totalPrice);
			orderReserves.setStartDeliveryTime(startDeliveryTime);
			orderReserves.setHopeDeliveryTime(hopeDeliveryTime);
			orderReserves.setLimitDeliveryTime(limitDeliveryTime);
			return orderReservesService.save(orderReserves);
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新订单预订单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "订单预订单id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "status", value = "订单预订单状态", required = false, dataType = "Integer", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, Integer status) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			OrderReserves orderReserves = orderReservesService.getById(id);
			return orderReservesService.update(orderReserves);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除订单预订单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单预订单id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = orderReservesService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除订单预订单", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除订单预订单", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除订单预订单", null);
		}

	}

}
