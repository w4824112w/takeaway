package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.dto.UserRanksDto;
import com.takeaway.modular.dao.dto.UserScoresDto;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.UserScores;
import com.takeaway.modular.service.UserRanksService;

/**
 * 会员等级信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/user_ranks")
@Api(value = "会员等级管理", description = "UserRanksController")
public class UserRanksController {
	private static final Logger log = Logger
			.getLogger(UserRanksController.class);

	@Autowired
	private UserRanksService userRanksService;

	@ApiOperation(value = "新增会员等级等级", httpMethod = "POST", notes = "新增会员等级信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserRanks userRanks) {
		try {
			return userRanksService.save(userRanks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}
	
	@ApiOperation(value = "批量新增会员等级", httpMethod = "POST", notes = "批量新增会员等级信息")
	@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
	public JSONObject batchSave(HttpServletRequest request,
			HttpServletResponse response, @RequestBody List<UserRanks> userRanks) {
		try {
			return userRanksService.batchSave(userRanks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "批量修改会员等级", httpMethod = "POST", notes = "批量修改会员等级信息")
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	public JSONObject batchUpdate(HttpServletRequest request,
			HttpServletResponse response, @RequestBody List<UserRanks> userRanks) {
		try {
			return userRanksService.batchUpdate(userRanks);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取所有会员等级")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		List<UserRanks> userRanks = userRanksService.getAll();
		JSONObject result = new JSONObject();
		result.put("userRanks", userRanks);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "会员等级信息查询", result);

	}

	@ApiOperation(value = "编辑会员等级等级", httpMethod = "GET", notes = "编辑会员等级")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "会员等级id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		UserRanks userRanks = userRanksService.getById(id);

		JSONObject result = new JSONObject();
		result.put("userRanks", userRanks);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑会员等级", result);
	}
	
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新会员等级")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			@ApiParam @RequestBody UserRanks userRanks) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}
		try {
			
			return userRanksService.update(userRanks);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}
	
	@ApiOperation(value = "列表", httpMethod = "GET", notes = "分页查询会员等级")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		UserRanksDto dto = new UserRanksDto();
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<UserRanks> userRanks = userRanksService
				.findPage(bounds, dto);
		
		JSONObject result = new JSONObject();
		result.put("page", userRanks.getPaginator().getPage());
		result.put("rows", userRanks.getPaginator().getLimit());
		result.put("totalCount", userRanks.getPaginator().getTotalCount());
		result.put("userRanks", userRanks.getPageList());

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "分页查询会员等级", result);

	}
}
