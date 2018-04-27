package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Orders {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private Integer id;
	@ApiModelProperty(value = "订单号", required = false)
	private String orderNo;
	@ApiModelProperty(value = "店铺ID", required = false)
	private Integer merchantId;
	@ApiModelProperty(value = "会员ID", required = false)
	private Integer userId;
	private Integer orderType;
	@ApiModelProperty(value = "商品总金额(未进行任何折扣的总价格)", required = false)
	private Double totalPrice;
	@ApiModelProperty(value = "运费", required = false)
	private Double deliverMoney;
	@ApiModelProperty(value = "(0：待处理;1：已处理;2：已完成;3：退款中;4：已评价;5：配送中;9：作废;)", required = false)
	private Integer status;
	private Date createdAt;
	private Date updatedAt;
	@ApiModelProperty(value = "(0：未支付;1：已支付;)", required = false)
	private Integer isPay;
	@ApiModelProperty(value = "付款时间", required = false)
	private Date payDate;
	@ApiModelProperty(value = "支付流水号", required = false)
	private String paySno;
	@ApiModelProperty(value = "实际订单总金额", required = false)
	private Double realTotalMoney;
	@ApiModelProperty(value = "(0：未发货;1：已发货;)", required = false)
	private Integer isShip;
	@ApiModelProperty(value = "发货时间", required = false)
	private Date shipDate;
	@ApiModelProperty(value = "(0：未收货;1：已收货;)", required = false)
	private Integer isReceipt;
	@ApiModelProperty(value = "收货时间", required = false)
	private Date receiptDate;
	@ApiModelProperty(value = "(0：未接单;1：已接单;)", required = false)
	private Integer isReceived;
	@ApiModelProperty(value = "接单时间", required = false)
	private Date receivedDate;
	@ApiModelProperty(value = "(0：未退款;1：已退款;)", required = false)
	private Integer isRefund;
	@ApiModelProperty(value = "退款时间", required = false)
	private Date refundDate;
	@ApiModelProperty(value = "退款金额", required = false)
	private Double refundMoney;
	@ApiModelProperty(value = "退款前订单状态", required = false)
	private Integer refundSrcStatus;
	@ApiModelProperty(value = "(0：未预定;1：已预定;)", required = false)
	private Integer isReservation;
	@ApiModelProperty(value = "预定时间", required = false)
	private Date reservationDate;
	@ApiModelProperty(value = "(0：未催单;1：已催单;)", required = false)
	private Integer isReminder;
	@ApiModelProperty(value = "催单时间", required = false)
	private Date reminderDate;
	@ApiModelProperty(value = "(0：配送中;1：已配送;)", required = false)
	private Integer isDistribution;
	@ApiModelProperty(value = "店铺类型", required = false)
	private Integer merchantType;
	@ApiModelProperty(value = "订单备注", required = false)
	private String remark;
	@ApiModelProperty(value = "支付方式", required = false)
	private Integer payType;
	@ApiModelProperty(value = "是否需要发票(0：不需要;1：需要;)", required = false)
	private Integer isInvoice;
	@ApiModelProperty(value = "发票抬头", required = false)
	private String invoiceClient;
	@ApiModelProperty(value = "是否点评(0：未点评;1：已点评;)", required = false)
	private Integer isAppraises;
	@ApiModelProperty(value = "收货人名称", required = false)
	private String userName;
	@ApiModelProperty(value = "收货人手机", required = false)
	private String userPhone;
	@ApiModelProperty(value = "收货人地址", required = false)
	private String userAddress;
	@ApiModelProperty(value = "平台佣金", required = false)
	private Double platformCommission;
	@ApiModelProperty(value = "所得积分", required = false)
	private Integer orderScore;
	@ApiModelProperty(value = "优惠券金额", required = false)
	private Double couponMoney;
	@ApiModelProperty(value = "满减活动优惠金额", required = false)
	private Double activityMoney;
	
	private List<OrderItems> orderItems;

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

}
