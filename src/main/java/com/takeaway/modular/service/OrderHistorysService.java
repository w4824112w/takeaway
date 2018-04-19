package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrderHistorysDto;
import com.takeaway.modular.dao.mapper.OrderHistorysMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderHistorys;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class OrderHistorysService {
	@Autowired
	private OrderHistorysMapper orderHistorysMapper;
	
	@Autowired
	private OrderItemsMapper orderItemsMapper;

	public PageResult<OrderHistorys> findPage(PageBounds bounds, OrderHistorysDto dto) {
		PageList<OrderHistorys> orderHistorys = orderHistorysMapper.findPage(bounds, dto);
		for(OrderHistorys orderHistory:orderHistorys){
			orderHistory.setOrderItems(orderItemsMapper.getByOrderId(orderHistory.getId().toString()));
		}
		return new PageResult<OrderHistorys>(orderHistorys);
	}

	@Transactional
	public JSONObject save(OrderHistorys orderHistorys) {
		int result;

		orderHistorys.setCreatedAt(new Date());
		result = orderHistorysMapper.save(orderHistorys);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单历史", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单历史", null);
		}
	}

	@Transactional
	public JSONObject update(OrderHistorys orderHistorys) {
		int result;
		orderHistorys.setUpdatedAt(new Date());
		result = orderHistorysMapper.update(orderHistorys);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单历史", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单历史", null);
		}
	}

	public OrderHistorys getById(String id) {
		return orderHistorysMapper.getById(id);
	}
	
	public JSONObject OrderIndex() {
		JSONObject result = new JSONObject();
		result.put("processing", orderHistorysMapper.getProcessing());
		result.put("refunding", orderHistorysMapper.getRefunding());
		result.put("todayOrderHistorys", orderHistorysMapper.getTodayOrderHistorys());
		result.put("todayTurnover", orderHistorysMapper.getTodayTurnover());
		return result;
	}

	@Transactional
	public int delete(String id) {
		return orderHistorysMapper.delete(id);
	}

}
