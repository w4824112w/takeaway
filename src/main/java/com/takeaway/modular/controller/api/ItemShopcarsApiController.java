/*package com.takeaway.modular.controller.api;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.model.ItemShopcars;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ItemShopcarsService;

*//**
 * 购物车商品信息接口
 * 
 * @author hk
 *
 *//*
@RestController
@RequestMapping("/api/item_shopcars")
@Api(value = "购物车商品信息---小程序接口", description = "ItemShopcarsApiController")
public class ItemShopcarsApiController {
	private static final Logger log = Logger
			.getLogger(ItemShopcarsApiController.class);

	@Autowired
	private ItemShopcarsService itemShopcarsService;

	@ApiOperation(value = "新增购物车商品", httpMethod = "POST", notes = "新增购物车商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "会员id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "itemPropertyId", value = "商品属性id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "num", value = "商品数量", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "batchNo", value = "批次号", required = false, dataType = "String", paramType = "form")
			})
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer userId, Integer itemId,
			Integer itemPropertyId, Integer num, String batchNo,
			String[] itemPropertyIds) {
		try {
			ItemShopcars itemShopcars = new ItemShopcars();
			itemShopcars.setUserId(userId);
			itemShopcars.setItemId(itemId);
			itemShopcars.setItemPropertyId(itemPropertyId);
			itemShopcars.setNum(num);
			itemShopcars.setBatchNo(batchNo);
			return itemShopcarsService.save(itemShopcars,itemPropertyIds);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取购物车商品列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "batchNo", value = "购物车批次id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String batchNo) {
		List<ItemShopcars> itemShopcars = itemShopcarsService
				.getByBatchNo(batchNo);
		JSONObject result = new JSONObject();
		result.put("batchNo", batchNo);
		result.put("itemShopcars", itemShopcars);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "购物车商品信息查询", result);

	}
	
	*//**
	 * 删除购物车单个商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除购物车单个商品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "购物车id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		try {
			int result = itemShopcarsService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除购物车商品", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除购物车商品", null);
			}
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除购物车商品", null);
		}

	}
	
	*//**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 *//*
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "根据批次号清空购物车")
	@ApiImplicitParams({ @ApiImplicitParam(name = "batchNo", value = "批次号", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	public JSONObject batchDel(HttpServletRequest request,
			HttpServletResponse response, String batchNo) {
		try {
			int result = itemShopcarsService.delete(batchNo);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "清空购物车", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "清空购物车", null);
			}
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "清空购物车", null);
		}

	}
}
*/