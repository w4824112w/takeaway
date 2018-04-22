package com.takeaway.modular.dao.mapper;


import org.springframework.stereotype.Repository;

import com.takeaway.modular.dao.model.WxPayNotifys;

@Repository
public interface WxPayNotifysMapper{
	int save(WxPayNotifys wxPayNotifys);

}