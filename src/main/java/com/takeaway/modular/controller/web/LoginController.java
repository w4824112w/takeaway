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

import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ManagersService;

/**
 * 用户登录
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
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "根据监狱代码、用户账户、密码登录系统")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "账户名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "passwordHash", value = "账户密码", required = true, dataType = "String", paramType = "form") })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response,String name,String passwordHash){
    	log.info("调用登录接口开始......");
		ManagersDto dto=new ManagersDto();
		dto.setName(name);
		dto.setPasswordHash(passwordHash);
		
    	Map<String,Object> retData=new HashMap<String,Object>();
    	
		if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPasswordHash())){
			retData.put("code", "1");
			retData.put("msg", "请输入账号或密码");
			retData.put("data", "");
			return retData;
		}

		
		Managers u = managersService.login(dto);
		if(u==null){
			retData.put("code", "1");
			retData.put("msg", "账号不存在，请确认");
			retData.put("data", "");
			return retData;
		}
		if(u.getStatus()==0){
			retData.put("code", "1");
			retData.put("msg", "账号已禁用");
			retData.put("data", "");
			return retData;
		}
		if(u.getStatus()==-1){
			retData.put("code", "1");
			retData.put("msg", "账号已删除");
			retData.put("data", "");
			return retData;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("s_user",u);	
		
		retData.put("code", "200");
		retData.put("msg", "登录成功");
		retData.put("dto", dto);
		retData.put("data", u);
		
		log.info("调用登录接口结束......");
    	return retData;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
	@ApiOperation(value = "退出登录", httpMethod = "GET", notes = "注销登出系统")
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public Map<String,Object> logout(HttpServletRequest request, HttpServletResponse response){
    	Map<String,Object> retData=new HashMap<String,Object>();
    	
		log.info("调用退出登录接口开始......");
		HttpSession session = request.getSession();
		Managers u  = (Managers)session.getAttribute("s_user");
		if(u!=null){
			session.removeAttribute("s_user");		
			log.info(u.getName()+"退出了系统");
			retData.put("msg", u.getName()+"退出了系统");
		}else{
			log.info("退出了系统");
			retData.put("msg", "退出了系统");
		}
		
		retData.put("code", "0");
		retData.put("data", "");
		
		log.info("调用退出登录接口结束......");
    	return retData;
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "重置密码", httpMethod = "POST", notes = "根据监狱代码、用户账户、密码重置用户密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "old_password", value = "账户旧密码", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "new_password", value = "账户新密码", required = true, dataType = "String", paramType = "form") })
    @RequestMapping(value = "/resetPwd", method = { RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> resetPwd(HttpServletRequest request, HttpServletResponse response,String old_password,String new_password){
		Map<String,Object> retData=new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		
		ManagersDto dto=new ManagersDto();
		dto.setPasswordHash(old_password);
		dto.setName(u.getName());
		
		Managers old_user = managersService.login(dto);

		if (old_user != null) {
			old_user.setPasswordHash(new_password);
			old_user.setUpdatedAt(new Date());
			managersService.saveOrUpdate(old_user);
		} else {
			retData.put("code", "2");
			retData.put("msg", "账号不存在，请确认");
			return retData;
		}
		
		 //发送邮件
		//调用发送邮件接口给系统用户发送邮件,重置后的密码newPwd
		
		retData.put("code", "0");
		retData.put("msg", "重置用户密码成功");
		
    	return retData;
    }
}
