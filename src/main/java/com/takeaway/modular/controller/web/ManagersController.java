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
import com.takeaway.modular.dao.dto.ManagersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.service.ManagersService;



/**
 * 系统用户管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/managers")
public class ManagersController {
	private static final Logger log = Logger.getLogger(ManagersController.class); 
	
    @Autowired
    private ManagersService managersService;
	
    /**
     * 分页查询用户
     * @param request
     * @param response
     * @param page
     * @param rows
     * @param dto
     * @return
     */
	@RequestMapping(value ="/page",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> page(HttpServletRequest request,HttpServletResponse response,
			Integer page,Integer rows,ManagersDto dto){
    	Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		} 
		
		if(page==null){
			page = 1;
		}
		if(rows==null){
			rows = 10;
		}
		
		PageBounds bounds = new PageBounds(page,rows);
		
		PageResult<Managers> result = managersService.findPage(bounds,dto);
		
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
	 * 编辑用户
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/edit",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> edit(HttpServletRequest request,HttpServletResponse response,String id){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			retData.put("data", "");
			return retData;
		}
		
		Managers user=managersService.getById(id);
		retData.put("code", "0");
		retData.put("msg", "获取编辑对象成功");
		retData.put("data", user);

		return retData;
	}	
	
	/**
	 * 保存用户
	 * @param o
	 * @param r
	 * @return
	 */
	@RequestMapping(value ="/save",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> save(HttpServletRequest request,HttpServletResponse response,Managers user){		
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		try {
			retData=managersService.saveOrUpdate(user);
			return retData;
		} catch (Exception e) {
			e.printStackTrace();
			retData.put("code", "1");
			retData.put("msg", "系统出现异常");
			return retData;
		}
		
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="/delete",method = { RequestMethod.POST,RequestMethod.GET})
	public Map<String,Object> delete(HttpServletRequest request,HttpServletResponse response,String id){
		Map<String,Object> retData=new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Managers u = (Managers)session.getAttribute("s_user");
		if(u==null){
			retData.put("code", "2");
			retData.put("msg", "用户已超时，请退出登录");
			return retData;
		}
		
		try {
			int result=managersService.delete(id);
			if(result>0){
				retData.put("code", "0");
				retData.put("msg", "删除用户成功");
			}else{
				retData.put("code", "1");
				retData.put("msg", "删除用户失败");
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
	 * 批量删除
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
			if(managersService.delBatch(ids)){
				retData.put("code", "0");
				retData.put("msg", "批量删除用户成功");
				return retData;
			}else{
				retData.put("code", "1");
				retData.put("msg", "批量删除用户失败");
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
