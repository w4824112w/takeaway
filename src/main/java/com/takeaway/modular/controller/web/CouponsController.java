package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.service.CouponsService;

/**
 * 优惠卷卷管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/coupons")
@Api(value = "优惠卷管理", description = "CouponsController")
public class CouponsController {
	private static final Logger log = Logger.getLogger(CouponsController.class);

	@Autowired
	private CouponsService couponsService;

	/**
	 * 分页查询优惠卷
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询优惠卷信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "优惠卷名称", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		CouponsDto dto = new CouponsDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<CouponsDto> coupons = couponsService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", coupons.getPaginator().getPage());
		result.put("rows", coupons.getPaginator().getLimit());
		result.put("totalCount", coupons.getPaginator().getTotalCount());
		result.put("coupons", coupons.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 优惠卷列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询优惠卷列表", httpMethod = "GET", notes = "查询所有优惠卷列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		List<Coupons> coupons = couponsService.getAll();

		JSONObject result = new JSONObject();
		result.put("coupons", coupons);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	@ApiOperation(value = "查询优惠卷列表", httpMethod = "GET", notes = "查询后台送券的类型的优惠卷列表信息")
	@RequestMapping(value = "/backList", method = RequestMethod.GET)
	public JSONObject backList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		List<Coupons> coupons = couponsService.getBackAll();

		JSONObject result = new JSONObject();
		result.put("coupons", coupons);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}
	
	/**
	 * 编辑优惠卷
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑优惠卷信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "优惠卷id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		CouponsDto coupons = couponsService.getById(id);

		JSONObject result = new JSONObject();
		result.put("coupons", coupons);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑优惠卷", result);
	}

	/**
	 * 新增优惠卷
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增优惠卷信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Coupons coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			return couponsService.save(coupons);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	/**
	 * 更新优惠卷
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新优惠卷信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Coupons coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {

			return couponsService.update(coupons);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除优惠卷
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除优惠卷信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Coupons coupons) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		try {
			int result = couponsService.delete(coupons.getId().toString());
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除优惠卷", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除优惠卷", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除优惠卷", null);
		}

	}

}
