package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Orders {
	private Integer id;
	private String orderNo;
	@ApiModelProperty(value = "店铺ID", required = false)
	private Integer merchantId;
	private Integer userId;
	private Integer orderType;
	@ApiModelProperty(value = "商品总金额(未进行任何折扣的总价格)", required = false)
	private Double totalPrice;
	@ApiModelProperty(value = "打包费", required = false)
	private Double packingCharge;
	@ApiModelProperty(value = "运费", required = false)
	private Double deliverMoney;
	private Integer status;
	private Date createdAt;
	private Date updatedAt;
	private Integer isPay;
	private Date payDate;
	private String paySno;
	@ApiModelProperty(value = "实际订单总金额", required = false)
	private Double realTotalMoney;
	private Integer isShip;
	private Date shipDate;
	private Integer isReceipt;
	private Date receiptDate;
	private Integer isReceived;
	private Date receivedDate;
	private Integer isRefund;
	private Date refundDate;
	private Double refundMoney;
	private Integer refundSrcStatus;
	private Integer isReservation;
	private Date reservationDate;
	private Integer isReminder;
	private Date reminderDate;
	private Integer isDistribution;
	private Integer merchantType;
	@ApiModelProperty(value = "订单备注", required = false)
	private String remark;
	private Integer payType;
	private Integer isInvoice;
	private String invoiceClient;
	private Integer isAppraises;
	@ApiModelProperty(value = "收货人名称", required = false)
	private String userName;
	@ApiModelProperty(value = "收货人手机", required = false)
	private String userPhone;
	@ApiModelProperty(value = "收货人地址", required = false)
	private String userAddress;
	private Double platformCommission;
	private Integer orderScore;
	@ApiModelProperty(value = "优惠券金额", required = false)
	private Double couponMoney;
	@ApiModelProperty(value = "满减活动优惠金额", required = false)
	private Double activityMoney;
	
	@ApiModelProperty(value = "openid", required = false)
	private String openid;

	private List<OrderItems> orderItems;
	
	private List<Coupons> coupons;

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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDeliverMoney() {
		return deliverMoney;
	}

	public void setDeliverMoney(Double deliverMoney) {
		this.deliverMoney = deliverMoney;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public Double getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(Double realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public Integer getIsShip() {
		return isShip;
	}

	public void setIsShip(Integer isShip) {
		this.isShip = isShip;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Integer getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(Integer isReceipt) {
		this.isReceipt = isReceipt;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
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

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Integer getRefundSrcStatus() {
		return refundSrcStatus;
	}

	public void setRefundSrcStatus(Integer refundSrcStatus) {
		this.refundSrcStatus = refundSrcStatus;
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

	public String getInvoiceClient() {
		return invoiceClient;
	}

	public void setInvoiceClient(String invoiceClient) {
		this.invoiceClient = invoiceClient;
	}

	public Integer getIsAppraises() {
		return isAppraises;
	}

	public void setIsAppraises(Integer isAppraises) {
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

	public Double getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(Double platformCommission) {
		this.platformCommission = platformCommission;
	}

	public Integer getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(Integer orderScore) {
		this.orderScore = orderScore;
	}

	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Double getActivityMoney() {
		return activityMoney;
	}

	public void setActivityMoney(Double activityMoney) {
		this.activityMoney = activityMoney;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Double getPackingCharge() {
		return packingCharge;
	}

	public void setPackingCharge(Double packingCharge) {
		this.packingCharge = packingCharge;
	}

	public List<Coupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupons> coupons) {
		this.coupons = coupons;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
