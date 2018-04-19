package com.takeaway.modular.dao.dto;

public class OrderHistorysDto {
	private String id;
	private String orderNo;
	private String merchantId;
	private String userId;
	private String orderType;
	private String size;
	private String totalPrice;
	private String itemId;
	private String itemName;
	private String itemPrice;
	private String status;
	private String createdAt;
	private String updatedAt;
	private String isPay;
	private String payDate;
	private String paySno;
	private String isShip;
	private String isReceipt;
	private String isReceived;
	private String receivedDate;
	private String isRefund;
	private String refundDate;
	private String isReservation;
	private String reservationDate;
	private String isReminder;
	private String reminderDate;
	private String isDistribution;
	private String merchantType;
	private String remark;
	private String payType;
	private String isInvoice;
	private String isAppraises;

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
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

	public String getIsAppraises() {
		return isAppraises;
	}

	public void setIsAppraises(String isAppraises) {
		this.isAppraises = isAppraises;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getIsShip() {
		return isShip;
	}

	public void setIsShip(String isShip) {
		this.isShip = isShip;
	}

	public String getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(String isReceipt) {
		this.isReceipt = isReceipt;
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
