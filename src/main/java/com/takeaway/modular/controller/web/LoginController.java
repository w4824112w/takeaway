package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ManagersService;
import com.takeaway.modular.service.MenusService;

/**
 * 用户登录
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("")
@Api(value = "登录管理", description = "LoginController")
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private ManagersService managersService;
	
	@Autowired
	private MenusService menusService;
	


	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "根据商户账户、密码登录系统")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "商户账户", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "password", value = "商户密码", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(HttpServletRequest request,
			HttpServletResponse response, String name, String password) {
		log.info("调用登录接口开始......");
		ManagersDto dto = new ManagersDto();
		dto.setName(name);
		dto.setPasswordHash(password);

		if (StringUtils.isBlank(dto.getName())
				|| StringUtils.isBlank(dto.getPasswordHash())) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "请输入账号或密码", null);
		}

		Managers u = managersService.login(dto);
		
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账号不存在，请确认", null);
		}
		if (u.getStatus() == 0) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账号已禁用", null);
		}
		if (u.getStatus() == -1) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账号已删除", null);
		}

		HttpSession session = request.getSession();
		session.setAttribute("s_user", u);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("password", password);
		result.put("users", u);
		log.info("调用登录接口结束......");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "登录", result);
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "退出登录", httpMethod = "GET", notes = "注销登出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public JSONObject logout(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("调用退出登录接口开始......");
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		session.removeAttribute("s_user");
		log.info(u.getName() + "退出了系统");
		return ErrorEnums.getResult(ErrorEnums.OVERTIME, "退出系统", null);
	}

	/**
	 * 重置密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "重置密码", httpMethod = "POST", notes = "根据监狱代码、用户账户、密码重置用户密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "old_password", value = "账户旧密码", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "new_password", value = "账户新密码", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	public JSONObject resetPwd(HttpServletRequest request,
			HttpServletResponse response, String old_password,
			String new_password) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		ManagersDto dto = new ManagersDto();
		dto.setPasswordHash(old_password);
		dto.setName(u.getName());

		Managers old_user = managersService.login(dto);

		if (old_user != null) {
			old_user.setPasswordHash(MD5Util.MD5(new_password));
			managersService.update(old_user);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账号不存在，请确认", null);
		}

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "重置用户密码成功", null);
	}
}
