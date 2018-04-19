package com.takeaway.modular.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.takeaway.modular.dao.dto.ProvincesDto;

@Repository
public interface ProvincesMapper {

	List<ProvincesDto> getAll();

}