package com.takeaway.modular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.PropertysDto;
import com.takeaway.modular.dao.mapper.PropertysMapper;
import com.takeaway.modular.dao.model.Propertys;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class PropertysService {
	@Autowired
	private PropertysMapper propertysMapper;

	public PageResult<Propertys> findPage(PageBounds bounds, PropertysDto dto) {
		PageList<Propertys> propertys = propertysMapper.findPage(bounds, dto);
		for(Propertys property:propertys){
			property.setSubPropertys(propertysMapper.getByPid(property.getId().toString()));
		}
		return new PageResult<Propertys>(propertys);
	}

	@Transactional
	public JSONObject save(Propertys propertys) {
		int result;
		result = propertysMapper.save(propertys);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商品属性", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商品属性", result);
		}
	}

	@Transactional
	public JSONObject bathcSave(List<Propertys> propertys) {
		int result = 0;
		for (Propertys parent : propertys) {
			result = propertysMapper.save(parent);
			for (Propertys child : parent.getSubPropertys()) {
				child.setPid(parent.getId());
				propertysMapper.save(child);
			}
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商品属性", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商品属性", result);
		}
	}

	@Transactional
	public JSONObject bathcUpdate(List<Propertys> propertys) {
		int result = 0;

		for (Propertys parent : propertys) {
			result = propertysMapper.update(parent);
			propertysMapper.delByPropertyId(parent.getId().toString());
			for (Propertys child : parent.getSubPropertys()) {
				child.setPid(parent.getId());
				propertysMapper.update(child);
			}
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商品属性", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商品属性", result);
		}
	}

	@Transactional
	public JSONObject update(Propertys propertys) {
		int result;
		result = propertysMapper.update(propertys);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品属性", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品属性", result);
		}
	}

	public Propertys getById(String id) {
		Propertys propertys = propertysMapper.getById(id);
		propertys.setSubPropertys(propertysMapper.getByPid(propertys.getId().toString()));
		return propertys;
	}

	@Transactional
	public int delete(String id) {
		return propertysMapper.delete(id);
	}

	public List<Propertys> getAll() {
		List<Propertys> propertys = propertysMapper.getAll();
		return propertys;
	}

	public List<Propertys> getByItemId(String itemId) {
		List<Propertys> propertys = propertysMapper.getByItemId(itemId);
		return propertys;
	}

	public List<Propertys> findByPid(String pid) {
		return propertysMapper.getByPid(pid);
	}

}
