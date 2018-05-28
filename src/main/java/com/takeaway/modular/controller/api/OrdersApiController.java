package com.takeaway.modular.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.OrderUtils;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.dto.OrdersDto;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.OrderCancles;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.OrderReserves;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.OrderCanclesService;
import com.takeaway.modular.service.OrderReservesService;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.UsersService;

/**
 * 会员信息接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/orders")
@Api(value = "我的订单信息---小程序接口", description = "OrdersApiController")
public class OrdersApiController {
	private static final Logger log = Logger
			.getLogger(OrdersApiController.class);

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderCanclesService orderCanclesService;

	@Autowired
	private OrderReservesService orderReservesService;
	
	@Autowired
	private UsersService usersService;

	@ApiOperation(value = "列表", httpMethod = "GET", notes = "获取用户所有订单")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "openid", value = "openid", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			String openid) {
		PageBounds bounds = new PageBounds(page, 10);
		
		Users user=usersService.getByOpenid(openid);
		String userId=user.getId().toString();
		List<Orders> orders = ordersService.getAllByUserId(bounds,userId); // 所有订单
		List<Orders> payOrders = ordersService.getAllByPay(bounds,userId); // 待付款
		List<Orders> shipOrders = ordersService.getAllByShip(bounds,userId); // 待收货
		List<Orders> appraisesOrders = ordersService.getAllByAppraises(bounds,userId); // 待点评
		List<Orders> refundOrders = ordersService.getAllByRefund(bounds,userId); // 退款/售后
		JSONObject result = new JSONObject();
		result.put("orders", orders);
		result.put("payOrders", payOrders);
		result.put("shipOrders", shipOrders);
		result.put("appraisesOrders", appraisesOrders);
		result.put("refundOrders", refundOrders);

		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "商户店铺信息查询", result);

	}

	@ApiOperation(value = "详情", httpMethod = "GET", notes = "订单信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public JSONObject details(HttpServletRequest request,
			HttpServletResponse response, String id) {

		Orders orders = ordersService.getById(id);

		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("orders", orders);
		return ErrorEnums.getResult(ErrorEnums.SUCCESS, "获取编辑对象", result);
	}

	@ApiOperation(value = "计算订单价格", httpMethod = "POST", notes = "计算订单价格")
	@RequestMapping(value = "/computePrice", method = RequestMethod.POST)
	public JSONObject computePrice(HttpServletRequest request,
			HttpServletResponse response, @RequestBody 	Orders orders) {
		try {
			JSONObject result = new JSONObject();
			Map money = ordersService.computePrice(orders);
			result.put("money", money);
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "计算", result);
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "计算", null);
		}

	}
	
/*	@ApiOperation(value = "下单", httpMethod = "POST", notes = "新增会员订单信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONObject save(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Orders orders) {
		try {
			JSONObject result = ordersService.save(orders);
			WebSocketServer.sendInfo(orders.getMerchantId().toString(),result.toJSONString());
			return result;
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}*/

	@ApiOperation(value = "催单申请", httpMethod = "GET", notes = "新增会员订单催单申请")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/reminderOrder", method = RequestMethod.GET)
	public JSONObject reminderOrder(HttpServletRequest request,
			HttpServletResponse response, String orderNo) {
			Orders orders = ordersService.getByOrderNo(orderNo);
			if (orders == null) {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "订单不存在,催单申请",
						null);
			}

			orders.setIsReminder(1);
			orders.setReminderDate(new Date());
			JSONObject result = ordersService.update(orders);
			
			JSONObject json = new JSONObject();
			json.put("code", 200);
			json.put("type", 3);
			json.put("order", orders);
			log.info("开始发送websocket消息........"+json.toJSONString());
		try {
				WebSocketServer.sendInfo(orders.getMerchantId().toString(),json.toJSONString());
			return result;
		} catch (Exception e) {
			return result;
		}

	}

	@ApiOperation(value = "预定申请", httpMethod = "GET", notes = "新增会员订单预定申请")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "数量", required = false, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "下单人名称", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "startDeliveryTime", value = "预定开始时间", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "hopeDeliveryTime", value = "期望送达时间", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "limitDeliveryTime", value = "送达限制时间", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "totalPrice", value = "订单总金额", required = true, dataType = "Double", paramType = "query") })
	@RequestMapping(value = "/reserveOrder", method = RequestMethod.GET)
	public JSONObject reserveOrder(HttpServletRequest request,
			HttpServletResponse response, String orderNo, Integer size,
			String name, String startDeliveryTime, String hopeDeliveryTime,
			String limitDeliveryTime, Double totalPrice) {
		try {
			Orders orders = ordersService.getByOrderNo(orderNo);
			if (orders == null) {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "订单不存在,预定申请",
						null);
			}

			String reserveNo = RandomSequence.getSixteenRandomVal(); // 预定单编号
			OrderReserves orderReserves = new OrderReserves();
			orderReserves.setOrderId(orders.getId());
			orderReserves.setReserveNo(reserveNo);
			orderReserves.setSize(size);
			orderReserves.setName(name);
			orderReserves.setStartDeliveryTime(DateUtil.parseDatetimeString(startDeliveryTime));
			orderReserves.setHopeDeliveryTime(DateUtil.parseDatetimeString(hopeDeliveryTime));
			orderReserves.setLimitDeliveryTime(DateUtil.parseDatetimeString(limitDeliveryTime));
			orderReserves.setTotalPrice(totalPrice);
			orderReserves.setCreatedAt(new Date());
			orderReserves.setOperAt(new Date());
			JSONObject result=orderReservesService.save(orderReserves); // 保存申请预定单
			
			orders.setIsReservation(1);
			orders.setReservationDate(new Date());
			ordersService.update(orders);	//修改订单状态为预定状态

			return result;
		} catch (Exception e) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增", null);
		}

	}

	@ApiOperation(value = "退款申请", httpMethod = "GET", notes = "新增会员订单退款申请")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query")
			})
	@RequestMapping(value = "/cancleOrder", method = RequestMethod.GET)
	public JSONObject saveCancleOrder(HttpServletRequest request,
			HttpServletResponse response, String orderNo) {

			Orders orders = ordersService.getByOrderNo(orderNo);
			if (orders == null) {
				return ErrorEnums.getResult(ErrorEnums.ERROR, "订单不存在,退款申请",
						null);
			}

			String refundNo = OrderUtils.generateTradeNo(); // 退单编号
			OrderCancles orderCancles = new OrderCancles();
			orderCancles.setOrderId(orders.getId());
			orderCancles.setRefundNo(refundNo);
			orderCancles.setCreatedAt(new Date());
			orderCancles.setOperAt(new Date());
			
			JSONObject result=orderCanclesService.save(orderCancles);	// 保存申请退款单
			
			JSONObject json = new JSONObject();
			json.put("code", 200);
			json.put("type", 2);
			json.put("order", orderCancles);
		try {
			WebSocketServer.sendInfo(orders.getMerchantId().toString(),json.toJSONString());
			return result;
		} catch (Exception e) {
			return result;
		}

	}
	
	@ApiOperation(value = "确认收货", httpMethod = "GET", notes = "会员订单确认收货")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query")
			})
	@RequestMapping(value = "/confirmReceipt", method = RequestMethod.GET)
	public JSONObject confirmReceipt(HttpServletRequest request,
			HttpServletResponse response, String orderNo) {

		try {
			Orders orders = ordersService.getByOrderNo(orderNo);
			orders.setStatus(4);	//	1 待支付。2 待发货。  3 待收货 4 待评价  5 已完成  6退款/售后
			orders.setIsReceipt(1);	// 0：未收货;1：已收货;
			orders.setReceiptDate(new Date());	// 收货时间
			return ordersService.update(orders);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新", null);
		}

	}

}
