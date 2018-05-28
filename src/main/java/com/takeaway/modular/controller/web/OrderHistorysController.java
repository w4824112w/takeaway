package com.takeaway.modular.controller.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.dao.dto.OrderHistorysDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.OrderHistorys;
import com.takeaway.modular.service.OrderHistorysService;

/**
 * 订单历史管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order_historys")
@Api(value = "订单历史管理", description = "OrderHistorysController")
public class OrderHistorysController {
	private static final Logger log = Logger.getLogger(OrderHistorysController.class);

	@Autowired
	private OrderHistorysService orderHistorysService;

	/**
	 * 分页查询订单历史
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "查询", httpMethod = "GET", notes = "分页查询订单历史信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "orderNo", value = "订单历史编号", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "merchantId", value = "店铺id", required = false, dataType = "String", paramType = "query")})
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public JSONObject page(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String orderNo,String merchantId) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		OrderHistorysDto dto = new OrderHistorysDto();
		if (u.getType() != 1) {
			dto.setMerchantId(u.getMerchantId().toString());
		}else{
			dto.setMerchantId(merchantId);
		}
		dto.setOrderNo(orderNo);
		PageBounds bounds = new PageBounds(page, rows);
		PageResult<OrderHistorys> orders = orderHistorysService.findPage(bounds, dto);

		JSONObject result = new JSONObject();
		result.put("orderNo", orderNo);
		result.put("page", orders.getPaginator().getPage());
		result.put("rows", orders.getPaginator().getLimit());
		result.put("totalCount", orders.getPaginator().getTotalCount());
		result.put("orders", orders.getPageList());
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "查询", result);
	}

	/**
	 * 编辑订单历史
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "编辑", httpMethod = "GET", notes = "编辑订单历史信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单历史id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public JSONObject edit(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		OrderHistorys orders = orderHistorysService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orders);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}
	

/*	@ApiOperation(value = "新增", httpMethod = "POST", notes = "新增订单历史信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "merchantId", value = "商户id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "userId", value = "会员id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "orderType", value = "订单历史类型", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "size", value = "商品数量", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "totalPrice", value = "商品总金额", required = true, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "itemName", value = "商品名称", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "itemPrice", value = "商品价格", required = false, dataType = "Double", paramType = "form"),
			@ApiImplicitParam(name = "merchantType", value = "商户类型", required = true, dataType = "Integer", paramType = "form"),
			@ApiImplicitParam(name = "remark", value = "备注", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "payType", value = "支付类型", required = true, dataType = "Integer", paramType = "form"),
			})*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, Integer merchantId, Integer userId,
			Integer orderType, Integer size, Double totalPrice, Integer itemId,
			String itemName, Double itemPrice, Integer merchantType,
			String remark, Integer payType) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			OrderHistorys orders = new OrderHistorys();
			String orderNo = RandomSequence.getSixteenRandomVal();	//订单历史编号
			orders.setOrderNo(orderNo);
			orders.setMerchantId(merchantId);
			orders.setUserId(userId);
			orders.setOrderType(orderType);
			orders.setTotalPrice(totalPrice);
			orders.setMerchantType(merchantType);
			orders.setRemark(remark);
			orders.setPayType(payType);
			orders.setStatus(0); // 待处理
			orders.setIsPay(0);	// 未支付
			orders.setIsShip(0);	// 未发货
			orders.setIsReceipt(0);	// 未收货
			orders.setIsReceived(0);	// 未接单
			orders.setIsRefund(0);	// 未退款
			orders.setIsReservation(0);	// 未预定
			orders.setIsReminder(0);	// 未催单
			orders.setIsDistribution(0);	// 配送中
			orders.setIsInvoice(0);	// 是否需要发票
			orders.setIsAppraises(0);	// 是否点评
			return orderHistorysService.save(orders);
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

/*	@ApiOperation(value = "更新", httpMethod = "POST", notes = "更新订单历史信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "订单历史id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "status", value = "订单历史状态", required = false, dataType = "Integer", paramType = "form") })*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JSONObject update(HttpServletRequest request,
			HttpServletResponse response, String id, Integer status) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			OrderHistorys orders = orderHistorysService.getById(id);
			orders.setStatus(status);
			return orderHistorysService.update(orders);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

/*	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除订单历史信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单历史id", required = true, dataType = "String", paramType = "form") })*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONObject delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		HttpSession session = request.getSession();
		Managers u = (Managers) session.getAttribute("s_user");
		if (u == null) {
			return ErrorEnums.getResult(ErrorEnums.OVERTIME, "用户已超时，请退出登录", null);
		}

		try {
			int result = orderHistorysService.delete(id);
			if (result > 0) {
				return ErrorEnums.getResult(ErrorEnums.SUCCESS, "删除订单历史", null);
			} else {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "删除订单历史", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "删除订单历史", null);
		}

	}

}
