package com.takeaway.modular.dao.model;

import java.util.Date;

public class Feedbacks {
	private Integer id;
	private Integer userId;
	private Integer orderId;
	private Integer merchantId;
	private Integer itemId;
	private Integer goodsScore;
	private Integer foodScore;
	private Integer distributionScore;
	private Date createdAt;
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getGoodsScore() {
		return goodsScore;
	}

	public void setGoodsScore(Integer goodsScore) {
		this.goodsScore = goodsScore;
	}

	public Integer getFoodScore() {
		return foodScore;
	}

	public void setFoodScore(Integer foodScore) {
		this.foodScore = foodScore;
	}

	public Integer getDistributionScore() {
		return distributionScore;
	}

	public void setDistributionScore(Integer distributionScore) {
		this.distributionScore = distributionScore;
	}

}
