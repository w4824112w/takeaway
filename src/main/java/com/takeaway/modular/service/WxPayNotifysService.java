package com.takeaway.modular.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.takeaway.modular.dao.mapper.WxPayNotifysMapper;
import com.takeaway.modular.dao.model.WxPayNotifys;
@Service
public class WxPayNotifysService {
	private static final Logger log = Logger
			.getLogger(WxPayNotifysService.class);
	@Autowired
	private WxPayNotifysMapper wxPayNotifysMapper;
	
	public int save(WxPayNotifys wxPayNotifys){
		int id=0;
		try {
			wxPayNotifysMapper.save(wxPayNotifys);
			id=wxPayNotifys.getId();
		} catch (Exception e) {
			log.error("添加微信回调记录失败 error:"+e.toString());
			return 0;
		}
		return id;
	}

	
}
