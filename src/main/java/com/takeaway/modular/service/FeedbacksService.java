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
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.FeedbacksDto;
import com.takeaway.modular.dao.mapper.FeedbacksMapper;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Feedbacks;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class FeedbacksService {
	@Autowired
	private FeedbacksMapper feedbacksMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private MerchantsMapper merchantsMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;

	public PageResult<FeedbacksDto> findPage(PageBounds bounds, FeedbacksDto dto) {
		PageList<FeedbacksDto> feedbacks = feedbacksMapper.findPage(bounds, dto);
		for (FeedbacksDto feedback : feedbacks) {
			feedback.setUsers(usersMapper.getById(feedback.getUserId()));
			feedback.setOrders(ordersMapper.getById(feedback.getOrderId()));
			feedback.setMerchants(merchantsMapper.getById(feedback.getMerchantId()));
			feedback.setItems(itemsMapper.getById(feedback.getItemId()));
		}
		return new PageResult<FeedbacksDto>(feedbacks);
	}

	@Transactional
	public JSONObject save(Feedbacks feedbacks) {
		int result;
		feedbacks.setCreatedAt(new Date());
		result = feedbacksMapper.save(feedbacks);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员订单评价", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员订单评价", null);
		}
	}

	@Transactional
	public JSONObject update(Feedbacks feedbacks) {
		int result;
		result = feedbacksMapper.update(feedbacks);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新会员订单评价", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新会员订单评价", null);
		}
	}

	public Feedbacks getById(String id) {
		return feedbacksMapper.getById(id);
	}

	public List<Feedbacks> getByUserId(String userId) {
		return feedbacksMapper.getByUserId(userId);
	}

	public List<Feedbacks> getByMerchantId(String merchantId) {
		return feedbacksMapper.getByMerchantId(merchantId);
	}

	@Transactional
	public int delete(String id) {
		return feedbacksMapper.delete(id);
	}

}
