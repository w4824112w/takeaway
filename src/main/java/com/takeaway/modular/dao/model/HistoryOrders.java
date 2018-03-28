package com.takeaway.modular.dao.model;

import java.util.Date;

public class HistoryOrders {
	private Integer id;
	private String orderNo;
	private Integer merchantId;
	private Integer userId;
	private Integer orderType;
	private Integer size;
	private Double totalPrice;
	private Integer itemId;
	private String itemName;
	private Double itemPrice;
	private Integer status;
	private Date createdAt;
	private Integer isPay;
	private Date payDate;
	private String paySno;
	private Integer isReceived;
	private Date receivedDate;
	private Integer isRefund;
	private Date refundDate;
	private Integer isReservation;
	private Date reservationDate;
	private Integer isReminder;
	private Date reminderDate;
	private Integer isDistribution;
	private Integer merchantType;
	private String remark;
	private Integer payType;
	private Integer isInvoice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPaySno() {
		return paySno;
	}
	public void setPaySno(String paySno) {
		this.paySno = paySno;
	}
	public Integer getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(Integer isReceived) {
		this.isReceived = isReceived;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Integer getIsRefund() {
		return isRefund;
	}
	public void setIsRefund(Integer isRefund) {
		this.isRefund = isRefund;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public Integer getIsReservation() {
		return isReservation;
	}
	public void setIsReservation(Integer isReservation) {
		this.isReservation = isReservation;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Integer getIsReminder() {
		return isReminder;
	}
	public void setIsReminder(Integer isReminder) {
		this.isReminder = isReminder;
	}
	public Date getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}
	public Integer getIsDistribution() {
		return isDistribution;
	}
	public void setIsDistribution(Integer isDistribution) {
		this.isDistribution = isDistribution;
	}
	public Integer getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
	
}
