package com.takeaway.modular.dao.dto;

import java.util.List;

import com.takeaway.modular.dao.model.CouponMerchants;

public class CouponsDto {
	private String id;
	private String name;
	private String couponMoney;
	private String spendMoney;
	private String description;
	private String sendNum;
	private String receiveNum;
	private String sendStartTime;
	private String sendEndTime;
	private String couponSendType;
	private String createdAt;
	private String startDate;
	private String endDate;
	private String updatedAt;
	private String status;
	
	private List<CouponMerchants> merchants;
	private List<CouponPicturesDto> pictures;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	public String getSpendMoney() {
		return spendMoney;
	}

	public void setSpendMoney(String spendMoney) {
		this.spendMoney = spendMoney;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSendNum() {
		return sendNum;
	}

	public void setSendNum(String sendNum) {
		this.sendNum = sendNum;
	}

	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getCouponSendType() {
		return couponSendType;
	}

	public void setCouponSendType(String couponSendType) {
		this.couponSendType = couponSendType;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CouponPicturesDto> getPictures() {
		return pictures;
	}

	public void setPictures(List<CouponPicturesDto> pictures) {
		this.pictures = pictures;
	}

	public List<CouponMerchants> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<CouponMerchants> merchants) {
		this.merchants = merchants;
	}

}
