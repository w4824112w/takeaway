package com.takeaway.modular.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.Order;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.AdvertisementsDto;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Menus;
import com.takeaway.modular.dao.model.Advertisements;
import com.takeaway.modular.service.AdvertisementsService;
import com.takeaway.modular.service.MenusService;
import com.takeaway.modular.service.AdvertisementsService;

/**
 * 系统广告管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/advertisements")
@Api(value = "首页广告信息---小程序接口", description = "AdvertisementsApiController")
public class AdvertisementsApiController {
	private static final Logger log = Logger.getLogger(AdvertisementsApiController.class);

	@Autowired
	private AdvertisementsService advertisementsService;

	/**
	 * 广告列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询广告列表", httpMethod = "GET", notes = "查询所有广告列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {

		List<Advertisements> advertisements = advertisementsService.getAll();

		JSONObject result = new JSONObject();
		result.put("advertisements", advertisements);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}





}
