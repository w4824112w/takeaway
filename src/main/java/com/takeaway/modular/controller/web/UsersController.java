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
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UsersService;

/**
 * app用户管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/users")
@Api(value = "app用户管理", description = "UsersController")
public class UsersController {
	private static final Logger log = Logger.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	/**
	 * 分页查询app用户
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询app用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "userPhone", value = "手机号码", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "userName", value = "用户名称", required = false, dataType = "String", paramType = "query")
			})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String userPhone,String userName) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		UsersDto dto = new UsersDto();
		dto.setUserPhone(userPhone);
		dto.setUserName(userName);
		
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<UsersDto> users = usersService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("userPhone", userPhone);
		result.put("userName", userName);
		result.put("page", users.getPaginator().getPage());
		result.put("rows", users.getPaginator().getLimit());
		result.put("totalCount", users.getPaginator().getTotalCount());
		result.put("users", users.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑app用户
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑app用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "app用户id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Users user = usersService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("users", user);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}

/*	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增app用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "账户名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "wxToken", value = "token", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "phone", value = "电话", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "realName", value = "真实姓名", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "address", value = "地址", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "avatar", value = "头像url", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "birthday", value = "生日", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "sex", value = "性别", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "ori", value = "注册来源", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name, String wxToken,
			String phone, String realName, String address, String avatar,
			String birthday, String sex, String ori) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "用户已超时，请退出登录", null);
		}
		
		try {
			Users user = new Users();
			user.setName(name);
			user.setWxToken(wxToken);
			user.setPhone(phone);
			user.setRealName(realName);
			user.setAddress(address);
			user.setAvatar(avatar);
			user.setBirthday(birthday);
			user.setSex(sex);
			user.setOri(ori);
			user.setGrade(0);
			user.setPoint(0);
			user.setStatus(1);
			return usersService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}*/

/*	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新app用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "app用户id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "账户名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "wxToken", value = "token", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "phone", value = "电话", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "realName", value = "真实姓名", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "address", value = "地址", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "avatar", value = "头像url", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "birthday", value = "生日", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "sex", value = "性别", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "ori", value = "注册来源", required = false, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name,
			String wxToken, String phone, String realName, String address,
			String avatar, String birthday, String sex, String ori) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "用户已超时，请退出登录", null);
		}
		
		try {
			Users user = usersService.getById(id);
			user.setName(name);
			user.setWxToken(wxToken);
			user.setPhone(phone);
			user.setRealName(realName);
			user.setAddress(address);
			user.setAvatar(avatar);
			user.setBirthday(birthday);
			user.setSex(sex);
			user.setOri(ori);
			return usersService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}*/

	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除app用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "app用户id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, @ApiParam @RequestBody Users user) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = usersService.delete(user.getId().toString());
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
