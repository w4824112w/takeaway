package com.takeaway.modular.dao.dto;

public class CouponExchangesDto {
	private String id;
	private String code;
	private String couponId;
	private String startDate;
	private String endDate;
	private String status;
	private String createdAt;
	private String updatedAt;

	private String name;
	private String amount;
	private String money;
	private String activityType;
	private String description;
	private String activityDescription;
	private String exceed;
	private String discount;
	private String effectiveTime;
	private String unit;
	private String limitUsed;
	private String couponType;

	private String exchangeCount; // 活动参与人数
	private String surplus; // 优惠券余额
	private String activityCount; // 活动核销数
	private String activityMoney; // 活动核销金额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getExceed() {
		return exceed;
	}

	public void setExceed(String exceed) {
		this.exceed = exceed;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLimitUsed() {
		return limitUsed;
	}

	public void setLimitUsed(String limitUsed) {
		this.limitUsed = limitUsed;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(String exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	public String getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(String activityCount) {
		this.activityCount = activityCount;
	}

	public String getActivityMoney() {
		return activityMoney;
	}

	public void setActivityMoney(String activityMoney) {
		this.activityMoney = activityMoney;
	}

}
