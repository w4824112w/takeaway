package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.CitiesDto;
import com.takeaway.modular.dao.dto.PropertysDto;
import com.takeaway.modular.dao.dto.ProvincesDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Propertys;
import com.takeaway.modular.service.PropertysService;

/**
 * 商品属性管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/propertys")
@Api(value = "商品属性管理", description = "PropertysController")
public class PropertysController {
	private static final Logger log = Logger
			.getLogger(PropertysController.class);

	@Autowired
	private PropertysService propertysService;

	/**
	 * 分页查询商品属性
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询商品属性信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "商品属性名称", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String name) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		PropertysDto dto = new PropertysDto();
		dto.setName(name);
		PageBounds bounds = new PageBounds(page, rows);

		PageResult<Propertys> propertys = propertysService
				.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("page", propertys.getPaginator().getPage());
		result.put("rows", propertys.getPaginator().getLimit());
		result.put("totalCount", propertys.getPaginator().getTotalCount());
		result.put("propertys", propertys.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 商品属性列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
//	@ApiOperation(value = "查询", httpMethod = "GET", notes = "查询商品属性列表")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query") })
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, String itemId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<Propertys> propertys = propertysService.findByPid(itemId);

		JSONObject result = new JSONObject();
		result.put("propertysSize", propertys.size());
		result.put("propertys", propertys);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商品属性查询", result);

	}
	
	@ApiOperation(value = "查询商品相关的属性列表", httpMethod = "GET", notes = "查询商品相关的属性列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/bindItemlist", method = RequestMethod.GET)
	public JSONObject bindItemlist(HttpServletRequest request,
			HttpServletResponse response,String itemId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		List<Propertys> propertys = propertysService.getByItemId(itemId);

		JSONObject result = new JSONObject();
		result.put("propertys", propertys);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}
	
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "查询父类商品属性")
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	public JSONObject parent(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<Propertys> propertys = propertysService.getAll();

		JSONObject result = new JSONObject();
		result.put("size", propertys.size());
		result.put("propertys", propertys);
		log.info("查询省份信息接口成功");
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "父类商品属性查询", result);

	}

	@ApiOperation(value = "查询", httpMethod = "GET", notes = "查询子类商品属性")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pid", value = "父类商品属性id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/children", method = RequestMethod.GET)
	public JSONObject children(HttpServletRequest request,
			HttpServletResponse response, String pid) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		
		List<Propertys> propertys = propertysService.findByPid(pid);

		JSONObject result = new JSONObject();
		result.put("size", propertys.size());
		result.put("propertys", propertys);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "子类商品属性属性查询", result);

	}
	
	/**
	 * 编辑商品属性
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑商品属性信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商品属性id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		Propertys propertys = propertysService.getById(id);

		JSONObject result = new JSONObject();
		result.put("propertys", propertys);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "编辑商品属性", result);
	}

	/**
	 * 新增商品属性
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
/*	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增商品属性信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "属性名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "value", value = "属性值", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "price", value = "价格", required = false, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "pid", value = "父类商品属性id", required = false, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "isOpen", value = "是否开启", required = false, dataType = "String", paramType = "form") })*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, String name, String value,Double price,
			Integer pid, Integer isOpen) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			Propertys propertys = new Propertys();
			propertys.setName(name);
			propertys.setPid(pid);
			return propertysService.save(propertys);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}
	
	/**
	 * 批量新增商品属性
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "批量新增", httpMethod = "POST", notes = "新增商品属性信息")
	@RequestMapping(value = "/bathcSave", method = RequestMethod.POST)
	public JSONObject bathcSave(HttpServletRequest request,
			HttpServletResponse response,@ApiParam @RequestBody List<Propertys> propertys) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			return propertysService.bathcSave(propertys);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "批量新增", null);
		}

	}
	
	@ApiOperation(value = "批量更新", httpMethod = "POST", notes = "更新商品属性信息")
	@RequestMapping(value = "/bathcUpdate", method = RequestMethod.POST)
	public JSONObject bathcUpdate(HttpServletRequest request,
			HttpServletResponse response,@ApiParam @RequestBody List<Propertys> propertys) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			return propertysService.bathcUpdate(propertys);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "批量新增", null);
		}

	}

	/**
	 * 更新商品属性
	 * 
	 * @param o
	 * @param r
	 * @return
	 */
	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新商品属性信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "商品属性id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "name", value = "属性名称", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "value", value = "属性值", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "price", value = "价格", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "pid", value = "父类商品属性id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "isOpen", value = "是否开启", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, String name, String value,Double price,
			Integer pid, Integer isOpen) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}
		try {
			Propertys propertys = propertysService.getById(id);
			propertys.setName(name);
			propertys.setPid(pid);
			return propertysService.update(propertys);

		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

	/**
	 * 删除商品属性
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除商品属性信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "商品属性id", required = true, dataType = "String", paramType = "form") })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = propertysService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除商品属性", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商品属性", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除商品属性", null);
		}

	}

}
