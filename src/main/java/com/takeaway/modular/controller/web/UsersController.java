package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UsersService;

/**
 * 系统用户管理
 *
 * @author hk
 */
@RestController
@RequestMapping("/users")
@Api(value = "系统用户管理", description = "UsersController")
public class UsersController {
	private static final Logger log = Logger.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	/**
	 * 系统用户登录
	 *
	 * @param request
	 * @param response
	 * @param prison
	 * @param username
	 * @param password
	 * @return
	 */
	@ApiOperation(value = "登录", httpMethod = "POST", notes = "根据监狱代码、用户账户、密码登录系统")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "prison", value = "监狱代码", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "username", value = "账户名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "password", value = "账户密码", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "prison") String prison,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		log.info("调用登录接口开始......");
		if (StringUtils.isBlank(prison) || StringUtils.isBlank(username)
				|| StringUtils.isBlank(password)) {
			return ErrorEnums.getResult(ErrorEnums.NAMEORPASW_ISNULL,
					"请输入监狱代码、账号和密码", null);
		}

		HttpSession session = request.getSession();
		JSONObject result = new JSONObject();
		UsersDto sys = usersService.isSysLogin(username, password);
		if (sys.getRole().equals("0")) {
			session.setAttribute("s_user", sys);
			result.put("users", sys);
			log.info("调用登录接口结束......");
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "系统管理员登录", result);
		}

		UsersDto users = usersService.login(prison, username, password);
		if (users == null) {
			return ErrorEnums.getResult(ErrorEnums.NAMEORPASW_ERROR,
					"账号不存在，请确认", null);
		}

		session.setAttribute("s_user", users);
		result.put("users", users);

		log.info("调用登录接口结束......");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "监狱用户登录", result);

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
		session.removeAttribute("s_user");
		log.info("退出登录");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "退出登录", null);
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
			HttpServletResponse response,
			@RequestParam(value = "old_password") String old_password,
			@RequestParam(value = "new_password") String new_password) {
		log.info("调用重置密码接口开始......");

		HttpSession session = request.getSession();
		UsersDto users = (UsersDto) session.getAttribute("s_user");
		if (users == null) {
			return ErrorEnums.getResult(ErrorEnums.NOT_LOGIN, "用户已超时", null);
		}

		Users old_user = usersService.getUsersByConditions(users.getJailId()
				.toString(), users.getUsername(), old_password);

		if (old_user != null) {
			old_user.setMd5Password(new_password);
			usersService.saveOrUpdate(old_user);
		} else {
			return ErrorEnums.getResult(ErrorEnums.NAMEORPASW_ERROR,
					"账号不存在，请确认", null);
		}

		// 发送邮件
		// 调用发送邮件接口给系统用户发送邮件,重置后的密码newPwd

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "重置用户密码", null);
	}

    @ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "jail", value = "监狱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "role", value = "角色", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public JSONObject page(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            String jail, String username, Integer role, HttpServletRequest request) {
        PageResult<Users> userList = usersService.findPage(page, rows, jail, username, role);
        JSONObject obj = new JSONObject();
        obj.put("items", userList.getPageList());
        obj.put("itemSize", userList.getPaginator().getTotalCount());

        log.info("分页查询用户信息");
        return ErrorEnums.getResult(ErrorEnums.SUCCESS, "分页查询用户信息", obj);
    }

	@ApiOperation(value = "新增", httpMethod = "POST", notes = "创建监狱账户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "prison", value = "监狱id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "username", value = "账户名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "password", value = "账户密码", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "prison") Integer prison,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, Integer role) {
		if (null == prison || StringUtils.isBlank(username)
				|| StringUtils.isBlank(password)) {
			return ErrorEnums.getResult(ErrorEnums.NAMEORPASW_ISNULL,
					"请输入监狱代码、账号和密码", null);
		}
		Users users = new Users();
		users.setJailId(prison);
		users.setRole(role);
		users.setUsername(username);
		users.setMd5Password(password);
		usersService.saveOrUpdate(users);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑监狱账户", null);
	}

	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "form")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		UsersDto users = (UsersDto) session.getAttribute("s_user");
		if (users == null) {
			return ErrorEnums.getResult(ErrorEnums.NOT_LOGIN, "用户已超时", null);
		}
		Users dUser = usersService.getById(id);
		dUser.setUpdatedAt(new Date());
		dUser.setSysFlag(0);

		usersService.saveOrUpdate(dUser);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除用户", null);
	}

    /**
     * @method toEdit
     * @decription
     * @author Seaman Luo
     * @param id
     * @return com.alibaba.fastjson.JSONObject
     * @exception
     * @date 2018/3/7
     */
    @ApiOperation(value = "编辑展示", httpMethod = "GET", notes = "用户编辑展示")
    @ApiImplicitParam(name = "id", value = "用户Id", required = true, dataType = "Integer", paramType = "query")
    @RequestMapping(value = "/to_edit", method = RequestMethod.GET)
    public JSONObject toEdit(HttpServletRequest request, Integer id) {
        HttpSession session = request.getSession();
        UsersDto users = (UsersDto) session.getAttribute("s_user");
        if (users == null) {
            return ErrorEnums.getResult(ErrorEnums.NOT_LOGIN, "用户已超时", null);
        }

        Users u = usersService.getById(id.toString());
        return ErrorEnums.getResult(ErrorEnums.SUCCESS, "用户编辑展示", u);
    }

    @ApiOperation(value = "编辑", httpMethod = "POST", notes = "编辑监狱账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "prison", value = "监狱id", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "username", value = "账户名称", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "账户密码", required = true, dataType = "String", paramType = "form")})
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JSONObject edit(HttpServletRequest request,
                          HttpServletResponse response, @RequestParam(value = "id") Integer id,
                          @RequestParam(value = "prison") Integer prison,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password, Integer role) {
        if (null == prison || StringUtils.isBlank(username)
                || StringUtils.isBlank(password)) {
            return ErrorEnums.getResult(ErrorEnums.NAMEORPASW_ISNULL,
                    "请输入监狱代码、账号和密码", null);
        }
        Users users = usersService.getById(id.toString());
        users.setJailId(prison);
        users.setRole(role);
        users.setUsername(username);
        users.setMd5Password(password);
        usersService.saveOrUpdate(users);

        return ErrorEnums.getResult(ErrorEnums.SUCCESS, "创建监狱账户", null);
    }

}
