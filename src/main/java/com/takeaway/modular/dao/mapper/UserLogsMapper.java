package com.takeaway.modular.dao.mapper;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.modular.dao.dto.UserLogsDto;
import com.takeaway.modular.dao.model.UserLogs;

public interface UserLogsMapper {

	PageList<UserLogsDto> reportQuery(PageBounds bounds, UserLogsDto dto);

	int save(UserLogs userLogs);

}