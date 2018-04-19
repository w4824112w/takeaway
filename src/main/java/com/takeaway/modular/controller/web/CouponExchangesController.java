/*package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
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
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CouponExchangesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.CouponExchanges;
import com.takeaway.modular.service.CouponExchangesService;

*//**
 * 优惠活动管理
 * 
 * @author Administrator
 *
 *//*
@RestController
@RequestMapping("/coupon_exchanges")
@Api(value = "优惠活动管理", description = "CouponExchangesController")
public class CouponExchangesController {
	private static final Logger log = Logger
			.getLogger(CouponExchangesController.class);

	@Autowired
	private CouponExchangesService couponExchangesService;

	*//**
	 * 分页查询优惠活动
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询优惠活动信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "优惠活动名称", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		CouponExchangesDto dto = new CouponExchangesDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<CouponExchangesDto> couponExchanges = couponExchangesService
				.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", couponExchanges.getPaginator().getPage());
		result.put("rows", couponExchanges.getPaginator().getLimit());
		result.put("totalCount", couponExchanges.getPaginator().getTotalCount());
		result.put("couponExchanges", couponExchanges.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 优惠活动列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询优惠活动列表", httpMethod = "GET", notes = "查询所有优惠活动列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<CouponExchanges> couponExchanges = couponExchangesService.getAll();

		JSONObject result = new JSONObject();
		result.put("couponExchanges", couponExchanges);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 编辑优惠活动
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑优惠活动信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "优惠活动id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		CouponExchanges couponExchanges = couponExchangesService.getById(id);

		JSONObject result = new JSONObject();
		result.put("couponExchanges", couponExchanges);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑优惠活动", result);
	}

	*//**
	 * 新增优惠活动
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增优惠活动信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "优惠活动编号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "startDate", value = "优惠活动开始时间", required = true, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "endDate", value = "优惠活动结束时间", required = true, dataType = "Date", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String code, Date startDate,
			Date endDate, Integer[] coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponExchanges couponExchanges = new CouponExchanges();
			couponExchanges.setCode(code);
			couponExchanges.setStartDate(startDate);
			couponExchanges.setEndDate(endDate);
			couponExchanges.setStatus(1);
			return couponExchangesService.save(couponExchanges, coupons);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	*//**
	 * 更新优惠活动
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新优惠活动信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "优惠活动id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "code", value = "优惠活动编号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "startDate", value = "优惠活动开始时间", required = true, dataType = "Date", paramType = "form"),
			@ApiImplicitParam(name = "endDate", value = "优惠活动结束时间", required = true, dataType = "Date", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String code,
			Date startDate, Date endDate, Integer[] coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponExchanges couponExchanges = couponExchangesService
					.getById(id);
			couponExchanges.setCode(code);
			couponExchanges.setStartDate(startDate);
			couponExchanges.setEndDate(endDate);
			couponExchanges.setStatus(1);
			return couponExchangesService.update(couponExchanges, coupons);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	*//**
	 * 删除优惠活动
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除优惠活动信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "优惠活动id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = couponExchangesService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除优惠活动", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除优惠活动", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除优惠活动", null);
		}

	}

}
*/