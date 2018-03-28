package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takeaway.commons.utils.MD5Util;
import com.takeaway.modular.dao.dto.UsersDto;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Users;

/**
 * 
 * @author hk
 *
 */
@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;

	/**
	 * 
	 * @param prison
	 * @param username
	 * @param password
	 * @return
	 */
	public UsersDto login(String prison, String username,
			String password) {
		UsersDto dto = new UsersDto();
		dto.setPrison(prison);
		dto.setUsername(username);
		dto.setMd5Password(password);

		dto.setMd5Password(MD5Util.MD5(dto.getMd5Password()));
		UsersDto users = usersMapper.login(dto);
		return users;
	}
	
	public UsersDto isSysLogin( String username,
			String password) {
		UsersDto dto = new UsersDto();
		dto.setUsername(username);
		dto.setMd5Password(password);

		dto.setMd5Password(MD5Util.MD5(dto.getMd5Password()));
		UsersDto users = usersMapper.isSysLogin(dto);
		return users;
	}
	
	public Users getUsersByConditions(String jailId, String username,
			String password) {
		UsersDto dto = new UsersDto();
		dto.setJailId(jailId);
		dto.setUsername(username);
		dto.setMd5Password(password);

		dto.setMd5Password(MD5Util.MD5(dto.getMd5Password()));
		Users users = usersMapper.getUsersByConditions(dto);
		return users;
	}

	@Transactional
	public Map<String, Object> saveOrUpdate(Users user) {
		int result;
		Map<String, Object> retData = new HashMap<String, Object>();

		// 默认密码，md5加密
		user.setMd5Password(MD5Util.MD5(user.getMd5Password()));

		if (user.getId() != null) {
			user.setUpdatedAt(new Date());
			result = usersMapper.update(user);

			if (result > 0) {
				retData.put("code", "0");
				retData.put("msg", "修改用户成功");
				return retData;
			} else {
				retData.put("code", "1");
				retData.put("msg", "修改用户失败");
				return retData;
			}
		} else {
			int count = usersMapper.checkLoginName(user.getUsername());
			if (count > 0) {
				retData.put("code", "1");
				retData.put("msg", "账户名称已经存在");
				return retData;
			}

			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			user.setSalt("$10$BiUJM6WYEfMsx7c1dSqBBe");
			user.setHashedPassword("$10$BiUJM6WYEfMsx7c1dSqBBe4b1VyqMWLmc5/qNs/RV2sW9tTdvbhUi");
			result = usersMapper.save(user);

			if (result > 0) {
				retData.put("code", "0");
				retData.put("msg", "新增用户成功");
				return retData;
			} else {
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
		int ret = 0;
		for (String id : ids) {
			int count = usersMapper.delete(id);
			ret = ret + count;
		}
		if (ret == ids.length) {
			return true;
		} else {
			return false;
		}
	}

	public int deleteUser(Integer id) {
		Date updateAt = new Date();
		return usersMapper.deleteUser(id, updateAt);
	}

	public PageResult<Users> findPage(int page, int rows, String jail, String username, Integer role) {

		PageBounds bounds = new PageBounds(page, rows);
		Map map=new HashMap();
		map.put("jail", jail);
		map.put("username", username);
		map.put("role", role);
		PageList<Users> users = usersMapper.getUserList(bounds, map);
		return new PageResult<Users>(users);
	}
}
