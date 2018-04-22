package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.MenusDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Menus;
import com.takeaway.modular.service.MenusService;

/**
 * 系统菜单管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/menus")
@Api(value = "菜单管理", description = "MenusController")
public class MenusController {
	private static final Logger log = Logger.getLogger(MenusController.class);

	@Autowired
	private MenusService menusService;

	/**
	 * 分页查询菜单
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询菜单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "菜单名称", required = false, dataType = "String", paramType = "query") })
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

		MenusDto dto = new MenusDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Menus> menus = menusService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", menus.getPaginator().getPage());
		result.put("rows", menus.getPaginator().getLimit());
		result.put("totalCount", menus.getPaginator().getTotalCount());
		result.put("menus", menus.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 一级菜单菜单列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询一级菜单", httpMethod = "GET", notes = "查询所有一级菜单信息")
	@RequestMapping(value = "/oneLevelList", method = RequestMethod.GET)
	public JSONObject oneLevelList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<Menus> menus = menusService.getOneLevelMenu();

		JSONObject result = new JSONObject();
		result.put("menus", menus);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 菜单列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询菜单列表", httpMethod = "GET", notes = "查询所有菜单列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<Menus> menus = menusService.getAll();

		JSONObject result = new JSONObject();
		result.put("menus", menus);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑菜单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑菜单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Menus menus = menusService.getById(id);

		JSONObject result = new JSONObject();
		result.put("menus", menus);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑菜单", result);
	}

	/**
	 * 新增菜单
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
/*	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增菜单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "菜单名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "level", value = "菜单等级", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "url", value = "菜单路径", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "pid", value = "父级菜单id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "pidName", value = "父级菜单名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "menuOrder", value = "菜单排序", required = false, dataType = "Integer", paramType = "form")
			})*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name, String level,
			String url, Integer pid, String pidName,Integer menuOrder) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			Menus menu = new Menus();
			menu.setName(name);
			menu.setLevel(level);
			menu.setUrl(url);
			menu.setPid(pid);
			menu.setPidName(pidName);
			menu.setMenuOrder(menuOrder);
			menu.setStatus(1);
			return menusService.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	/**
	 * 更新菜单
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
/*	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新菜单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "菜单名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "level", value = "菜单等级", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "url", value = "菜单路径", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "pid", value = "父级菜单id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "pidName", value = "父级菜单名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "menuOrder", value = "菜单排序", required = false, dataType = "Integer", paramType = "form")
	})*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name, String level,
			String url, Integer pid, String pidName,Integer menuOrder) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			Menus menu = menusService.getById(id);
			menu.setName(name);
			menu.setLevel(level);
			menu.setUrl(url);
			menu.setPid(pid);
			menu.setPidName(pidName);
			menu.setMenuOrder(menuOrder);
			menu.setStatus(1);
			return menusService.update(menu);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除菜单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
/*	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除菜单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String", paramType = "form") })*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = menusService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除菜单", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除菜单", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除菜单", null);
		}

	}

}
