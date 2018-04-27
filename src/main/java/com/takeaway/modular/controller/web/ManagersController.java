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
import com.takeaway.commons.page.Order;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ManagersService;

/**
 * 系统用户管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/managers")
@Api(value = "用户管理", description = "ManagersController")
public class ManagersController {
	private static final Logger log = Logger
			.getLogger(ManagersController.class);

	@Autowired
	private ManagersService managersService;

	/**
	 * 分页查询用户
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query") })
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

		ManagersDto dto = new ManagersDto();
		dto.setName(name);

		PageBounds bounds = new PageBounds(page, rows);
		PageResult<ManagersDto> managers = managersService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", managers.getPaginator().getPage());
		result.put("rows", managers.getPaginator().getLimit());
		result.put("totalCount", managers.getPaginator().getTotalCount());
		result.put("managers", managers.getPageList());
		log.info("调用登录接口结束......");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);

	}

	/**
	 * 编辑用户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Managers managers = managersService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("managers", managers);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}

	/**
	 * 新增用户
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
//	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增用户信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "name", value = "账户名称", required = false, dataType = "String", paramType = "form"),
//			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Integer", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name, Integer roleId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			Managers user = new Managers();
			user.setName(name);
			user.setPasswordHash("123456");
			user.setRoleId(roleId);
			user.setStatus(1);

			return managersService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	/**
	 * 更新用户
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
//	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新用户信息")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "form"),
//			@ApiImplicitParam(name = "name", value = "账户名称", required = false, dataType = "String", paramType = "form"),
//			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Integer", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name,Integer roleId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			Managers user = managersService.getById(id);
			user.setName(name);
			user.setRoleId(roleId);
			user.setStatus(1);

			return managersService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除用户信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response,@ApiParam @RequestBody Managers user) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = managersService.delete(user.getId().toString());
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除用户", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除用户", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除用户", null);
		}

	}

}
