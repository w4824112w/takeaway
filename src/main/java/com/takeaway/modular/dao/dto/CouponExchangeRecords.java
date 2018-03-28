package com.takeaway.modular.dao.dto;

import java.util.Date;

public class CouponExchangeRecords {
	private Integer id;
	private String exchangeCode;
	private Date exchangeTime;
	private Integer userId;
	private String userName;
	private Integer exchangeId;
	private Date gainTime;
	private Integer sendType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Date getGainTime() {
		return gainTime;
	}

	public void setGainTime(Date gainTime) {
		this.gainTime = gainTime;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

}
