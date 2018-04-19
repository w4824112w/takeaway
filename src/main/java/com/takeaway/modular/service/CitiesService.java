package com.takeaway.modular.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeaway.modular.dao.dto.CitiesDto;
import com.takeaway.modular.dao.mapper.CitiesMapper;
import com.takeaway.modular.dao.model.Cities;


/**
 * 
 * @author Administrator
 *
 */
@Service
public class CitiesService {
	private static final Logger log = Logger.getLogger(CitiesService.class);
	@Autowired
	private CitiesMapper citiesMapper;

	public List<Cities> findAll() {
		return citiesMapper.getAll();
	}
	
	public List<CitiesDto> findByProvinceId(String provinceId) {
		return  citiesMapper.getByProvinceId(provinceId);
	}
}
