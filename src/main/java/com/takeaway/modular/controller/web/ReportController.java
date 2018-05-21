package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.ReportDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.OrdersService;

/**
 * 会员收货地址信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/report")
@Api(value = "财务管理", description = "ReportController")
public class ReportController {
	private static final Logger log = Logger.getLogger(ReportController.class);

	@Autowired
	private OrdersService ordersService;

	/**
	 * 分页查询每天财务统计报表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询每天财务统计报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String startTime, String endTime, String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		ReportDto dto = new ReportDto();
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		} else {
			dto.setMerchantId(merchantId);
		}
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<ReportDto> report = ordersService.reportQuery(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		result.put("page", report.getPaginator().getPage());
		result.put("rows", report.getPaginator().getLimit());
		result.put("totalCount", report.getPaginator().getTotalCount());
		result.put("report", report.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	@ApiOperation(value = "导出", httpMethod = "GET", notes = "导出财务统计报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startTime", value = "开始日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束日期", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")})
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public JSONObject export(HttpServletRequest request,
			HttpServletResponse response, String startTime, String endTime,String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录",
					null);
		}

		ReportDto dto = new ReportDto();
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		} else {
			dto.setMerchantId(merchantId);
		}
		List<ReportDto> report = ordersService.reportExport(dto);

		int colIndex = 0;

		try {

			String filename = "excel_file.xlsx";

			String tmpPath = request.getSession().getServletContext()
					.getRealPath("/filedown/" + filename);

			InputStream is = new FileInputStream(tmpPath);

			XSSFWorkbook work = new XSSFWorkbook(is);

			// 获取第一个工作表
			XSSFSheet sheet = work.getSheetAt(0);

			// 创建第一行
			XSSFRow row = null;
			XSSFCell cell = null;
			row = sheet.createRow(0);

			cell = row.createCell(colIndex);
			cell.setCellValue("序号");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("时间");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("营业额");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("配送费");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("优惠券金额");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("满减送金额");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("实际支付金额");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("平台服务费");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("实际收入");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("微信手续费");
			colIndex++;

			cell = row.createCell(colIndex);
			cell.setCellValue("结算金额");
			colIndex++;

			colIndex = 0;

			for (int i = 0; i < report.size(); i++) {

				// 从第三行开始写数据，第一行和第二行分别为标题和信息列
				row = sheet.createRow(i + 1);

				// 第1列：序号
				cell = row.createCell(colIndex);
				cell.setCellValue(i + 1);
				colIndex++;

				ReportDto o = report.get(i);

				// 第2列：时间
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getSettlTime());
				colIndex++;

				// 第3列：营业额
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getTotalPrice());
				colIndex++;

				// 第4列：配送费
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getDeliverMoney());
				colIndex++;

				// 第5列：优惠券金额
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getCouponMoney());
				colIndex++;

				// 第6列：满减送金额
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getActivityMoney());
				colIndex++;

				// 第7列：实际支付金额
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getRealTotalMoney());
				colIndex++;

				// 第8列：平台服务费
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getPlatformServiceFee());
				colIndex++;

				// 第9列：实际收入
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getRealIncome());
				colIndex++;

				// 第10列：微信手续费
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getWxProcedures());
				colIndex++;

				// 第11列：结算金额
				cell = row.createCell(colIndex);
				cell.setCellValue(o.getSettleMoney());
				colIndex++;

				colIndex = 0;
			}

			String savePath = request.getSession().getServletContext()
					.getRealPath("/filedown/reports/" + filename);

			FileOutputStream os = new FileOutputStream(savePath);
			work.write(os);
			os.close();

			JSONObject result = new JSONObject();
			result.put("filepath", "/filedown/reports/" + filename);
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商品数据导出", result);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常，导出", null);
		}

	}

}
