package com.takeaway.modular.dao.dto;

import java.util.List;

import com.takeaway.modular.dao.model.OrderItems;

public class OrdersDto {
	private String id;
	private String orderNo;
	private String merchantId;
	private String merchantName;
	private String userId;
	private String orderType;
	private String totalPrice;
	private String packingCharge;
	private String deliverMoney;
	private String status;
	private String createdAt;
	private String updatedAt;
	private String isPay;
	private String payDate;
	private String paySno;
	private String realTotalMoney;
	private String isShip;
	private String shipDate;
	private String isReceipt;
	private String receiptDate;
	private String isReceived;
	private String receivedDate;
	private String isRefund;
	private String refundDate;
	private String refundMoney;
	private String refundSrcStatus;
	private String isReservation;
	private String reservationDate;
	private String isReminder;
	private String reminderDate;
	private String isDistribution;
	private String merchantType;
	private String remark;
	private String payType;
	private String isInvoice;
	private String invoiceClient;
	private String isAppraises;
	private String userName;
	private String userPhone;
	private String userAddress;
	private String platformCommission;
	private String orderScore;
	private String couponMoney;
	private String activityMoney;
	private String targetName;	//	优惠券或活动名称

	private String processing; // 未处理订单数
	private String refunding; // 退款中的外卖订单数
	private String todayOrders; // 今日订单数
	private String todayTurnover; // 今日营业额

	private String startTime; // 开始日期
	private String endTime; // 结束日期
	
	private List<OrderItems> orderItems;

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

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getActivityMoney() {
		return activityMoney;
	}

	public void setActivityMoney(String activityMoney) {
		this.activityMoney = activityMoney;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPackingCharge() {
		return packingCharge;
	}

	public void setPackingCharge(String packingCharge) {
		this.packingCharge = packingCharge;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

}
