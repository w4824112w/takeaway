package com.takeaway.modular.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.netpay.wxpay.utils.Configure;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.ManagersService;
import com.takeaway.modular.service.MenusService;
import com.takeaway.modular.service.UsersService;

/**
 * app会员登录
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "app会员登录管理---小程序接口", description = "LoginController")
public class LoginApiController {
	private static final Logger log = Logger.getLogger(LoginApiController.class);

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "app用户登录", httpMethod = "POST", notes = "根据app会员账户、密码登录系统")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "小程序登录时获取的code", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(HttpServletRequest request,
			HttpServletResponse response,String code) {
		log.info("调用登录接口开始......");

		String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";

		Map parms = new HashMap();
		parms.put("appid", Configure.getAppid());
		parms.put("secret", Configure.getSecret());
		parms.put("js_code", code);
		parms.put("grant_type", "authorization_code");
		JSONObject json = restTemplate.getForObject(url, JSONObject.class,
				parms);

		String  openid =  json.get("openid").toString();
		
		Users user=new Users();
		user.setOpenId(openid);
		usersService.save(user);
		
		JSONObject result = new JSONObject();
		result.put("openid", openid);

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
//	@ApiOperation(value = "退出登录", httpMethod = "GET", notes = "注销登出系统")
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public JSONObject logout(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("调用退出登录接口开始......");
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		session.removeAttribute("s_user");
		log.info(u.getName() + "退出了系统");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "退出系统", null);
	}

}
