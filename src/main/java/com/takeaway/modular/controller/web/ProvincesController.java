package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ProvincesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ProvincesService;

/**
 * 省份管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/provinces")
@Api(value = "省份管理", description = "ProvincesController")
public class ProvincesController {
	private static final Logger log = Logger
			.getLogger(ProvincesController.class);

	@Autowired
	private ProvincesService provincesService;

	/**
	 * 查询省份信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "查询省份信息")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<ProvincesDto> provinces = provincesService.findAll();

		JSONObject result = new JSONObject();
		result.put("provincesSize", provinces.size());
		result.put("provinces", provinces);
		log.info("查询省份信息接口成功");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "省份信息查询", result);

	}

}
