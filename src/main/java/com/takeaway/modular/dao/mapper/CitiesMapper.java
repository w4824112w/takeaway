package com.takeaway.modular.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.takeaway.modular.dao.dto.CitiesDto;
import com.takeaway.modular.dao.model.Cities;

@Repository
public interface CitiesMapper {

	List<Cities> getAll();

	List<CitiesDto> getByProvinceId(String provinceId);
}