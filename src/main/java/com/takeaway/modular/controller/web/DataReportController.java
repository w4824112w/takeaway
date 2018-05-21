package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.BossReportDto;
import com.takeaway.modular.dao.dto.BusinessReportDto;
import com.takeaway.modular.dao.dto.OrderItemsDto;
import com.takeaway.modular.dao.dto.UserLogsDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.UserLogsService;

/**
 * 经营数据管理
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/data_report")
@Api(value = "经营数据管理", description = "DataReportController")
public class DataReportController {
	private static final Logger log = Logger
			.getLogger(DataReportController.class);

	@Autowired
	private UserLogsService userLogsService;

	@Autowired
	private OrdersService ordersService;

	/**
	 * 分页查询每天访问量报表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "访问统计", httpMethod = "GET", notes = "分页查询每天访问量报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/accessPage", method = RequestMethod.GET)
	public JSONObject accessPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String startTime, String endTime,String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		UserLogsDto dto = new UserLogsDto();
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<UserLogsDto> report = userLogsService.reportQuery(bounds,
				dto);

		JSONObject result = new JSONObject();
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		result.put("page", report.getPaginator().getPage());
		result.put("rows", report.getPaginator().getLimit());
		result.put("totalCount", report.getPaginator().getTotalCount());
		result.put("report", report.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 分页查询销量排行报表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@ApiOperation(value = "销量排行", httpMethod = "GET", notes = "分页查询销量排行报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")
			})
	@RequestMapping(value = "/salesPage", method = RequestMethod.GET)
	public JSONObject salesPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		OrderItemsDto dto = new OrderItemsDto();
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<OrderItemsDto> report = ordersService.salesReportQuery(
				bounds, dto);

		JSONObject result = new JSONObject();
		result.put("page", report.getPaginator().getPage());
		result.put("rows", report.getPaginator().getLimit());
		result.put("totalCount", report.getPaginator().getTotalCount());
		result.put("report", report.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 分页查询订单统计报表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 */
	@ApiOperation(value = "订单统计", httpMethod = "GET", notes = "分页查询订单统计报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")
			})
	@RequestMapping(value = "/businessPage", method = RequestMethod.GET)
	public JSONObject businessPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String startTime, String endTime,String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		BusinessReportDto dto = new BusinessReportDto();
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<BusinessReportDto> report = ordersService.businessReport(
				bounds, dto);

		JSONObject result = new JSONObject();
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		result.put("page", report.getPaginator().getPage());
		result.put("rows", report.getPaginator().getLimit());
		result.put("totalCount", report.getPaginator().getTotalCount());
		result.put("report", report.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	@ApiOperation(value = "老板统计页", httpMethod = "GET", notes = "查询老板统计页报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "reportTime", value = "统计日期", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/bossPage", method = RequestMethod.GET)
	public JSONObject bossPage(HttpServletRequest request,
			HttpServletResponse response, String merchantId, String reportTime) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		BossReportDto dto = new BossReportDto();
		dto.setMerchantId(merchantId);
		dto.setReportTime(reportTime);
		BossReportDto report = ordersService.bossReport(dto);

		JSONObject result = new JSONObject();
		result.put("merchantId", merchantId);
		result.put("reportTime", reportTime);
		result.put("report", report);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}
}
