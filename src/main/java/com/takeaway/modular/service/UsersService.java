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
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Users;


/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;

	public Users login(UsersDto dto) {
		dto.setPasswordHash(MD5Util.MD5(dto.getPasswordHash()));
		Users u = usersMapper.login(dto);
		return u;
	}

	public PageResult<Users> findPage(PageBounds bounds,UsersDto dto) {
	    PageList<Users> users=usersMapper.findPage(bounds,dto);
		return new PageResult<Users>(users);
	}

	@Transactional
	public Map<String,Object> saveOrUpdate(Users user) {
		int result;
		Map<String,Object> retData=new HashMap<String,Object>();
		
		//默认密码，md5加密
		user.setPasswordHash(MD5Util.MD5(user.getPasswordHash()));
		
		if(user.getId()!=null){
			user.setUpdatedAt(new Date());
			result = usersMapper.update(user);
			
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
			int count=usersMapper.checkLoginName(user.getName());
			if(count>0){
				retData.put("code", "1");
				retData.put("msg", "账户名称已经存在");
				return retData;
			}
			
			user.setCreatedAt(new Date());
			result = usersMapper.save(user);
			
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

	public Users getById(String id) {
		return usersMapper.getById(id);
	}

	@Transactional
	public int delete(String id) {
		return usersMapper.delete(id);
	}

	@Transactional
	public boolean delBatch(String[] ids) {
		int ret=0;
		for(String id:ids){
			int count=usersMapper.delete(id);
			ret=ret+count;
		}
		if(ret==ids.length){
			return true;
		}else{
			return false;
		}
	}


}
