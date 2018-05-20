package com.takeaway.modular.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.websocket.WebSocketServer;
import com.takeaway.modular.dao.dto.BossReportDto;
import com.takeaway.modular.dao.dto.BusinessReportDto;
import com.takeaway.modular.dao.dto.CouponsDto;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.dto.OrderItemsDto;
import com.takeaway.modular.dao.dto.OrdersDto;
import com.takeaway.modular.dao.dto.ReportDto;
import com.takeaway.modular.dao.mapper.ActivitysMapper;
import com.takeaway.modular.dao.mapper.CouponMerchantsMapper;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.UserCouponsMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.Users;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;

	@Autowired
	private OrderItemsMapper orderItemsMapper;

	@Autowired
	private CouponsMapper couponsMapper;

	@Autowired
	private UserCouponsMapper userCouponsMapper;
	
	@Autowired
	private MerchantsMapper merchantsMapper;
	
	@Autowired
	private CouponMerchantsMapper couponMerchantsMapper;
	
	@Autowired
	private ActivitysMapper activitysMapper;
	
	@Autowired
	private ItemPropertysMapper itemPropertysMapper;
	
	
	public PageResult<OrdersDto> findPage(PageBounds bounds, OrdersDto dto) {
		PageList<OrdersDto> orders = ordersMapper.findPage(bounds, dto);
		for (OrdersDto order : orders) {
			order.setOrderItems(orderItemsMapper.getByOrderId(order.getId()
					.toString()));
		}
		return new PageResult<OrdersDto>(orders);
	}

	public PageResult<ReportDto> reportQuery(PageBounds bounds, ReportDto dto) {
		PageList<ReportDto> report = ordersMapper.reportQuery(bounds, dto);
		return new PageResult<ReportDto>(report);
	}

	public PageResult<BusinessReportDto> businessReport(PageBounds bounds,
			BusinessReportDto dto) {
		PageList<BusinessReportDto> report = ordersMapper.businessReport(
				bounds, dto);
		return new PageResult<BusinessReportDto>(report);
	}

	public BossReportDto bossReport(BossReportDto dto) {
		BossReportDto report = ordersMapper.bossReport(dto);
		return report;
	}

	public List<ReportDto> reportExport(ReportDto dto) {
		List<ReportDto> report = ordersMapper.reportExport(dto);
		return report;
	}

	public PageResult<OrderItemsDto> salesReportQuery(PageBounds bounds,
			OrderItemsDto dto) {
		PageList<OrderItemsDto> report = orderItemsMapper.reportQuery(bounds,
				dto);
		return new PageResult<OrderItemsDto>(report);
	}

	public PageResult<Orders> findReminderPage(PageBounds bounds, OrdersDto dto) {
		PageList<Orders> orders = ordersMapper.findReminderPage(bounds, dto);
		for (Orders order : orders) {
			order.setOrderItems(orderItemsMapper.getByOrderId(order.getId()
					.toString()));
		}
		return new PageResult<Orders>(orders);
	}

	@Transactional
	public JSONObject save(Orders orders) {
		int result;

		String orderNo = RandomSequence.getSixteenRandomVal(); // 订单编号
		orders.setOrderNo(orderNo);
		orders.setStatus(0); // 待处理
		orders.setIsPay(0); // 未支付
		orders.setIsShip(0); // 未发货
		orders.setIsReceipt(0); // 未收货
		orders.setIsReceived(0); // 未接单
		orders.setIsRefund(0); // 未退款
		orders.setIsReservation(0); // 未预定
		orders.setIsReminder(0); // 未催单
		orders.setIsDistribution(0); // 配送中
		orders.setIsInvoice(0); // 是否需要发票
		orders.setIsAppraises(0); // 是否点评
		orders.setCreatedAt(new Date());
		
		orders.setPlatformCommission(0.0);
		Integer orderId = orders.getId();
		
		result = ordersMapper.save(orders);		
		for (OrderItems orderItems : orders.getOrderItems()) {
			ItemsDto items=itemsMapper.getById(orderItems.getItemId().toString());
			orderItems.setOrderId(orderId);
			orderItems.setItemPrice(Double.parseDouble(items.getPrice()));
			orderItems.setItemType(Integer.parseInt(items.getItemType()));
			orderItems.setItemTypeName(items.getItemTypeName());
			orderItems.setItemName(items.getName());
			orderItemsMapper.save(orderItems);
		}

	//	JSONObject ret = new JSONObject();
	//	ret.put("orders", orders);

		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单", null);
		}
	}

	public Map computePrice(Orders orders){
		Map ret=new HashMap<String,Double>();
		Double realTotalMoney=0.0;
		Double packingCharge=0.0;
		String merchantId=orders.getMerchantId().toString();
		Merchants merchants=merchantsMapper.getById(merchantId);

		for(OrderItems orderItem : orders.getOrderItems()){
			ItemsDto items=itemsMapper.getById(orderItem.getItemId().toString());	// 查询数据库对应商品
			realTotalMoney+=Double.parseDouble(items.getPrice())*orderItem.getItemNums();	//	增加商品价格*数量
			if(orderItem.getItemPropertys()!=null){
				for(ItemPropertys ItemPropertys:orderItem.getItemPropertys()){
					ItemPropertys ItemProperty=itemPropertysMapper.getByItemIdAndPropertyId(ItemPropertys);	// 查询数据库对应属性
					realTotalMoney+=ItemProperty.getPrice()*orderItem.getItemNums();	// 增加商品属性价格*数量 
				}
			}
			packingCharge+=Double.parseDouble(items.getPackingCharge())*orderItem.getItemNums();	//	增加打包费*数量
		}
		
		List<Activitys> activitys=activitysMapper.getByMerchantId(merchantId);
		Double maxActivityMoney = 0.0;
		String maxActivityName="";
		for(Activitys activity:activitys){
			if(realTotalMoney>=activity.getFullMoney()){
			//	realTotalMoney-=activity.getReduceMoney();
				maxActivityMoney= (maxActivityMoney > activity.getReduceMoney()) ? maxActivityMoney : activity.getReduceMoney();
				if(maxActivityMoney==activity.getReduceMoney()){
					maxActivityName=activity.getName();
				}
			}
		}
		realTotalMoney=realTotalMoney-maxActivityMoney;	// 扣减活动价
		
		if(orders.getCoupons()!=null){
			for(Coupons coupon:orders.getCoupons()){
				CouponsDto couponsDto=couponsMapper.getById(coupon.getId().toString());	// 查询数据库对应优惠券
				Double spendMoney=Double.parseDouble(couponsDto.getSpendMoney());	// 最低消费金额
				if(realTotalMoney>=spendMoney){
					realTotalMoney-=Double.parseDouble(couponsDto.getCouponMoney());	// 优惠券实际面额
				}
			}
		}
		
	//	realTotalMoney=realTotalMoney+packingCharge;	//	打包费
	//	Double distributionFee=merchants.getDistributionFee();	
	//	realTotalMoney=realTotalMoney+distributionFee;	// 运费
		
		ret.put("realTotalMoney", realTotalMoney);
		ret.put("distributionFee", merchants.getDistributionFee());	// 运费
		ret.put("packingCharge", packingCharge);	//	打包费
		ret.put("maxActivityMoney", maxActivityMoney);	//	活动最大减免费
		ret.put("maxActivityName", maxActivityName);	//	活动最大减免费名称
		return ret;
	}
	
	@Transactional
	public JSONObject update(Orders orders) {
		int result;
		orders.setUpdatedAt(new Date());
		result = ordersMapper.update(orders);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单", null);
		}
	}

	@Transactional
	public JSONObject updateDistribution(Orders orders) {
		int result;
		orders.setUpdatedAt(new Date());
		result = ordersMapper.update(orders);
		Integer userId = orders.getUserId();
		Users users = usersMapper.getById(userId.toString());
		int userScore = orders.getRealTotalMoney().intValue();
		users.setUserScore(users.getUserScore() + userScore);
		usersMapper.update(users);

		CouponsDto couponsDto = new CouponsDto();
		couponsDto.setMerchantId(orders.getMerchantId().toString());
		couponsDto.setCouponSendType("2"); // 实付满多少送券
		List<Coupons> coupons = couponsMapper.getByCouponSendType(couponsDto);
		for (Coupons coupon : coupons) {
			if (orders.getRealTotalMoney() >= coupon.getFullMoney()) {
				UserCoupons userCoupons = new UserCoupons();
				userCoupons.setUserId(userId);
				userCoupons.setCouponId(coupon.getId());
				userCoupons.setAmount(1); // 数量
				userCoupons.setStatus(1); // 使用状态(1:未用，0：已用 -1:删除)
				userCoupons.setIsFlag(1); // 有效状态(1:有效 -1:删除)
				userCoupons.setType(2); // 优惠券类型
				userCoupons.setGainTime(new Date());
				userCouponsMapper.save(userCoupons);
			}
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "更新订单", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "更新订单", null);
		}
	}

	public Orders getById(String id) {
		Orders orders = ordersMapper.getById(id);
		orders.setOrderItems(orderItemsMapper.getByOrderId(id));
		return orders;
	}

	public Orders getByOrderNo(String orderNo) {
		return ordersMapper.getByOrderNo(orderNo);
	}

	public List<Orders> getAllByUserId(String userId) {
		return ordersMapper.getByUserId(userId);
	}

	public List<Orders> getAllByPay(String userId) {
		return ordersMapper.getByPay(userId);
	}

	public List<Orders> getAllByShip(String userId) {
		return ordersMapper.getByShip(userId);
	}

	public List<Orders> getAllByAppraises(String userId) {
		return ordersMapper.getByAppraises(userId);
	}

	public List<Orders> getAllByRefund(String userId) {
		return ordersMapper.getByRefund(userId);
	}

	public List<OrderItems> getByOrderId(String orderId) {
		return orderItemsMapper.getByOrderId(orderId);
	}

	public JSONObject OrderIndex() {
		JSONObject result = new JSONObject();
		result.put("processing", ordersMapper.getProcessing());
		result.put("refunding", ordersMapper.getRefunding());
		result.put("todayOrders", ordersMapper.getTodayOrders());
		result.put("todayTurnover", ordersMapper.getTodayTurnover());
		return result;
	}

	@Transactional
	public int delete(String id) {
		return ordersMapper.delete(id);
	}

}
