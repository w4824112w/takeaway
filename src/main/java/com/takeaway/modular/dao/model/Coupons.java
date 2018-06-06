package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

import com.takeaway.modular.dao.dto.CouponMerchantsDto;
import com.takeaway.modular.dao.dto.CouponPicturesDto;

public class Coupons {
	private Integer id;
	private String name;
	private Double fullMoney;
	private Double couponMoney;
	private Double spendMoney;
	private String description;
	private Integer sendNum;
	private Integer receiveNum;
	private Date sendStartTime;
	private Date sendEndTime;
	private Integer couponSendType;
	private Integer effectiveTime;
	private Date createdAt;
	private Date startDate;
	private Date endDate;
	private Date updatedAt;
	private Integer status;

	private List<CouponMerchantsDto> merchants;

	private List<CouponPictures> pictures;

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

	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Double getSpendMoney() {
		return spendMoney;
	}

	public void setSpendMoney(Double spendMoney) {
		this.spendMoney = spendMoney;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Integer getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}

	public Date getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(Date sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public Date getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(Date sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public Integer getCouponSendType() {
		return couponSendType;
	}

	public void setCouponSendType(Integer couponSendType) {
		this.couponSendType = couponSendType;
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

	public List<CouponMerchantsDto> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<CouponMerchantsDto> merchants) {
		this.merchants = merchants;
	}

	public List<CouponPictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<CouponPictures> pictures) {
		this.pictures = pictures;
	}

	public Integer getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

}
