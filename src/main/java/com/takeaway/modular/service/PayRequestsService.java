package com.takeaway.modular.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeaway.commons.utils.DateUtil;
import com.takeaway.modular.dao.dto.PayRequestsDto;
import com.takeaway.modular.dao.mapper.PayRequestsMapper;
import com.takeaway.modular.dao.model.PayRequests;

/**
 * 
 * @author yb
 *
 */
@Service
public class PayRequestsService {
	private static final Logger log = Logger
	            .getLogger(PayRequestsService.class);
	@Autowired
	private PayRequestsMapper payRequestsMapper;	
	
	public boolean save(PayRequestsDto dto){
	
		Integer id=null;
		try {
			id=payRequestsMapper.save(dto);
		} catch (Exception e) {
			log.error("增加请求记录出现异常 error:"+e.toString());
			return false;
		}
		if(id !=null){
			return true;
		}
		return false;
	}
	
	public boolean save(String systemId,String payTypeId,String orderNo,String itemName,String amount,String notifyUrl,String jumpUrl){
		PayRequestsDto dto=new PayRequestsDto();
		dto.setPayTypeId(payTypeId);
		dto.setOrderNo(orderNo);
		dto.setItemName(itemName);
		dto.setAmount(amount);
		dto.setNotifyUrl(notifyUrl);
		dto.setJumpUrl(jumpUrl);
		dto.setCreatedAt(DateUtil.parseDate(new Date(), "yyyy-MM-dd HH:mm:sss"));
		Integer id=null;
		try {
			id=payRequestsMapper.save(dto);
		} catch (Exception e) {
			log.error("增加请求记录出现异常 error:"+e.toString());
			return false;
		}
		if(id !=null){
			return true;
		}
		return false;
	}
	
	public boolean update(String orderNo,Integer isPay,Integer isNotify,String successTime,String notifyTime){
		PayRequests payReq=new PayRequests();
		payReq.setOrderNo(orderNo);
		payReq.setIsPay(isPay);
		payReq.setIsNotify(isNotify);
		payReq.setSuccessTime(successTime);
		payReq.setNotifyTime(notifyTime);
		try {
			payRequestsMapper.update(payReq);
		} catch (Exception e) {
			log.error("更新请求记录出现异常 error:"+e.toString());
			return false;
		}
		return true;
	}
}
