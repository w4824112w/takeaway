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
import com.takeaway.modular.dao.dto.AdvertisementsDto;
import com.takeaway.modular.dao.mapper.AdvertisementsMapper;
import com.takeaway.modular.dao.model.Advertisements;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class AdvertisementsService {
	@Autowired
	private AdvertisementsMapper advertisementsMapper;

	public PageResult<Advertisements> findPage(PageBounds bounds, AdvertisementsDto dto) {
		PageList<Advertisements> advertisementss = advertisementsMapper.findPage(bounds, dto);
		return new PageResult<Advertisements>(advertisementss);
	}

	@Transactional
	public JSONObject save(Advertisements advertisements) {
		int result;
		advertisements.setStatus(1);	// 上架状态:0-下架；1-上架；
		advertisements.setCreatedAt(new Date());
		result = advertisementsMapper.save(advertisements);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增广告", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增广告", null);
		}
	}

	@Transactional
	public JSONObject update(Advertisements advertisements) {
		int result;
		result = advertisementsMapper.update(advertisements);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新广告", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新广告", null);
		}
	}

	public Advertisements getById(String id) {
		return advertisementsMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		return advertisementsMapper.delete(id);
	}

	public List<Advertisements> getAll() {
		return advertisementsMapper.getAll();
	}



}
