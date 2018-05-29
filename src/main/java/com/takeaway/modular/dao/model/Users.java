package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Users {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private Integer id;
	@ApiModelProperty(value = "账号", required = false)
	private String loginName;
	@ApiModelProperty(value = "安全码", required = false)
	private Integer loginSecret;
	@ApiModelProperty(value = "密码", required = false)
	private String loginPwd;
	@ApiModelProperty(value = "性别(0：女;1：男;)", required = false)
	private Integer userSex;
	@ApiModelProperty(value = "用户类型(0:普通会员 1:门店用户)", required = false)
	private Integer userType;
	@ApiModelProperty(value = "用户名称", required = false)
	private String userName;
	@ApiModelProperty(value = "用户QQ", required = false)
	private String userQQ;
	@ApiModelProperty(value = "手机", required = false)
	private String userPhone;
	@ApiModelProperty(value = "邮箱", required = false)
	private String userEmail;
	@ApiModelProperty(value = "用户积分", required = false)
	private Integer userScore;
	@ApiModelProperty(value = "会员头像", required = false)
	private String userPhoto;
	@ApiModelProperty(value = "用户历史消费积分", required = false)
	private Integer userTotalScore;
	private Date createdAt;
	private Date updatedAt;
	@ApiModelProperty(value = "状态(0：禁用;1：正常;-1：删除)", required = false)
	private Integer status;
	@ApiModelProperty(value = "删除标志(0:未删除 1:已删除)", required = false)
	private Integer isFlag;
	@ApiModelProperty(value = "最后登录IP", required = false)
	private String lastIP;
	@ApiModelProperty(value = "最后登录时间", required = false)
	private Date lastTime;
	@ApiModelProperty(value = "用户来源", required = false)
	private String userFrom;
	private String openId;
	private String wxOpenId;
	@ApiModelProperty(value = "帐号余额", required = false)
	private Double userMoney;
	@ApiModelProperty(value = "冻结金额", required = false)
	private Double lockMoney;
	@ApiModelProperty(value = "佣金", required = false)
	private Double distributMoney;
	@ApiModelProperty(value = "是否购买者(0：不是;1：是;)", required = false)
	private Integer isBuyer;
	@ApiModelProperty(value = "支付密码", required = false)
	private String payPwd;

	private String userRank;	// 用户当前等级
	
	private String nextRankScore;	// 用户距离下一等级还差多少积分
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getLoginSecret() {
		return loginSecret;
	}

	public void setLoginSecret(Integer loginSecret) {
		this.loginSecret = loginSecret;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
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

	public Integer getUserScore() {
		return userScore;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Integer getUserTotalScore() {
		return userTotalScore;
	}

	public void setUserTotalScore(Integer userTotalScore) {
		this.userTotalScore = userTotalScore;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

	public String getLastIP() {
		return lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
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

	public Double getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(Double userMoney) {
		this.userMoney = userMoney;
	}

	public Double getLockMoney() {
		return lockMoney;
	}

	public void setLockMoney(Double lockMoney) {
		this.lockMoney = lockMoney;
	}

	public Double getDistributMoney() {
		return distributMoney;
	}

	public void setDistributMoney(Double distributMoney) {
		this.distributMoney = distributMoney;
	}

	public Integer getIsBuyer() {
		return isBuyer;
	}

	public void setIsBuyer(Integer isBuyer) {
		this.isBuyer = isBuyer;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getUserRank() {
		return userRank;
	}

	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}

	public String getNextRankScore() {
		return nextRankScore;
	}

	public void setNextRankScore(String nextRankScore) {
		this.nextRankScore = nextRankScore;
	}

}
