package com.takeaway.modular.dao.model;

import java.util.Date;

public class Coupons {
	private Integer id;
	private String name;
	private Integer amount;
	private Double money;
	private String description;
	private String activityDescription;
	private Double exceed;
	private Double discount;
	private Date startDate;
	private Date endDate;
	private Integer effectiveTime;
	private String unit;
	private Double limitUsed;
	private Integer type;
	private Date createdAt;
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public Double getExceed() {
		return exceed;
	}

	public void setExceed(Double exceed) {
		this.exceed = exceed;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getLimitUsed() {
		return limitUsed;
	}

	public void setLimitUsed(Double limitUsed) {
		this.limitUsed = limitUsed;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
