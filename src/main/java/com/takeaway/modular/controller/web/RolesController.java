package com.takeaway.modular.controller.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.commons.page.Order;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.modular.dao.dto.RolesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Menus;
import com.takeaway.modular.dao.model.Roles;
import com.takeaway.modular.service.MenusService;
import com.takeaway.modular.service.RolesService;

/**
 * 系统角色管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
	private static final Logger log = Logger.getLogger(RolesController.class);

	@Autowired
	private RolesService rolesService;

	@Autowired
	private MenusService menusService;

	/**
	 * 分页查询角色
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/page", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> page(HttpServletRequest request,
			HttpServletResponse response, Integer page, Integer rows,
			RolesDto dto) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}

		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = 10;
		}

		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Roles> result = rolesService.findPage(bounds, dto);

		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("page", result.getPaginator().getPage());
		retData.put("rows", result.getPaginator().getLimit());
		retData.put("totalCount", result.getPaginator().getTotalCount());
		retData.put("dto", dto);
		retData.put("data", result.getPageList());

		return retData;
	}

	/**
	 * 角色列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> list(HttpServletRequest request,
			HttpServletResponse response, Integer page, Integer rows,
			RolesDto dto) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}

		List<Roles> result = rolesService.getAll();

		retData.put("code", "0");
		retData.put("msg", "查询成功");
		retData.put("data", result);

		return retData;
	}

	/**
	 * 编辑角色
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}

		Roles role = rolesService.getById(id);
		retData.put("code", "0");
		retData.put("msg", "获取编辑对象成功");
		retData.put("data", role);

		return retData;
	}

	/**
	 * 角色权限编辑
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/menuSetup", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> menuSetup(HttpServletRequest request,
			HttpServletResponse response, String roleId) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}

		// 获取系统所有菜单权限
		List<Menus> allList = menusService.getAll();
		// 获取用户已分配的菜单权限
		List<Menus> ownList = menusService.getByRoleId(roleId);

		retData.put("code", "0");
		retData.put("msg", "获取菜单信息列表成功");
		retData.put("allList", allList);
		retData.put("ownList", ownList);
		retData.put("roleId", roleId);

		return retData;
	}

	/**
	 * 保存角色权限
	 * 
	 * @param menuIds
	 * @param roleId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveRoleMenuSetup",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> saveRoleMenuSetup(String[] menuIds,String roleId,HttpServletRequest request,HttpServletResponse response){		
	   	Map<String,Object> retData=new HashMap<String,Object>();
	   	
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		
		if(menuIds==null || menuIds.length<1){
			retData.put("code", "1");
			retData.put("msg", "未选择添加的菜单项");
			return retData;
		}
		
		if(rolesService.saveRoleMenu(menuIds, roleId)){
			retData.put("code", "0");
			retData.put("msg", "保存角色权限成功");
			return retData;
		}else{
			retData.put("code", "1");
			retData.put("msg", "保存角色权限失败");
			return retData;
		}

		
	}

	/**
	 * 保存角色
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> save(HttpServletRequest request,
			HttpServletResponse response, Roles role) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		try {
			retData = rolesService.saveOrUpdate(role);
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}

	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Map<String, Object> delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		Map<String, Object> retData = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if (u == null) {
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}

		try {
			int result = rolesService.delete(id);
			if (result > 0) {
				retData.put("code", "0");
				retData.put("msg", "删除角色成功");
			} else {
				retData.put("code", "1");
				retData.put("msg", "删除角色失败");
			}
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}

	}
	
	/**
	 * 批量删除角色
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/batchDelete",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> batchDelete(String[] ids,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		
		try {
			if(rolesService.delBatch(ids)){
				retData.put("code", "0");
				retData.put("msg", "批量删除角色成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "批量删除角色失败");
				return retData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}
		
	}
	
}
