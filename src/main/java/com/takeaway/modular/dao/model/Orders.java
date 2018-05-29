package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Orders {
	private Integer id; // 主键id
	private String orderNo; // 订单号
	@ApiModelProperty(value = "店铺ID", required = false)
	private Integer merchantId; // 店铺ID
	private Integer userId; // 会员id
	private Integer orderType;
	@ApiModelProperty(value = "用餐人数", required = false)
	private Integer meals;	//	用餐人数
	@ApiModelProperty(value = "商品总金额(未进行任何折扣的总价格)", required = false)
	private Double totalPrice; // 商品总金额(未进行任何折扣的总价格)
	@ApiModelProperty(value = "打包费", required = false)
	private Double packingCharge; // 打包费
	@ApiModelProperty(value = "运费", required = false)
	private Double deliverMoney; // 运费
	private Integer status; // (1 待支付。2 待发货。 3 待收货 4 待评价 5 已完成 6退款/售后)
	private Date createdAt; // 创建时间
	private Date updatedAt; // 更新时间
	private Integer isPay; // (0：未支付;1：已支付;)
	private Date payDate; // 付款时间
	private String paySno; // 支付流水号
	@ApiModelProperty(value = "实际订单总金额", required = false)
	private Double realTotalMoney; // 实际订单总金额
	private Integer isShip; // (0：未发货;1：已发货;)
	private Date shipDate; // 发货时间
	private Integer isReceipt; // (0：未收货;1：已收货;)
	private Date receiptDate; // 收货时间
	private Integer isReceived; // (0：未接单;1：已接单;)
	private Date receivedDate; // 接单时间
	private Date refundApplyDate; // 退款申请时间
	private Integer isRefund; // (0：未退款;1：已退款;)
	private Date refundDate; // 退款时间
	private Double refundMoney; // 退款金额
	private Integer refundSrcStatus; // 退款前订单状态
	private Integer isReservation; // (0：未预定;1：已预定;)
	private Date reservationDate; // 预定时间
	private String reservationTime; // 预定时间
	private Integer isReminder; // (0：未催单;1：已催单;)
	private Date reminderDate; // 催单时间
	private Integer isDistribution; // (0：配送中;1：已配送;)
	private Date distributionDate; // 配送时间
	private Integer merchantType;
	@ApiModelProperty(value = "订单备注", required = false)
	private String remark; // 订单备注
	private Integer payType; // 支付方式
	private Integer isInvoice; // 是否需要发票(0：不需要;1：需要;)
	private String invoiceClient; // 发票抬头
	private Integer isAppraises; // 是否点评(0：未点评;1：已点评;)
	private Date appraisesDate; // 评价时间
	@ApiModelProperty(value = "收货人名称", required = false)
	private String userName; // 收货人名称
	@ApiModelProperty(value = "收货人手机", required = false)
	private String userPhone; // 收货人手机
	@ApiModelProperty(value = "收货人地址", required = false)
	private String userAddress; // 收货人地址
	private Double platformCommission;
	private Integer orderScore; // 所得积分
	@ApiModelProperty(value = "优惠券金额", required = false)
	private Double couponMoney; // 优惠券金额
	@ApiModelProperty(value = "满减活动优惠金额", required = false)
	private Double activityMoney; // 满减活动优惠金额

	@ApiModelProperty(value = "openid", required = false)
	private String openid;
	
	private Integer activityId;
	private Integer couponId;

	private String merchantName;
	private String merchantTel;
	private String merchantPicture;

	private String createdTime; // 创建时间

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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	
	
	public String getMerchantTel() {
		return merchantTel;
	}

	public void setMerchantTel(String merchantTel) {
		this.merchantTel = merchantTel;
	}

	public String toString() {
		return "meals:"+meals+"-id:" + this.id + "-orderNo:" + orderNo + "-merchantId:"
				+ merchantId + "-userId:" + userId + "-orderType:" + orderType
				+ "-totalPrice:" + totalPrice + "-packingCharge:"
				+ packingCharge + "-deliverMoney:" + deliverMoney + "-status:"
				+ status + "-createdAt:" + createdAt + "-updatedAt:"
				+ updatedAt + "-isPay:" + isPay + "-payDate:" + payDate
				+ "-paySno:" + paySno + "-realTotalMoney:" + realTotalMoney
				+ "-isShip:" + isShip + "-shipDate:" + shipDate + "-isReceipt:"
				+ isReceipt + "-receiptDate:" + receiptDate + "-isReceived:"
				+ isReceived + "-receivedDate:" + receivedDate + "-isRefund:"
				+ isRefund + "-refundDate:" + refundDate + "-refundMoney:"
				+ refundMoney + "-refundSrcStatus:" + refundSrcStatus
				+ "-isReservation:" + isReservation + "-reservationDate:"
				+ reservationDate + "-isReminder:" + isReminder
				+ "-reminderDate:" + reminderDate + "-isDistribution:"
				+ isDistribution + "-merchantType:" + merchantType + "-remark:"
				+ remark + "-payType:" + payType + "-isInvoice:" + isInvoice
				+ "-invoiceClient:" + invoiceClient + "-isAppraises:"
				+ isAppraises + "-userName:" + userName + "-userPhone:"
				+ userPhone + "-userAddress:" + userAddress
				+ "-platformCommission:" + platformCommission + "-orderScore:"
				+ orderScore + "-couponMoney:" + couponMoney
				+ "-activityMoney:" + activityMoney + "-openid:" + openid
				+ "-merchantName:" + merchantName + "-merchantPicture:"
				+ merchantPicture;
	}

	public Integer getMeals() {
		return meals;
	}

	public void setMeals(Integer meals) {
		this.meals = meals;
	}

	public Date getRefundApplyDate() {
		return refundApplyDate;
	}

	public void setRefundApplyDate(Date refundApplyDate) {
		this.refundApplyDate = refundApplyDate;
	}

	public Date getDistributionDate() {
		return distributionDate;
	}

	public void setDistributionDate(Date distributionDate) {
		this.distributionDate = distributionDate;
	}

	public Date getAppraisesDate() {
		return appraisesDate;
	}

	public void setAppraisesDate(Date appraisesDate) {
		this.appraisesDate = appraisesDate;
	}

	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

}
