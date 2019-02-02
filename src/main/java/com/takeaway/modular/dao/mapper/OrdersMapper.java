package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.modular.dao.dto.BossReportDto;
import com.takeaway.modular.dao.dto.BusinessReportDto;
import com.takeaway.modular.dao.dto.OrdersDto;
import com.takeaway.modular.dao.dto.ReportDto;
import com.takeaway.modular.dao.model.Orders;

public interface OrdersMapper {
	Orders getById(String id);
	
	Orders getByOrderNo(String orderNo);
	
	int getProcessing();
	
	int getRefunding();
	
	int getTodayOrders();
	
	Double getTodayTurnover();
	
	int getMonthOrdersByMerchantId(String merchantId);

	PageList<Orders> getByUserId(PageBounds bounds,String userId);
	
	PageList<Orders> getByPay(PageBounds bounds,String userId);
	
	PageList<Orders> getByShip(PageBounds bounds,String userId);
	
	PageList<Orders> getByAppraises(PageBounds bounds,String userId);
	
	List<Orders> getByNotAppraises();
	
	List<Orders> getByNotReceipt();
	
	List<Orders> getByNotComplete();
	
	List<Orders> getByNotPrint();
	
	List<Orders> getByNotPay();
	
	PageList<Orders> getByRefund(PageBounds bounds,String userId);
	
	PageList<OrdersDto> findPage(PageBounds bounds, OrdersDto dto);
	
	PageList<ReportDto> reportQuery(PageBounds bounds, ReportDto dto);
	
	PageList<BusinessReportDto> businessReport(PageBounds bounds, BusinessReportDto dto);
	
	BossReportDto bossReport(BossReportDto dto);
	
	List<ReportDto> reportExport(ReportDto dto);
	
	PageList<Orders> findReminderPage(PageBounds bounds, OrdersDto dto);
	
	PageList<Orders> findReservesPage(PageBounds bounds, OrdersDto dto);

	int save(Orders orders);

	int update(Orders orders);

	int delete(String id);

}