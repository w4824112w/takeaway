package com.takeaway.modular.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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
import com.takeaway.modular.dao.dto.UserRanksDto;
import com.takeaway.modular.dao.mapper.FeedbacksMapper;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.UserRanksMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Feedbacks;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.Users;

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
	private OrderHistorysService orderHistorysService;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Autowired
	private UserRanksMapper userRanksMapper;

	public PageResult<FeedbacksDto> findPage(PageBounds bounds, FeedbacksDto dto) {
		PageList<FeedbacksDto> feedbacks = feedbacksMapper
				.findPage(bounds, dto);
		for (FeedbacksDto feedback : feedbacks) {
			feedback.setUsers(usersMapper.getById(feedback.getUserId()));
			feedback.setOrders(ordersMapper.getById(feedback.getOrderId()));
			feedback.setMerchants(merchantsMapper.getById(feedback
					.getMerchantId()));
			feedback.setItems(itemsMapper.getById(feedback.getItemId()));
		}
		return new PageResult<FeedbacksDto>(feedbacks);
	}

	@Transactional
	public JSONObject save(Feedbacks feedbacks) {
		int result;
		feedbacks.setCreatedAt(new Date());
		result = feedbacksMapper.save(feedbacks);
		Orders orders=ordersMapper.getById(feedbacks.getOrderId().toString());
		orders.setStatus(5);	//	1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后
		orders.setIsAppraises(1);	// 是否点评(0：未点评;1：已点评;)
		orders.setAppraisesDate(new Date());	//评价时间
		ordersMapper.update(orders);
		
    	OrderHistorys orderHistorys=new OrderHistorys();
    	BeanUtils.copyProperties(orders, orderHistorys);
    	orderHistorysService.save(orderHistorys);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增会员订单评价", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增会员订单评价", null);
		}
	}

	@Transactional
	public JSONObject Reply(Feedbacks feedbacks) {
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
		List<Feedbacks> feedbacks = feedbacksMapper.getByUserId(userId);
		for (Feedbacks feedback : feedbacks) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			feedback.setCreatedTime(sdf.format(feedback.getCreatedAt()));
			
			Users users=usersMapper.getById(feedback.getUserId().toString());
			feedback.setUserName(users.getUserName());
			feedback.setUserPhoto(users.getUserPhoto());
			UserRanksDto dto=new UserRanksDto();
			dto.setUserScore(users.getUserScore().toString());
			UserRanks userRanks=userRanksMapper.getCurrentUserRanks(dto);
			if(userRanks!=null){
				feedback.setUserRank(userRanks.getName());
			}
			feedback.setSubFeedbacks(feedbacksMapper.getByPid(feedback.getId().toString()));
		}
		return feedbacks;
	}

	public List<Feedbacks> getByMerchantId(String merchantId) {
		List<Feedbacks> feedbacks = feedbacksMapper.getByMerchantId(merchantId);
		for (Feedbacks feedback : feedbacks) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			feedback.setCreatedTime(sdf.format(feedback.getCreatedAt()));
			
			Users users=usersMapper.getById(feedback.getUserId().toString());
			feedback.setUserName(users.getUserName());
			feedback.setUserPhoto(users.getUserPhoto());
			UserRanksDto dto=new UserRanksDto();
			dto.setUserScore(users.getUserScore().toString());
			UserRanks userRanks=userRanksMapper.getCurrentUserRanks(dto);
			if(userRanks!=null){
				feedback.setUserRank(userRanks.getName());
			}
			feedback.setSubFeedbacks(feedbacksMapper.getByPid(feedback.getId().toString()));
		}
		return feedbacks;
	}

	@Transactional
	public int delete(String id) {
		return feedbacksMapper.delete(id);
	}

}
