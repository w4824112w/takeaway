package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.mapper.ManagersMapper;
import com.takeaway.modular.dao.model.Managers;


/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ManagersService {
	@Autowired
	private ManagersMapper managersMapper;

	public Managers login(ManagersDto dto) {
		dto.setPasswordHash(MD5Util.MD5(dto.getPasswordHash()));
		Managers u = managersMapper.login(dto);
		return u;
	}

	public PageResult<Managers> findPage(PageBounds bounds,ManagersDto dto) {
	    PageList<Managers> users=managersMapper.findPage(bounds,dto);
		return new PageResult<Managers>(users);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Managers user) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		//默认密码，md5加密
		user.setPasswordHash(MD5Util.MD5(user.getPasswordHash()));
		
		if(user.getId()!=null){
			user.setUpdatedAt(new Date());
			result = managersMapper.update(user);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "修改用户成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "修改用户失败");
				return retData;
			}
		}else{
			int count=managersMapper.checkLoginName(user.getName());
			if(count>0){
				retData.put("code", "1");
				retData.put("msg", "账户名称已经存在");
				return retData;
			}
			
			user.setCreatedAt(new Date());
			result = managersMapper.save(user);
			
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "新增用户成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "新增用户失败");
				return retData;
			}
		}
		
		
	}

	public Managers getById(String id) {
		return managersMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		return managersMapper.delete(id);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret=0;
		for(String id:ids){
			int count=managersMapper.delete(id);
			ret=ret+count;
		}
		if(ret==ids.length){
			return true;
		}else{
			return false;
		}
	}


}
