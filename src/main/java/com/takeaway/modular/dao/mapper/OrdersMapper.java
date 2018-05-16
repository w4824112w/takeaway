package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
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

	List<Orders> getByUserId(String userId);
	
	List<Orders> getByPay(String userId);
	
	List<Orders> getByShip(String userId);
	
	List<Orders> getByAppraises(String userId);
	
	List<Orders> getByRefund(String userId);
	
	PageList<OrdersDto> findPage(PageBounds bounds, OrdersDto dto);
	
	PageList<ReportDto> reportQuery(PageBounds bounds, ReportDto dto);
	
	PageList<BusinessReportDto> businessReport(PageBounds bounds, BusinessReportDto dto);
	
	List<ReportDto> reportExport(ReportDto dto);
	
	PageList<Orders> findReminderPage(PageBounds bounds, OrdersDto dto);

	int save(Orders orders);

	int update(Orders orders);

	int delete(String id);

}