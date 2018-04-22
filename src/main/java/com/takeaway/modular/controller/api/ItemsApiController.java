package com.takeaway.modular.controller.api;

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
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantTypes;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.service.ItemTypesService;
import com.takeaway.modular.service.ItemsService;

/**
 * 商品管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/items")
@Api(value = "商品信息---小程序接口", description = "ItemsApiController")
public class ItemsApiController {
	private static final Logger log = Logger
			.getLogger(ItemsApiController.class);

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private ItemTypesService itemTypesService;

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
	@ApiImplicitParams({ @ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response,String merchantId) {
		
		List<Items> items = itemsService.getAllByMerchantId(merchantId);

		JSONObject result = new JSONObject();
		result.put("items", items);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 商品类型列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询商品类型列表", httpMethod = "GET", notes = "查询所有商品类型列表信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public JSONObject types(HttpServletRequest request,
			HttpServletResponse response, String merchantId) {
		List<ItemTypes> itemTypes = itemTypesService
				.getAllByMerchantId(merchantId);

		JSONObject result = new JSONObject();
		result.put("itemTypes", itemTypes);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

}
