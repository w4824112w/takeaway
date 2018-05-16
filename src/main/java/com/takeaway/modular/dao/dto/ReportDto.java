package com.takeaway.modular.dao.dto;

public class ReportDto {
	private String settlTime; // 时间
	private String totalPrice; // 营业额
	private String deliverMoney; // 配送费
	private String couponMoney; // 优惠券金额
	private String activityMoney; // 满减送金额
	private String realTotalMoney; // 实际支付金额
	private String platformServiceFee; // 平台服务费
	private String realIncome; // 实际收入
	private String wxProcedures; // 微信手续费
	private String settleMoney; // 结算金额

	private String merchantId;
	private String startTime; // 开始日期
	private String endTime; // 结束日期

	public String getSettlTime() {
		return settlTime;
	}

	public void setSettlTime(String settlTime) {
		this.settlTime = settlTime;
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

	public String getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(String realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public String getPlatformServiceFee() {
		return platformServiceFee;
	}

	public void setPlatformServiceFee(String platformServiceFee) {
		this.platformServiceFee = platformServiceFee;
	}

	public String getRealIncome() {
		return realIncome;
	}

	public void setRealIncome(String realIncome) {
		this.realIncome = realIncome;
	}

	public String getWxProcedures() {
		return wxProcedures;
	}

	public void setWxProcedures(String wxProcedures) {
		this.wxProcedures = wxProcedures;
	}

	public String getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(String settleMoney) {
		this.settleMoney = settleMoney;
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

}
