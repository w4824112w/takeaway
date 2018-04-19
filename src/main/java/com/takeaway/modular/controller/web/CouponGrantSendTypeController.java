/*package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

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
import com.takeaway.modular.dao.dto.CouponGrantSendTypeDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.CouponGrantSendType;
import com.takeaway.modular.service.CouponGrantSendTypeService;

*//**
 * 代金卷发放规则类型管理
 * 
 * @author Administrator
 *
 *//*
@RestController
@RequestMapping("/coupon_grant_send_type")
@Api(value = "代金卷发放规则类型管理", description = "CouponGrantSendTypeController")
public class CouponGrantSendTypeController {
	private static final Logger log = Logger
			.getLogger(CouponGrantSendTypeController.class);

	@Autowired
	private CouponGrantSendTypeService couponGrantSendTypeService;

	*//**
	 * 分页查询代金卷发放规则类型
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询代金卷发放规则类型信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "代金卷发放规则类型名称", required = false, dataType = "String", paramType = "query") })
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

		CouponGrantSendTypeDto dto = new CouponGrantSendTypeDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<CouponGrantSendType> couponGrantSendType = couponGrantSendTypeService
				.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", couponGrantSendType.getPaginator().getPage());
		result.put("rows", couponGrantSendType.getPaginator().getLimit());
		result.put("totalCount", couponGrantSendType.getPaginator().getTotalCount());
		result.put("couponGrantSendType", couponGrantSendType.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 代金卷发放规则类型列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 *//*
	@ApiOperation(value = "查询代金卷发放规则类型列表", httpMethod = "GET", notes = "查询所有代金卷发放规则类型列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<CouponGrantSendType> couponGrantSendType = couponGrantSendTypeService.getAll();

		JSONObject result = new JSONObject();
		result.put("couponGrantSendType", couponGrantSendType);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	*//**
	 * 编辑代金卷发放规则类型
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑代金卷发放规则类型信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "代金卷发放规则类型id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		CouponGrantSendType couponGrantSendType = couponGrantSendTypeService.getById(id);

		JSONObject result = new JSONObject();
		result.put("couponGrantSendType", couponGrantSendType);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑代金卷发放规则类型", result);
	}

	*//**
	 * 新增代金卷发放规则类型
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增代金卷发放规则类型信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "代金卷发放规则类型名称", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponGrantSendType couponGrantSendType = new CouponGrantSendType();
			couponGrantSendType.setName(name);
			return couponGrantSendTypeService.save(couponGrantSendType);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	*//**
	 * 更新代金卷发放规则类型
	 * 
	 * @param o
	 * @param r
	 * @return
	 *//*
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新代金卷发放规则类型信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "代金卷发放规则类型id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "代金卷发放规则类型名称", required = true, dataType = "String", paramType = "form")
			})
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			CouponGrantSendType merchants = couponGrantSendTypeService.getById(id);
			merchants.setName(name);
			return couponGrantSendTypeService.update(merchants);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	*//**
	 * 删除代金卷发放规则类型
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除代金卷发放规则类型信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "代金卷发放规则类型id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = couponGrantSendTypeService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除代金卷发放规则类型", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除代金卷发放规则类型", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除代金卷发放规则类型", null);
		}

	}

}
*/