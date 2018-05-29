package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

public class Activitys {
	private Integer id;
	private String activityNo;
	private String name;
	private Double fullMoney;
	private Double reduceMoney;
	private String description;
	private Date createdAt;
	private Date startDate;
	private Date endDate;
	private Date updatedAt;
	private Integer status;
	
	private String totalCount;	//	活动核销数
	private String totalPrice;	//	活动核销金额

	private List<CouponMerchants> merchants;
	
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

	public Double getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(Double fullMoney) {
		this.fullMoney = fullMoney;
	}

	public Double getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(Double reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<CouponMerchants> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<CouponMerchants> merchants) {
		this.merchants = merchants;
	}

	public String getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
