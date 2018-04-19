package com.takeaway.modular.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UsersService;

/**
 * 会员信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "会员信息---小程序接口", description = "UsersApiController")
public class UsersApiController {
	private static final Logger log = Logger
			.getLogger(UsersApiController.class);

	@Autowired
	private UsersService usersService;

	@ApiOperation(value = "新增会员", httpMethod = "POST", notes = "新增会员信息")
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
			user.setStatus(1);
			return usersService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}
	
	
}
