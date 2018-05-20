package com.takeaway.modular.controller.web;

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
import com.takeaway.modular.dao.dto.ItemMerchantsDto;
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
@RequestMapping("/advertisements")
@Api(value = "广告管理", description = "AdvertisementsController")
public class AdvertisementsController {
	private static final Logger log = Logger.getLogger(AdvertisementsController.class);

	@Autowired
	private AdvertisementsService advertisementsService;

	/**
	 * 分页查询广告
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询广告信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query")
			 })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		AdvertisementsDto dto = new AdvertisementsDto();

		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Advertisements> advertisements = advertisementsService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("page", advertisements.getPaginator().getPage());
		result.put("rows", advertisements.getPaginator().getLimit());
		result.put("totalCount", advertisements.getPaginator().getTotalCount());
		result.put("advertisements", advertisements.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

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
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<Advertisements> advertisements = advertisementsService.getAll();

		JSONObject result = new JSONObject();
		result.put("advertisements", advertisements);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑广告
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑广告信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "广告id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Advertisements advertisements = advertisementsService.getById(id);

		JSONObject result = new JSONObject();
		result.put("advertisements", advertisements);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑广告", result);
	}



	/**
	 * 新增广告
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增广告信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Advertisements advertisements) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			return advertisementsService.save(advertisements);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新广告信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Advertisements advertisements) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			return advertisementsService.update(advertisements);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "广告上架or下架广告信息")
	@RequestMapping(value = "/updateIsPuton", method = RequestMethod.POST)
	public JSONObject updateIsPuton(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Advertisements advertisements) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			return advertisementsService.update(advertisements);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}
	
	/**
	 * 删除广告
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除广告信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Advertisements advertisements) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = advertisementsService.delete(advertisements.getId().toString());
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除广告", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除广告", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除广告", null);
		}

	}

}
