package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserCoupons {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private Integer id;
	@ApiModelProperty(value = "优惠券ID", required = false)
	private Integer couponId;
	@ApiModelProperty(value = "优惠券兑换码", required = false)
	private String couponCode;
	@ApiModelProperty(value = "数量", required = false)
	private Integer amount;
	@ApiModelProperty(value = "会员ID", required = false)
	private Integer userId;
	@ApiModelProperty(value = "会员名称", required = false)
	private String userName;
	@ApiModelProperty(value = "使用状态(1:未用，0：已用 -1:删除)", required = false)
	private Integer status;
	@ApiModelProperty(value = "有效状态(1:有效 -1:删除)", required = false)
	private Integer isFlag;
	@ApiModelProperty(value = "优惠券类型", required = false)
	private Integer type;
	@ApiModelProperty(value = "优惠券开始时间", required = false)
	private Date startDate;
	@ApiModelProperty(value = "优惠券结束时间", required = false)
	private Date endDate;
	@ApiModelProperty(value = "优惠券获得时间", required = false)
	private Date gainTime;
	@ApiModelProperty(value = "优惠券名称", required = false)
	private String couponName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Date getGainTime() {
		return gainTime;
	}

	public void setGainTime(Date gainTime) {
		this.gainTime = gainTime;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

}
