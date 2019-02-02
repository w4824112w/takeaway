package com.takeaway.modular.dao.mapper;

import java.util.List;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserCouponsDto;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.OrderCouponRecords;
import com.takeaway.modular.dao.model.UserCoupons;


public interface OrderCouponRecordsMapper {
	
	List<OrderCouponRecords> getByOrderNo(String orderNo);
	
	int save(OrderCouponRecords orderCouponRecords);
	
}