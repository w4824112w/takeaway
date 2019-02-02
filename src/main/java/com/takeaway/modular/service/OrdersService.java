package com.takeaway.modular.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.DateUtil;
import com.takeaway.commons.utils.MapDistance;
import com.takeaway.commons.utils.OrderUtils;
import com.takeaway.commons.utils.PreciseCompute;
import com.takeaway.commons.utils.RandomSequence;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.netpay.wxpay.utils.HttpsRequest;
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
import com.takeaway.modular.dao.mapper.MerchantPicturesMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrderCouponRecordsMapper;
import com.takeaway.modular.dao.mapper.OrderItemPropertysMapper;
import com.takeaway.modular.dao.mapper.OrderItemsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.PropertysMapper;
import com.takeaway.modular.dao.mapper.UserCouponsMapper;
import com.takeaway.modular.dao.mapper.UserScoresMapper;
import com.takeaway.modular.dao.mapper.UsersMapper;
import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.MerchantPictures;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.OrderCouponRecords;
import com.takeaway.modular.dao.model.OrderItemPropertys;
import com.takeaway.modular.dao.model.OrderItems;
import com.takeaway.modular.dao.model.Orders;
import com.takeaway.modular.dao.model.Propertys;
import com.takeaway.modular.dao.model.UserCoupons;
import com.takeaway.modular.dao.model.UserScores;
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

	@Autowired
	private PropertysMapper propertysMapper;

	@Autowired
	private MerchantPicturesMapper merchantPicturesMapper;

	@Autowired
	private UserScoresMapper userScoresMapper;

	@Autowired
	private OrderItemPropertysMapper orderItemPropertysMapper;
	
	@Autowired
	private DistributionsService distributionsService;
	
	@Autowired
	private OrderCouponRecordsMapper orderCouponRecordsMapper;

	public PageResult<OrdersDto> findPage(PageBounds bounds, OrdersDto dto) {
		PageList<OrdersDto> orders = ordersMapper.findPage(bounds, dto);
		for (OrdersDto order : orders) {
			List<OrderItems> orderItems = orderItemsMapper.getByOrderId(order
					.getId().toString());
			for (OrderItems orderItem : orderItems) {
				List<OrderItemPropertys> orderItemPropertys = orderItemPropertysMapper
						.getByOrderItemId(orderItem.getId().toString());
				for (OrderItemPropertys orderItemProperty : orderItemPropertys) {
					ItemPropertys itemPropertys = itemPropertysMapper
							.getById(orderItemProperty.getItemPropertyId()
									.toString());
					if(itemPropertys!=null){
						Propertys propertys = propertysMapper.getById(itemPropertys
								.getPropertyId().toString());
						orderItemProperty.setPrice(itemPropertys.getPrice());
						orderItemProperty.setPropertyName(propertys.getName());
					}
				}
				orderItem.setOrderItemPropertys(orderItemPropertys);
			}
			order.setOrderItems(orderItems);
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
		if(report==null){
			dto.setSettlTime(dto.getReportTime());
			dto.setTotalPrice("0");
			dto.setSuccessCount("0");
			dto.setMaxMoney("0");
			dto.setMinMoney("0");
			dto.setAccessTimes("0");
			dto.setRealTotalMoney("0");
			return dto;
		}
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

	public PageResult<Orders> findReservesPage(PageBounds bounds, OrdersDto dto) {
		PageList<Orders> orders = ordersMapper.findReservesPage(bounds, dto);
		for (Orders order : orders) {
			List<OrderItems> orderItems = orderItemsMapper.getByOrderId(order
					.getId().toString());
			for (OrderItems orderItem : orderItems) {
				List<OrderItemPropertys> orderItemPropertys = orderItemPropertysMapper
						.getByOrderItemId(orderItem.getId().toString());
				for (OrderItemPropertys orderItemProperty : orderItemPropertys) {
					ItemPropertys itemPropertys = itemPropertysMapper
							.getById(orderItemProperty.getItemPropertyId()
									.toString());
					if(itemPropertys!=null){
					Propertys propertys = propertysMapper.getById(itemPropertys
							.getPropertyId().toString());
					orderItemProperty.setPrice(itemPropertys.getPrice());
					orderItemProperty.setPropertyName(propertys.getName());
					}
				}
				orderItem.setOrderItemPropertys(orderItemPropertys);
			}
			order.setOrderItems(orderItems);
		}
		return new PageResult<Orders>(orders);
	}

	@Transactional
	public JSONObject save(Orders orders) {
		int result;

		Merchants merchants = merchantsMapper.getById(orders.getMerchantId()
				.toString());

/*		if(StringUtils.isNotBlank(orders.getUserAddress())){
			orders.setDeliverMoney(merchants.getDistributionFee()); // 运费
		}else{
			orders.setDeliverMoney(0.0); // 运费
		}*/
		
		orders.setStatus(1); // 1 待支付。2 待发货。 3 待收货 4 待评价 5 已完成 6退款/售后
		orders.setIsPay(0); // 未支付
		orders.setIsShip(0); // 未发货
		orders.setIsReceipt(0); // 未收货
		orders.setIsReceived(0); // 未接单
		orders.setIsRefund(0); // 未退款
		
		
		if(StringUtils.isNotBlank(orders.getReservationTime())){
			orders.setIsReservation(1); // 已预定
		}else{
			orders.setIsReservation(0); // 未预定
		}
		
		if (orders.getReservationDate() != null) {
			
		} else {
			orders.setIsReservation(0); // 未预定
			orders.setReservationDate(null);
		}
		
		orders.setIsReminder(0); // 未催单
		orders.setIsDistribution(0); // 配送中
		orders.setIsInvoice(0); // 是否需要发票
		orders.setIsAppraises(0); // 是否点评
		orders.setCreatedAt(new Date());

		orders.setPlatformCommission(0.0);

		result = ordersMapper.save(orders);

		Integer orderId = orders.getId();
		for (OrderItems orderItems : orders.getOrderItems()) {
			ItemsDto items = itemsMapper.getById(orderItems.getItemId()
					.toString());
			orderItems.setOrderId(orderId);
			orderItems.setItemPrice(Double.parseDouble(items.getPrice()));
			orderItems.setItemType(Integer.parseInt(items.getItemType()));
			orderItems.setItemTypeName(items.getItemTypeName());
			orderItems.setItemName(items.getName());

			List<Coupons> coupons = orders.getCoupons();
			if (coupons != null && coupons.size() > 0) {
				orderItems.setTargetId(coupons.get(0).getId());
				orderItems.setTargetName(coupons.get(0).getName());
			}
			orderItemsMapper.save(orderItems);

			if (orderItems.getItemPropertys() != null) {
				for (ItemPropertys ItemPropertys : orderItems
						.getItemPropertys()) {
					ItemPropertys.setItemId(Integer.parseInt(items.getId()));
					ItemPropertys ItemProperty = itemPropertysMapper
							.getByItemIdAndPropertyId(ItemPropertys); // 查询数据库对应属性

					OrderItemPropertys orderItemPropertys = new OrderItemPropertys();
					orderItemPropertys.setOrderItemId(orderItems.getId());
					orderItemPropertys.setItemPropertyId(ItemProperty.getId());
					orderItemPropertys.setCreatedAt(new Date());
					orderItemPropertysMapper.save(orderItemPropertys);
				}
			}

		}

		if (orders.getCoupons() != null) {
			if(orders.getUserId()==null){
				return ErrorEnums.getResult(ErrorEnums.ERROR, "下单用户id不能为空,请传入userId参数,当前传入的userId参数为:"+orders.getUserId(), null);
			}
			
			for (Coupons coupon : orders.getCoupons()) {
				CouponsDto couponsDto = couponsMapper.getById(coupon.getId()
						.toString()); // 查询数据库对应优惠券
				
				UserCoupons query=new UserCoupons();
				query.setUserId(orders.getUserId());
				query.setCouponId(coupon.getId());
				List<UserCoupons> userCoupons=userCouponsMapper.getByUserIdAndCouponId(query);
				if(userCoupons.size()<0){
					break;
				}
				
				OrderCouponRecords orderCouponRecords=new OrderCouponRecords();
				orderCouponRecords.setOrderId(orders.getId());
				orderCouponRecords.setOrderNo(orders.getOrderNo());
				orderCouponRecords.setCouponType(coupon.getCouponSendType());
				orderCouponRecords.setCouponAmout(coupon.getCouponMoney());
				orderCouponRecords.setUserId(orders.getUserId());
				orderCouponRecords.setMerchantId(orders.getMerchantId());
				orderCouponRecords.setUserCouponId(userCoupons.get(0).getId());
				orderCouponRecords.setCreatedAt(new Date());
				orderCouponRecordsMapper.save(orderCouponRecords);
			}
			
		}
		
		// JSONObject ret = new JSONObject();
		// ret.put("orders", orders);
		
		
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增订单", null);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增订单", null);
		}
	}

	public Map computeDistributionPrice(Map param){
		Map ret=new HashMap();
		
		return ret;
	}
	
	public Map computePrice(Orders orders) {
		Orders old_orders = ordersMapper.getByOrderNo(orders.getOrderNo());
		if(old_orders==null){
			String orderNo = OrderUtils.generateTradeNo(); // 订单编号
			orders.setOrderNo(orderNo); // 订单编号
		}
		
		Map ret = new HashMap<String, Double>();
		Double realTotalMoney = 0.0;	// 实际付款金额
		Double totalPrice = 0.0;	// 营业额
		Double packingCharge = 0.0;	// 打包费
		String merchantId = orders.getMerchantId().toString();
		Merchants merchants = merchantsMapper.getById(merchantId);

		for (OrderItems orderItem : orders.getOrderItems()) {
			ItemsDto items = itemsMapper.getById(orderItem.getItemId()
					.toString()); // 查询数据库对应商品
			totalPrice = PreciseCompute.add(totalPrice, Double.parseDouble(items.getPrice())
					* orderItem.getItemNums()); // 增加商品价格*数量
			realTotalMoney =  PreciseCompute.add(realTotalMoney, Double.parseDouble(items.getPrice())
					* orderItem.getItemNums()); // 增加商品价格*数量
			if (orderItem.getItemPropertys() != null) {
				for (ItemPropertys ItemPropertys : orderItem.getItemPropertys()) {
					ItemPropertys.setItemId(Integer.parseInt(items.getId()));
					ItemPropertys ItemProperty = itemPropertysMapper
							.getByItemIdAndPropertyId(ItemPropertys); // 查询数据库对应属性
					totalPrice =PreciseCompute.add(totalPrice, ItemProperty.getPrice()
							* orderItem.getItemNums()); // 增加商品属性价格*数量
					realTotalMoney = PreciseCompute.add(realTotalMoney, ItemProperty.getPrice()
							* orderItem.getItemNums()); // 增加商品属性价格*数量
				}
			}
			packingCharge =PreciseCompute.add(packingCharge, Double.parseDouble(items.getPackingCharge())
					* orderItem.getItemNums()); // 增加打包费*数量
		}

		List<Activitys> activitys = activitysMapper.getByMerchantId(merchantId);
		Double maxActivityMoney = 0.0;
		String maxActivityName = "";
		Integer maxActivityId = null;
		for (Activitys activity : activitys) {
			if (realTotalMoney >= activity.getFullMoney()) {
				// realTotalMoney-=activity.getReduceMoney();
				maxActivityMoney = (maxActivityMoney > activity
						.getReduceMoney()) ? maxActivityMoney : activity
						.getReduceMoney();
				if (maxActivityMoney == activity.getReduceMoney()) {
					maxActivityName = activity.getName();
					maxActivityId = activity.getId();
				}
			}
		}
		realTotalMoney =PreciseCompute.sub(realTotalMoney, maxActivityMoney); // 扣减活动价

		Double maxCouponMoney = 0.0;
		Integer maxCouponId = null;
		if (orders.getCoupons() != null) {
			for (Coupons coupon : orders.getCoupons()) {
				CouponsDto couponsDto = couponsMapper.getById(coupon.getId()
						.toString()); // 查询数据库对应优惠券
				Double spendMoney = Double.parseDouble(couponsDto
						.getSpendMoney()); // 最低消费金额
				if (realTotalMoney >= spendMoney) {
					maxCouponMoney = Double.parseDouble(couponsDto
							.getCouponMoney()); // 优惠券实际面额
					maxCouponId=Integer.parseInt(couponsDto.getId());
				}
			}
		}

		realTotalMoney =PreciseCompute.sub(realTotalMoney, maxCouponMoney); // 扣减优惠券

		// realTotalMoney=realTotalMoney+packingCharge; // 打包费
		// Double distributionFee=merchants.getDistributionFee();
		// realTotalMoney=realTotalMoney+distributionFee; // 运费

		orders.setPackingCharge(packingCharge);
		orders.setActivityMoney(maxActivityMoney);
		orders.setActivityId(maxActivityId);
		orders.setCouponMoney(maxCouponMoney);
		orders.setCouponId(maxCouponId);
		orders.setTotalPrice(totalPrice);

		ret.put("totalPrice", totalPrice);	// 营业额
		ret.put("realTotalMoney", realTotalMoney);	// 实际价格
		
		if(merchants.getDistributionType()==0){
			if(StringUtils.isNotBlank(orders.getUserAddress())){
				String lat=orders.getLat();
				String lng=orders.getLng();
				if(StringUtils.isNotBlank(lat)&&StringUtils.isNotBlank(lng)&&StringUtils.isNotBlank(merchants.getLat())&&StringUtils.isNotBlank(merchants.getLng())){
					int distance=Integer.parseInt(MapDistance.getDistance(lng,lat,merchants.getLng(),merchants.getLat()));
					ret.put("distance", distance); // 距离店铺多少km
					if(distance<=1000){
						orders.setDeliverMoney(6.0); // <=1km,运费6元
					}else if(distance>1000&&distance<=2000){
						orders.setDeliverMoney(7.0); // <=2km,运费7元
					}else if(distance>2000&&distance<=3000){
						orders.setDeliverMoney(8.0); // <=3km,运费8元
					}else if(distance>3000&&distance<=3500){
						orders.setDeliverMoney(10.0); // <=3.5km,运费10元
					}else if(distance>3500&&distance<=4000){
						orders.setDeliverMoney(11.0); // <=4km,运费11元
					}else if(distance>4000&&distance<=5000){
						orders.setDeliverMoney(15.0); // <=5km,运费15元
					}else if(distance>5000&&distance<=7000){
						orders.setDeliverMoney(20.0); // <=7km,运费20元
					}else if(distance>7000&&distance<=8500){
						orders.setDeliverMoney(25.0); // <=8.5km,运费25元
					}else if(distance>8500&&distance<=95000){
						orders.setDeliverMoney(30.0); // <=9.5km,运费30元
					}else{
						orders.setDeliverMoney(30.0); // >9.5km,运费30元
					}
				}else{
					ret.put("distance", 0); // 距离店铺多少km
					orders.setDeliverMoney(0.0);
				}
			}else{
				ret.put("distance", 0); // 距离店铺多少km
				orders.setDeliverMoney(0.0); // 运费
			}
			ret.put("distribution_result", "");
		}else if(merchants.getDistributionType()==1){
			if(StringUtils.isNotBlank(orders.getUserAddress())){
				String lat=orders.getLat();
				String lng=orders.getLng();
				if(StringUtils.isNotBlank(lat)&&StringUtils.isNotBlank(lng)&&StringUtils.isNotBlank(merchants.getLat())&&StringUtils.isNotBlank(merchants.getLng())){
					Map<String, Object> distribution=distributionsService.computePrice(orders, merchants);
					ret.put("distribution_result", distribution); // 闪送调用计算结果
					if(distribution.get("status").toString().equals("OK")){
						Map data=(Map) distribution.get("data");
						Double amount=BigDecimal.valueOf(Long.valueOf(data.get("amount").toString())).divide(new BigDecimal(100)).doubleValue();	//分转元
						orders.setDeliverMoney(amount); // 运费:xx元
						ret.put("distance", data.get("distance").toString()); // 距离店铺多少km
					}else{
						ret.put("distance", 0); // 距离店铺多少km
						orders.setDeliverMoney(0.0); // 运费
					}
				}else{
					ret.put("distance", 0); // 距离店铺多少km
					orders.setDeliverMoney(0.0); // 运费
					ret.put("distribution_result", "");
				}
			}else{
				ret.put("distance", 0); // 距离店铺多少km
				orders.setDeliverMoney(0.0); // 运费
				ret.put("distribution_result", "");
			}
		}else{
			ret.put("distance", 0); // 距离店铺多少km
			orders.setDeliverMoney(0.0); // 运费
			ret.put("distribution_result", "");
		}
		
		
		ret.put("distributionFee", orders.getDeliverMoney()); // 运费
		
		ret.put("packingCharge", packingCharge); // 打包费
		ret.put("maxActivityMoney", maxActivityMoney); // 活动最大减免费
		ret.put("maxActivityName", maxActivityName); // 活动最大减免费名称
		
		if(StringUtils.isBlank(orders.getUserAddress())){
			ret.put("distributionFee", 0.0); // 运费
			ret.put("packingCharge", 0.0); // 打包费
			
			orders.setDeliverMoney(0.0); // 运费
			orders.setPackingCharge(0.0);// 打包费
		}
		return ret;
	}

	@Transactional
	public JSONObject update(Orders orders) {
		int result;
		orders.setUpdatedAt(new Date());
		result = ordersMapper.update(orders);
		
		List<OrderCouponRecords> orderCouponRecords=orderCouponRecordsMapper.getByOrderNo(orders.getOrderNo());
		for(OrderCouponRecords obj:orderCouponRecords){
			UserCoupons update_dict=new UserCoupons();
			update_dict.setId(obj.getUserCouponId());
			userCouponsMapper.updateStatus(update_dict);
		}
		
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

		// 用户积分明细记录
		UserScores userScores = new UserScores();
		userScores.setUserId(userId);
		userScores.setScore(userScore);
		userScores.setDataSrc(2); // 来源(1：系统赠送;2:订单消费;)
		userScores.setDataRemarks("配送订单成功后返送相应金额的积分");
		userScores.setScoreType(1); // 积分标识(1:收入;2：支出;)
		userScores.setCreatedAt(new Date());
		userScoresMapper.save(userScores);

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
		if(orders.getReservationDate()!=null){
			orders.setReservationTime(DateUtil.parseDatetime(orders.getReservationDate()));
		}
		if(orders.getDistributionDate()!=null){
			orders.setDistributionTime(DateUtil.parseDatetime(orders.getDistributionDate()));
		}
		List<OrderItems> orderItems = orderItemsMapper.getByOrderId(id);
		for (OrderItems orderItem : orderItems) {
			List<OrderItemPropertys> orderItemPropertys = orderItemPropertysMapper
					.getByOrderItemId(orderItem.getId().toString());
			for (OrderItemPropertys orderItemProperty : orderItemPropertys) {
				ItemPropertys itemPropertys = itemPropertysMapper
						.getById(orderItemProperty.getItemPropertyId()
								.toString());
				Propertys propertys = propertysMapper.getById(itemPropertys
						.getPropertyId().toString());
				orderItemProperty.setPrice(itemPropertys.getPrice());
				orderItemProperty.setPropertyName(propertys.getName());
			}
			orderItem.setOrderItemPropertys(orderItemPropertys);
		}
		orders.setOrderItems(orderItems);
		return orders;
	}

	public Orders getByOrderNo(String orderNo) {
		Orders orders = ordersMapper.getByOrderNo(orderNo);
		if (orders != null) {
			List<OrderItems> orderItems = orderItemsMapper.getByOrderId(orders
					.getId().toString());
			for (OrderItems orderItem : orderItems) {
				List<OrderItemPropertys> orderItemPropertys = orderItemPropertysMapper
						.getByOrderItemId(orderItem.getId().toString());
				for (OrderItemPropertys orderItemProperty : orderItemPropertys) {
					ItemPropertys itemPropertys = itemPropertysMapper
							.getById(orderItemProperty.getItemPropertyId()
									.toString());
					Propertys propertys = propertysMapper.getById(itemPropertys
							.getPropertyId().toString());
					orderItemProperty.setPrice(itemPropertys.getPrice());
					orderItemProperty.setPropertyName(propertys.getName());
				}
				orderItem.setOrderItemPropertys(orderItemPropertys);
			}
			orders.setOrderItems(orderItems);
		}
		return orders;
	}

	public PageResult<Orders> getAllByUserId(PageBounds bounds, String userId) {
		PageList<Orders> orders = ordersMapper.getByUserId(bounds, userId);
		for (Orders order : orders) {
			Merchants merchants = merchantsMapper.getById(order.getMerchantId()
					.toString());
			List<MerchantPictures> oldPictures = merchantPicturesMapper
					.getByMerchantId(merchants.getId().toString());
			order.setMerchantName(merchants.getName());
			if (oldPictures != null && oldPictures.size() > 0) {
				order.setMerchantPicture(oldPictures.get(0).getUrl());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setCreatedTime(sdf.format(order.getCreatedAt()));
		}
		return new PageResult<Orders>(orders);
	}

	public PageResult<Orders> getAllByPay(PageBounds bounds, String userId) {
		PageList<Orders> orders = ordersMapper.getByPay(bounds, userId);
		for (Orders order : orders) {
			Merchants merchants = merchantsMapper.getById(order.getMerchantId()
					.toString());
			List<MerchantPictures> oldPictures = merchantPicturesMapper
					.getByMerchantId(merchants.getId().toString());
			order.setMerchantName(merchants.getName());
			if (oldPictures != null && oldPictures.size() > 0) {
				order.setMerchantPicture(oldPictures.get(0).getUrl());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setCreatedTime(sdf.format(order.getCreatedAt()));
		}
		return new PageResult<Orders>(orders);
	}

	public PageResult<Orders> getAllByShip(PageBounds bounds, String userId) {
		PageList<Orders> orders = ordersMapper.getByShip(bounds, userId);
		for (Orders order : orders) {
			Merchants merchants = merchantsMapper.getById(order.getMerchantId()
					.toString());
			List<MerchantPictures> oldPictures = merchantPicturesMapper
					.getByMerchantId(merchants.getId().toString());
			order.setMerchantName(merchants.getName());
			if (oldPictures != null && oldPictures.size() > 0) {
				order.setMerchantPicture(oldPictures.get(0).getUrl());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setCreatedTime(sdf.format(order.getCreatedAt()));
		}
		return new PageResult<Orders>(orders);
	}

	public PageResult<Orders> getAllByAppraises(PageBounds bounds, String userId) {
		PageList<Orders> orders = ordersMapper.getByAppraises(bounds, userId);
		for (Orders order : orders) {
			Merchants merchants = merchantsMapper.getById(order.getMerchantId()
					.toString());
			List<MerchantPictures> oldPictures = merchantPicturesMapper
					.getByMerchantId(merchants.getId().toString());
			order.setMerchantName(merchants.getName());
			if (oldPictures != null && oldPictures.size() > 0) {
				order.setMerchantPicture(oldPictures.get(0).getUrl());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setCreatedTime(sdf.format(order.getCreatedAt()));
		}
		return new PageResult<Orders>(orders);
	}

	public List<Orders> getAllByNotAppraises() {
		List<Orders> orders = ordersMapper.getByNotAppraises();
		return orders;
	}
	
	public List<Orders> getAllByNotReceipt() {
		List<Orders> orders = ordersMapper.getByNotReceipt();
		return orders;
	}
	
	public List<Orders> getAllByNotComplete() {
		List<Orders> orders = ordersMapper.getByNotComplete();
		return orders;
	}
	
	public List<Orders> getAllByNotPrint() {
		List<Orders> orders = ordersMapper.getByNotPrint();
		return orders;
	}

	public List<Orders> getAllByNotPay() {
		List<Orders> orders = ordersMapper.getByNotPay();
		return orders;
	}

	public PageResult<Orders> getAllByRefund(PageBounds bounds, String userId) {
		PageList<Orders> orders = ordersMapper.getByRefund(bounds, userId);
		for (Orders order : orders) {
			Merchants merchants = merchantsMapper.getById(order.getMerchantId()
					.toString());
			List<MerchantPictures> oldPictures = merchantPicturesMapper
					.getByMerchantId(merchants.getId().toString());
			order.setMerchantName(merchants.getName());
			if (oldPictures != null && oldPictures.size() > 0) {
				order.setMerchantPicture(oldPictures.get(0).getUrl());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setCreatedTime(sdf.format(order.getCreatedAt()));
		}
		return new PageResult<Orders>(orders);
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
		Orders orders = ordersMapper.getById(id);
		if(orders.getStatus()==2||orders.getStatus()==3){
			return -1;
		}
		return ordersMapper.delete(id);
	}

}
