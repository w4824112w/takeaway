package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.OrderHistorysMapper;
import com.takeaway.modular.dao.mapper.OrderItemPropertysMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.PropertysMapper;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.dao.model.OrderItemPropertys;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.Propertys;

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
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private ItemPropertysMapper itemPropertysMapper;

	@Autowired
	private PropertysMapper propertysMapper;
	
	@Autowired
	private OrderItemPropertysMapper orderItemPropertysMapper;
	
	public PageResult<OrderHistorys> findPage(PageBounds bounds, OrderHistorysDto dto) {
		PageList<OrderHistorys> orderHistorys = orderHistorysMapper.findPage(bounds, dto);
		for(OrderHistorys orderHistory:orderHistorys){
			Orders orders=ordersMapper.getByOrderNo(orderHistory.getOrderNo().toString());
			List<OrderItems> orderItems = orderItemsMapper.getByOrderId(orders
					.getId().toString());
			for (OrderItems orderItem : orderItems) {
				List<OrderItemPropertys> orderItemPropertys = orderItemPropertysMapper
						.getByOrderItemId(orderItem.getId().toString());
				for (OrderItemPropertys orderItemProperty : orderItemPropertys) {
					ItemPropertys itemPropertys = itemPropertysMapper
							.getById(orderItemProperty.getItemPropertyId()
									.toString());
					Propertys propertys = propertysMapper.getById(itemPropertys
							.getPropertyId().toString());
					orderItemProperty.setPrice(itemPropertys.getPrice());
					orderItemProperty.setPropertyName(propertys.getName());
				}
				orderItem.setOrderItemPropertys(orderItemPropertys);
			}
			orderHistory.setOrderItems(orderItems);
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
