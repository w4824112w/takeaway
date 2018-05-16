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
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.UserLogsDto;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.UserLogsMapper;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.UserLogs;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UserLogsService {
	@Autowired
	private UserLogsMapper userLogsMapper;

	public PageResult<UserLogsDto> reportQuery(PageBounds bounds,
			UserLogsDto dto) {
		PageList<UserLogsDto> report = userLogsMapper
				.reportQuery(bounds, dto);
		return new PageResult<UserLogsDto>(report);
	}

	@Transactional
	public JSONObject save(UserLogs userLogs) {
		int result;

		userLogs.setCreatedAt(new Date());
		result = userLogsMapper.save(userLogs);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单", null);
		}
	}

}
