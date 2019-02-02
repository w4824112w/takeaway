package com.takeaway.modular.controller.api;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.netpay.wxpay.api.PayUtils;
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.UserAddress;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.UserRanks;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.DistributionsService;
import com.takeaway.modular.service.OrdersService;
import com.takeaway.modular.service.UserAddressService;
import com.takeaway.modular.service.UsersService;

/**
 * 闪送配送接口
 * 
 * @author hk
 *
 */
@RestController
@RequestMapping("/api/distributions")
@Api(value = "闪送配送接口---小程序接口", description = "DistributionsApiController")
public class DistributionsApiController {
	private static final Logger log = Logger
			.getLogger(DistributionsApiController.class);

	@Autowired
	private DistributionsService distributionsService;

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "/syncstatus", method = RequestMethod.GET)
	public JSONObject syncstatus(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "partnerno") String partnerno,
			@PathVariable(value = "orderno") String orderno,
			@PathVariable(value = "mobile") String mobile,
			@PathVariable(value = "signature") String signature,
			@PathVariable(value = "issorderno") String issorderno,
			@PathVariable(value = "status") String status,
			@PathVariable(value = "statuscode") String statuscode)
			throws IOException {
		log.info("partnerno--" + partnerno + "--orderno--" + orderno
				+ "--mobile--" + mobile + "--signature--" + signature
				+ "--issorderno--" + issorderno + "--status--" + status
				+ "--statuscode--" + statuscode);
		log.info("闪送返回订单状态回调start.....");

		Orders orders = ordersService.getByOrderNo(orderno);
		JSONObject result = new JSONObject();
		if (orders != null) {
			orders.setSyncstatus(statuscode);
			ordersService.update(orders);

			if (StringUtils.isNotBlank(statuscode) && statuscode.equals("30")) {
				log.info("闪送待取件推送消息给打印机.....");
				Map<String, Object> shansong_order = distributionsService
						.queryOrder(orders.getOrderNo(), orders.getIssorderno());

				if (shansong_order != null) {
					log.info("闪送订单查询....." + shansong_order.toString());
					Map shansong_data = (Map) shansong_order.get("data");
					if (shansong_data != null) {
						String pickupPassword = shansong_data
								.get("pickupPassword") == null ? ""
								: shansong_data.get("pickupPassword")
										.toString();
						if (StringUtils.isNotBlank(pickupPassword)) {
							JSONObject json = new JSONObject();
							json.put("code", 200);
							json.put("type", 1);
							json.put("order", orders);
							json.put("pickupPassword", pickupPassword);

							log.info("开始发送websocket消息........"
									+ json.toJSONString());
							WebSocketServer.sendInfo(orders.getMerchantId()
									.toString(), json.toJSONString());
						}
					}
				}

			}

			result.put("status", "OK");
			result.put("errMsg", null);
			return result;
		} else {
			result.put("status", "ER");
			result.put("errMsg", "订单号不存在");
			return result;
		}
	}

	@ApiOperation(value = "订单查询", httpMethod = "GET", notes = "查询闪送订单配送状态")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/queryOrder", method = RequestMethod.GET)
	public JSONObject queryOrder(HttpServletRequest request,
			HttpServletResponse response, String orderNo) {
		try {
			Orders orders = ordersService.getByOrderNo(orderNo);
			if (StringUtils.isBlank(orders.getIssorderno())) {
				return ErrorEnums.getResult(ErrorEnums.ERROR,
						"闪送平台订单号不存在，查询配送信息", null);
			}
			Map<String, Object> result = distributionsService.queryOrder(
					orders.getOrderNo(), orders.getIssorderno());
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "闪送订单配送状态查询",
					result);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}

	}

	@ApiOperation(value = "订单轨迹", httpMethod = "GET", notes = "查询闪送订单配送轨迹")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/queryTrail", method = RequestMethod.GET)
	public JSONObject queryTrail(HttpServletRequest request,
			HttpServletResponse response, String orderNo) {
		try {
			Orders orders = ordersService.getByOrderNo(orderNo);
			if (StringUtils.isBlank(orders.getIssorderno())) {
				return ErrorEnums.getResult(ErrorEnums.ERROR,
						"闪送平台订单号不存在，查询配送信息", null);
			}
			Map<String, Object> result = distributionsService.queryTrail(
					orders.getOrderNo(), orders.getIssorderno());
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "闪送订单配送轨迹查询",
					result);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorEnums.getResult(ErrorEnums.ERROR, "系统出现异常", null);
		}
	}

}
