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
import com.takeaway.commons.page.Order;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.RolesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Menus;
import com.takeaway.modular.dao.model.Roles;
import com.takeaway.modular.service.MenusService;
import com.takeaway.modular.service.RolesService;

/**
 * 系统角色管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/roles")
@Api(value = "角色管理", description = "RolesController")
public class RolesController {
	private static final Logger log = Logger.getLogger(RolesController.class);

	@Autowired
	private RolesService rolesService;

	@Autowired
	private MenusService menusService;

	/**
	 * 分页查询角色
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询角色信息")
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

		RolesDto dto = new RolesDto();
		dto.setName(name);

		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Roles> roles = rolesService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", roles.getPaginator().getPage());
		result.put("rows", roles.getPaginator().getLimit());
		result.put("totalCount", roles.getPaginator().getTotalCount());
		result.put("roles", roles.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 角色列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询角色列表", httpMethod = "GET", notes = "查询所有角色列表信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<Roles> roles = rolesService.getAll();

		JSONObject result = new JSONObject();
		result.put("roles", roles);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑角色
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑角色信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Roles roles = rolesService.getById(id);

		JSONObject result = new JSONObject();
		result.put("roles", roles);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑角色", result);
	}

	/**
	 * 角色权限编辑
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "角色权限编辑")
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/menuSetup", method = RequestMethod.GET)
	public JSONObject menuSetup(HttpServletRequest request,
			HttpServletResponse response, String roleId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		// 获取系统所有菜单权限
		List<Menus> allList = menusService.getAll();
		// 获取用户已分配的菜单权限
		List<Menus> ownList = menusService.getByRoleId(roleId);

		JSONObject result = new JSONObject();
		result.put("allList", allList);
		result.put("ownList", ownList);
		result.put("roleId", roleId);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "角色权限编辑", result);
	}

	/**
	 * 保存角色权限
	 * 
	 * @param menuIds
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "保存", httpMethod = "POST", notes = "保存角色权限")
	@RequestMapping(value = "/saveRoleMenuSetup", method = RequestMethod.POST)
	public JSONObject saveRoleMenuSetup(String[] menuIds, String roleId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		if (menuIds == null || menuIds.length < 1) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "未选择添加的菜单项", null);
		}

		if (rolesService.saveRoleMenu(menuIds, roleId)) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "保存角色权限", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "保存角色权限", null);
		}

	}

	/**
	 * 新增角色
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增角色信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "角色名称", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			Roles role = new Roles();
			role.setName(name);
			role.setStatus(1);
			return rolesService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新角色信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "角色名称", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			Roles role = rolesService.getById(id);
			role.setName(name);
			return rolesService.update(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除角色信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = rolesService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除角色", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除角色", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除角色", null);
		}

	}

}
