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
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.dto.UserScoresDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.UserScores;
import com.takeaway.modular.service.UserScoresService;

/**
 * 会员积分明细信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/user_scores")
@Api(value = "会员积分明细管理", description = "UserScoresController")
public class UserScoresController {
	private static final Logger log = Logger
			.getLogger(UserScoresController.class);

	@Autowired
	private UserScoresService userScoresService;

	@ApiOperation(value = "新增会员积分明细积分明细", httpMethod = "POST", notes = "新增会员积分明细信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserScores userScores) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		try {
			return userScoresService.save(userScores);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "分页查询会员积分明细")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "会员id", required = false, dataType = "Integer", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String userId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		UserScoresDto dto = new UserScoresDto();
		dto.setUserId(userId);
		
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<UserScores> userScores = userScoresService.findPage(bounds,dto);
		JSONObject result = new JSONObject();
		result.put("userId", userId);
		result.put("page", userScores.getPaginator().getPage());
		result.put("rows", userScores.getPaginator().getLimit());
		result.put("totalCount", userScores.getPaginator().getTotalCount());
		result.put("userScores", userScores.getPageList());

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "分页查询会员积分明细", result);

	}
}
