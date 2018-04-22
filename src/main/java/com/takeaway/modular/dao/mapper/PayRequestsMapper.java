package com.takeaway.modular.dao.mapper;

import org.springframework.stereotype.Repository;

import com.takeaway.modular.dao.dto.PayRequestsDto;
import com.takeaway.modular.dao.model.PayRequests;

@Repository
public interface PayRequestsMapper{
	int save(PayRequestsDto dto) throws Exception;
	void update(PayRequests payReq) throws Exception;
}