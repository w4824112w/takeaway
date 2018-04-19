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
import com.takeaway.modular.dao.dto.CouponGrantRulesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.CouponGrantRules;
import com.takeaway.modular.service.CouponGrantRulesService;

*//**
 * 代金卷发放规则管理
 * 
 * @author Administrator
 *
 *//*
@RestController
@RequestMapping("/coupon_grant_rules")
@Api(value = "代金卷发放规则管理", description = "CouponGrantRulesController")
public class CouponGrantRulesController {
	private static final Logger log = Logger.getLogger(CouponGrantRulesController.class);

	@Autowired
	private CouponGrantRulesService couponGrantRulesService;

	*//**
	 * 分页查询代金卷发放规则
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询代金卷发放规则信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "代金卷发放规则名称", required = false, dataType = "String", paramType = "query") })
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

		CouponGrantRulesDto dto = new CouponGrantRulesDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<CouponGrantRules> couponGrantRules = couponGrantRulesService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", couponGrantRules.getPaginator().getPage());
		result.put("rows", couponGrantRules.getPaginator().getLimit());
		result.put("totalCount", couponGrantRules.getPaginator().getTotalCount());
		result.put("couponGrantRules", couponGrantRules.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 代金卷发放规则列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询代金卷发放规则列表", httpMethod = "GET", notes = "查询所有代金卷发放规则列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<CouponGrantRules> couponGrantRules = couponGrantRulesService.getAll();

		JSONObject result = new JSONObject();
		result.put("couponGrantRules", couponGrantRules);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 编辑代金卷发放规则
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑代金卷发放规则信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "代金卷发放规则id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		CouponGrantRules couponGrantRules = couponGrantRulesService.getById(id);

		JSONObject result = new JSONObject();
		result.put("couponGrantRules", couponGrantRules);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑代金卷发放规则", result);
	}

	*//**
	 * 新增代金卷发放规则
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增代金卷发放规则信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sendType", value = "代金卷发放规则类型id", required = true, dataType = "Integer", paramType = "form"),
		@ApiImplicitParam(name = "name", value = "代金卷发放规则名称", required = true, dataType = "String", paramType = "form"),
		@ApiImplicitParam(name = "content", value = "代金卷发放规则内容", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer sendType, String name,
			String content, Integer[] coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponGrantRules couponGrantRules = new CouponGrantRules();
			couponGrantRules.setSendType(sendType);
			couponGrantRules.setName(name);
			couponGrantRules.setContent(content);
			couponGrantRules.setStatus(1);
			return couponGrantRulesService.save(couponGrantRules, coupons);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	*//**
	 * 更新代金卷发放规则
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新代金卷发放规则信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "代金卷发放规则id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "sendType", value = "代金卷发放规则类型id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "代金卷发放规则名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "content", value = "代金卷发放规则内容", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, Integer sendType, String name,
			String content, Integer[] coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponGrantRules couponGrantRules = couponGrantRulesService.getById(id);
			couponGrantRules.setSendType(sendType);
			couponGrantRules.setName(name);
			couponGrantRules.setContent(content);
			couponGrantRules.setStatus(1);
			return couponGrantRulesService.update(couponGrantRules, coupons);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	*//**
	 * 删除代金卷发放规则
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除代金卷发放规则信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "代金卷发放规则id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = couponGrantRulesService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除代金卷发放规则", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除代金卷发放规则", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除代金卷发放规则", null);
		}

	}

}
*/