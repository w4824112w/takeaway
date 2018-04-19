package com.takeaway.modular.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takeaway.modular.dao.dto.ProvincesDto;
import com.takeaway.modular.dao.mapper.ProvincesMapper;


/**
 * 
 * @author Administrator
 *
 */
@Service
public class ProvincesService {
	private static final Logger log = Logger.getLogger(ProvincesService.class);
	@Autowired
	private ProvincesMapper provincesMapper;

	public List<ProvincesDto> findAll() {
		return provincesMapper.getAll();
	}

}
