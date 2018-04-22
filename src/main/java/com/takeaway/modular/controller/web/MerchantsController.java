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
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.MerchantsDto;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.service.ManagersService;
import com.takeaway.modular.service.MerchantsService;

/**
 * 商户管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/merchants")
@Api(value = "商户管理", description = "MerchantsController")
public class MerchantsController {
	private static final Logger log = Logger
			.getLogger(MerchantsController.class);

	@Autowired
	private MerchantsService merchantsService;

	@Autowired
	private ManagersService managersService;

	/**
	 * 分页查询商户
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询商户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "商户名称", required = false, dataType = "String", paramType = "query") })
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

		MerchantsDto dto = new MerchantsDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<MerchantsDto> merchants = merchantsService.findPage(bounds,
				dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", merchants.getPaginator().getPage());
		result.put("rows", merchants.getPaginator().getLimit());
		result.put("totalCount", merchants.getPaginator().getTotalCount());
		result.put("merchants", merchants.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 商户列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询商户列表", httpMethod = "GET", notes = "查询所有商户列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		List<Merchants> merchants = merchantsService.getAll();

		JSONObject result = new JSONObject();
		result.put("merchants", merchants);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	@ApiOperation(value = "查询商品相关的商户列表", httpMethod = "GET", notes = "查询商品相关的商户列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/bindItemlist", method = RequestMethod.GET)
	public JSONObject bindItemlist(HttpServletRequest request,
			HttpServletResponse response, String itemId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		List<Merchants> merchants = merchantsService.getByItemId(itemId);

		JSONObject result = new JSONObject();
		result.put("merchants", merchants);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑商户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑商户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商户id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		Merchants merchants = merchantsService.getById(id);

		JSONObject result = new JSONObject();
		result.put("merchants", merchants);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑商户", result);
	}

	/**
	 * 新增商户
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增商户信息")
	/*
	 * @ApiImplicitParams({
	 * 
	 * @ApiImplicitParam(name = "accountName", value = "商户登录账号", required =
	 * true, dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "accountPassword", value = "商户登录密码", required =
	 * true, dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "typeId", value = "商户类型id", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "name", value = "商户名称", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "code", value = "商户编号", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "provinceId", value = "省份id", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "cityId", value = "城市id", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "address", value = "地址", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "star", value = "星级", required = true, dataType
	 * = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "managerName", value = "负责人姓名", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "managerPhone", value = "负责人姓名", required =
	 * true, dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "description", value = "店铺介绍", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "platformCommission", value = "平台佣金", required =
	 * true, dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "tel", value = "联系电话", required = true, dataType
	 * = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "lat", value = "经度", required = true, dataType =
	 * "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType =
	 * "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "logo", value = "logo图", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "notice", value = "店铺公告", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "startDate", value = "营业开始时间", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "endDate", value = "营业结束时间", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "distributionInfo", value = "配送信息", required =
	 * true, dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "startingPrice", value = "起送价", required = true,
	 * dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "fullFreeDistribution", value = "满多少免配送",
	 * required = true, dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "distributionFee", value = "配送费", required =
	 * true, dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "distributionScope", value = "配送范围", required =
	 * false, dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "isOnline", value = "是否上线", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "pictures", value = "商户图片地址数组", required =
	 * false, dataType = "String", paramType = "form") })
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response,
			@ApiParam @RequestBody Merchants merchants) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			return merchantsService.save(merchants);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	/**
	 * 更新商户
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新商户信息")
/*	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "商户id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "typeId", value = "商户类型id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "商户名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "code", value = "商户编号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "provinceId", value = "省份id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "cityId", value = "城市id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "star", value = "星级", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "managerName", value = "负责人姓名", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "managerPhone", value = "负责人姓名", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "description", value = "店铺介绍", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "platformCommission", value = "平台佣金", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "tel", value = "联系电话", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "lat", value = "经度", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "lng", value = "纬度", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "logo", value = "logo图", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "notice", value = "店铺公告", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "distributionInfo", value = "配送信息", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "startingPrice", value = "起送价", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "fullFreeDistribution", value = "满多少免配送", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "distributionFee", value = "配送费", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "distributionScope", value = "配送范围", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "isOnline", value = "是否上线", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "pictures", value = "商户图片地址数组", required = false, dataType = "String", paramType = "form") })*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Merchants merchants) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			
			return merchantsService.update(merchants);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除商户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除商户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商户id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		try {
			int result = merchantsService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除商户", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商户", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商户", null);
		}

	}

}
