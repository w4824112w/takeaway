package com.takeaway.modular.dao.dto;

import java.util.Date;

public class OrderHistorysDto {
	private String id; // 主键id
	private String orderNo; // 订单号
	private String merchantId; // 店铺ID
	private String userId; // 会员id
	private String orderType;
	private String meals; // 用餐人数
	private String totalPrice; // 商品总金额(未进行任何折扣的总价格)
	private String packingCharge; // 打包费
	private String deliverMoney; // 运费
	private String status; // (1 待支付。2 待发货。 3 待收货 4 待评价 5 已完成 6退款/售后)
	private String createdAt; // 创建时间
	private String updatedAt; // 更新时间
	private String isPay; // (0：未支付;1：已支付;)
	private String payDate; // 付款时间
	private String paySno; // 支付流水号
	private String realTotalMoney; // 实际订单总金额
	private String isShip; // (0：未发货;1：已发货;)
	private String shipDate; // 发货时间
	private String isReceipt; // (0：未收货;1：已收货;)
	private String receiptDate; // 收货时间
	private String isReceived; // (0：未接单;1：已接单;)
	private String receivedDate; // 接单时间
	private String refundApplyDate; // 退款申请时间
	private String isRefund; // (0：未退款;1：已退款;)
	private String refundDate; // 退款时间
	private String refundMoney; // 退款金额
	private String refundSrcStatus; // 退款前订单状态
	private String isReservation; // (0：未预定;1：已预定;)
	private String reservationDate; // 预定时间
	private String isReminder; // (0：未催单;1：已催单;)
	private String reminderDate; // 催单时间
	private String isDistribution; // (0：配送中;1：已配送;)
	private String distributionDate; // 配送时间
	private String merchantType;
	private String remark; // 订单备注
	private String payType; // 支付方式
	private String isInvoice; // 是否需要发票(0：不需要;1：需要;)
	private String invoiceClient; // 发票抬头
	private String isAppraises; // 是否点评(0：未点评;1：已点评;)
	private String appraisesDate; // 评价时间
	private String userName; // 收货人名称
	private String userPhone; // 收货人手机
	private String userAddress; // 收货人地址
	private String platformCommission;
	private String orderScore; // 所得积分
	private String couponMoney; // 优惠券金额
	private String activityMoney; // 满减活动优惠金额

	private String openid;

	private String merchantName;
	private String merchantTel;
	private String merchantPicture;

	private String createdTime; // 创建时间

	private String processing; // 未处理订单数
	private String refunding; // 退款中的外卖订单数
	private String todayOrders; // 今日订单数
	private String todayTurnover; // 今日营业额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getMeals() {
		return meals;
	}

	public void setMeals(String meals) {
		this.meals = meals;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPackingCharge() {
		return packingCharge;
	}

	public void setPackingCharge(String packingCharge) {
		this.packingCharge = packingCharge;
	}

	public String getDeliverMoney() {
		return deliverMoney;
	}

	public void setDeliverMoney(String deliverMoney) {
		this.deliverMoney = deliverMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPaySno() {
		return paySno;
	}

	public void setPaySno(String paySno) {
		this.paySno = paySno;
	}

	public String getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(String realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public String getIsShip() {
		return isShip;
	}

	public void setIsShip(String isShip) {
		this.isShip = isShip;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(String isReceipt) {
		this.isReceipt = isReceipt;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getRefundApplyDate() {
		return refundApplyDate;
	}

	public void setRefundApplyDate(String refundApplyDate) {
		this.refundApplyDate = refundApplyDate;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public String getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRefundSrcStatus() {
		return refundSrcStatus;
	}

	public void setRefundSrcStatus(String refundSrcStatus) {
		this.refundSrcStatus = refundSrcStatus;
	}

	public String getIsReservation() {
		return isReservation;
	}

	public void setIsReservation(String isReservation) {
		this.isReservation = isReservation;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getIsReminder() {
		return isReminder;
	}

	public void setIsReminder(String isReminder) {
		this.isReminder = isReminder;
	}

	public String getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getIsDistribution() {
		return isDistribution;
	}

	public void setIsDistribution(String isDistribution) {
		this.isDistribution = isDistribution;
	}

	public String getDistributionDate() {
		return distributionDate;
	}

	public void setDistributionDate(String distributionDate) {
		this.distributionDate = distributionDate;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getInvoiceClient() {
		return invoiceClient;
	}

	public void setInvoiceClient(String invoiceClient) {
		this.invoiceClient = invoiceClient;
	}

	public String getIsAppraises() {
		return isAppraises;
	}

	public void setIsAppraises(String isAppraises) {
		this.isAppraises = isAppraises;
	}

	public String getAppraisesDate() {
		return appraisesDate;
	}

	public void setAppraisesDate(String appraisesDate) {
		this.appraisesDate = appraisesDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(String platformCommission) {
		this.platformCommission = platformCommission;
	}

	public String getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(String orderScore) {
		this.orderScore = orderScore;
	}

	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	public String getActivityMoney() {
		return activityMoney;
	}

	public void setActivityMoney(String activityMoney) {
		this.activityMoney = activityMoney;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantTel() {
		return merchantTel;
	}

	public void setMerchantTel(String merchantTel) {
		this.merchantTel = merchantTel;
	}

	public String getMerchantPicture() {
		return merchantPicture;
	}

	public void setMerchantPicture(String merchantPicture) {
		this.merchantPicture = merchantPicture;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getRefunding() {
		return refunding;
	}

	public void setRefunding(String refunding) {
		this.refunding = refunding;
	}

	public String getTodayOrders() {
		return todayOrders;
	}

	public void setTodayOrders(String todayOrders) {
		this.todayOrders = todayOrders;
	}

	public String getTodayTurnover() {
		return todayTurnover;
	}

	public void setTodayTurnover(String todayTurnover) {
		this.todayTurnover = todayTurnover;
	}

}
