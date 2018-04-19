package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import com.takeaway.modular.dao.dto.CitiesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.CitiesService;

/**
 * 城市管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/cities")
@Api(value = "城市管理", description = "CitiesController")
public class CitiesController {
	private static final Logger log = Logger
			.getLogger(CitiesController.class);

	@Autowired
	private CitiesService citiesService;

	/**
	 * 查询城市信息
	 * @param request
	 * @param response
	 * @param ProvicesId
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "查询城市信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "provinceId", value = "省份id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String provinceId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<CitiesDto> citys = citiesService.findByProvinceId(provinceId);

		JSONObject result = new JSONObject();
		result.put("citysSize", citys.size());
		result.put("citys", citys);
		log.info("查询省份信息接口成功");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "省份信息查询", result);

	}

}
