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
import com.takeaway.modular.dao.dto.ItemMerchantsDto;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.service.ItemsService;

/**
 * 商品管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/items")
@Api(value = "商品管理", description = "ItemsController")
public class ItemsController {
	private static final Logger log = Logger.getLogger(ItemsController.class);

	@Autowired
	private ItemsService itemsService;

	/**
	 * 分页查询商品
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "商户id", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "商品名称", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String merchantId, String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		ItemsDto dto = new ItemsDto();
		dto.setMerchantId(merchantId);
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Items> items = itemsService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("merchantId", merchantId);
		result.put("name", name);
		result.put("page", items.getPaginator().getPage());
		result.put("rows", items.getPaginator().getLimit());
		result.put("totalCount", items.getPaginator().getTotalCount());
		result.put("items", items.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 商品列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询商品列表", httpMethod = "GET", notes = "查询所有商品列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		List<Items> items = itemsService.getAll();

		JSONObject result = new JSONObject();
		result.put("items", items);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑商品信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		Items items = itemsService.getById(id);

		JSONObject result = new JSONObject();
		result.put("items", items);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑商品", result);
	}

	/**
	 * 新增商品
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	/*
	 * @ApiOperation(value = "新增", httpMethod = "POST", notes = "新增商品信息")
	 * 
	 * @ApiImplicitParams({
	 * 
	 * @ApiImplicitParam(name = "itemType", value = "商品类型id", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "name", value = "商品名称", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "code", value = "商品编号", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "label", value = "商品标签", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "originPrice", value = "原价", required = true,
	 * dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "costPrice", value = "成本价", required = true,
	 * dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "price", value = "销售价", required = true,
	 * dataType = "Double", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "unit", value = "商品单位", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "salesVolume", value = "销售量", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "tips", value = "提示", required = true, dataType
	 * = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "remain", value = "备注", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "stock", value = "库存", required = true, dataType
	 * = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "stockStatus", value = "库存状态", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "description", value = "描述", required = true,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "cityId", value = "城市id", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "isPuton", value = "是否上架", required = true,
	 * dataType = "Integer", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "origin", value = "销售来源", required = false,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "merchants", value = "店铺id数组", required = false,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "propertys", value = "属性id数组", required = false,
	 * dataType = "String", paramType = "form"),
	 * 
	 * @ApiImplicitParam(name = "pictures", value = "图片id数组", required = false,
	 * dataType = "String", paramType = "form") })
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public
	 * JSONObject save(HttpServletRequest request, HttpServletResponse response,
	 * Integer itemType, String name, String code, String label, Double
	 * originPrice, Double costPrice, Double price, String unit, Integer
	 * salesVolume, String tips, String remain, Integer stock, Integer
	 * stockStatus, String description, Integer cityId, Integer isPuton, String
	 * origin, Integer[] merchants, Integer[] propertys, String[] pictures) {
	 */
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增商品信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			@ApiParam @RequestBody Items items) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			/*Items items = new Items();
			items.setItemType(itemType);
			items.setName(name);
			items.setCode(code);
			items.setLabel(label);
			items.setOriginPrice(originPrice);
			items.setCostPrice(costPrice);
			items.setPrice(price);
			items.setUnit(unit);
			items.setSalesVolume(salesVolume);
			items.setTips(tips);
			items.setRemain(remain);
			items.setStock(stock);
			items.setStockStatus(stockStatus);
			items.setDescription(description);
			items.setCityId(cityId);
			items.setOrigin(origin);*/
			return itemsService.save(items);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	/**
	 * 更新商品
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	/*	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "itemType", value = "商品类型id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "code", value = "商品编号", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "label", value = "商品标签", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "originPrice", value = "原价", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "costPrice", value = "成本价", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "price", value = "销售价", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "unit", value = "商品单位", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "salesVolume", value = "销售量", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "tips", value = "提示", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "remain", value = "备注", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "stock", value = "库存", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "stockStatus", value = "库存状态", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "description", value = "描述", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "cityId", value = "城市id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "isPuton", value = "是否上架", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "origin", value = "销售来源", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "merchants", value = "店铺id数组", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "propertys", value = "属性id数组", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "pictures", value = "图片id数组", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, Integer itemType,
			String name, String code, String label, Double originPrice,
			Double costPrice, Double price, String unit, Integer salesVolume,
			String tips, String remain, Integer stock, Integer stockStatus,
			String description, Integer cityId, Integer isPuton, String origin,
			Integer[] merchants, Integer[] propertys, String[] pictures) {*/
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新商品信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			@ApiParam @RequestBody Items items) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
		/*	Items items = itemsService.getById(id);
			items.setItemType(itemType);
			items.setName(name);
			items.setCode(code);
			items.setLabel(label);
			items.setOriginPrice(originPrice);
			items.setCostPrice(costPrice);
			items.setPrice(price);
			items.setUnit(unit);
			items.setSalesVolume(salesVolume);
			items.setTips(tips);
			items.setRemain(remain);
			items.setStock(stock);
			items.setStockStatus(stockStatus);
			items.setDescription(description);
			items.setCityId(cityId);
			items.setOrigin(origin);*/
			
			return itemsService.update(items);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "商户店铺上架or下架商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "merchantId", value = "商户id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "isPuton", value = "是否上架(0：下架;1：上架;)", required = true, dataType = "Integer", paramType = "form") })
	@RequestMapping(value = "/updateIsPuton", method = RequestMethod.POST)
	public JSONObject updateIsPuton(HttpServletRequest request,
			HttpServletResponse response, String itemId, String merchantId,
			String isPuton) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			ItemMerchantsDto dto = new ItemMerchantsDto();
			dto.setItemId(itemId);
			dto.setMerchantId(merchantId);
			dto.setIsPuton(isPuton);
			return itemsService.updateIsPuton(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "超级管理员上架or下架商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "isPuton", value = "是否上架(0：下架;1：上架;)", required = true, dataType = "Integer", paramType = "form") })
	@RequestMapping(value = "/superUpdate", method = RequestMethod.POST)
	public JSONObject superUpdate(HttpServletRequest request,
			HttpServletResponse response, String itemId, String isPuton) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			return itemsService.superUpdate(itemId, isPuton);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除商品信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "String", paramType = "form") })
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
			int result = itemsService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除商品", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商品", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商品", null);
		}

	}

}
