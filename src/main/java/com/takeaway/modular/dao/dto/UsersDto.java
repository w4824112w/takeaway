package com.takeaway.modular.dao.dto;

import io.swagger.annotations.ApiModelProperty;

public class UsersDto {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private String id;
	@ApiModelProperty(value = "账号", required = false)
	private String loginName;
	@ApiModelProperty(value = "安全码", required = false)
	private String loginSecret;
	@ApiModelProperty(value = "密码", required = false)
	private String loginPwd;
	@ApiModelProperty(value = "性别(0：女;1：男;)", required = false)
	private String userSex;
	@ApiModelProperty(value = "用户类型(0:普通会员 1:门店用户)", required = false)
	private String userType;
	@ApiModelProperty(value = "用户名称", required = false)
	private String userName;
	@ApiModelProperty(value = "用户QQ", required = false)
	private String userQQ;
	@ApiModelProperty(value = "手机", required = false)
	private String userPhone;
	@ApiModelProperty(value = "邮箱", required = false)
	private String userEmail;
	@ApiModelProperty(value = "用户积分", required = false)
	private String userScore;
	@ApiModelProperty(value = "会员头像", required = false)
	private String userPhoto;
	@ApiModelProperty(value = "用户历史消费积分", required = false)
	private String userTotalScore;
	private String createdAt;
	private String updatedAt;
	@ApiModelProperty(value = "状态(0：禁用;1：正常;-1：删除)", required = false)
	private String status;
	@ApiModelProperty(value = "删除标志(0:未删除 1:已删除)", required = false)
	private String isFlag;
	@ApiModelProperty(value = "最后登录IP", required = false)
	private String lastIP;
	@ApiModelProperty(value = "最后登录时间", required = false)
	private String lastTime;
	@ApiModelProperty(value = "用户来源", required = false)
	private String userFrom;
	private String openId;
	private String wxOpenId;
	@ApiModelProperty(value = "帐号余额", required = false)
	private String userMoney;
	@ApiModelProperty(value = "冻结金额", required = false)
	private String lockMoney;
	@ApiModelProperty(value = "佣金", required = false)
	private String distributMoney;
	@ApiModelProperty(value = "是否购买者(0：不是;1：是;)", required = false)
	private String isBuyer;
	@ApiModelProperty(value = "支付密码", required = false)
	private String payPwd;

	private String orderCount;
	private String totalPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginSecret() {
		return loginSecret;
	}
	public void setLoginSecret(String loginSecret) {
		this.loginSecret = loginSecret;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserScore() {
		return userScore;
	}
	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserTotalScore() {
		return userTotalScore;
	}
	public void setUserTotalScore(String userTotalScore) {
		this.userTotalScore = userTotalScore;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public String getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}
	public String getLockMoney() {
		return lockMoney;
	}
	public void setLockMoney(String lockMoney) {
		this.lockMoney = lockMoney;
	}
	public String getDistributMoney() {
		return distributMoney;
	}
	public void setDistributMoney(String distributMoney) {
		this.distributMoney = distributMoney;
	}
	public String getIsBuyer() {
		return isBuyer;
	}
	public void setIsBuyer(String isBuyer) {
		this.isBuyer = isBuyer;
	}
	public String getPayPwd() {
		return payPwd;
	}
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


}
