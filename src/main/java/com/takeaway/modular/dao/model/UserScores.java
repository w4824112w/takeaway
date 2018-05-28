package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserScores {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private Integer id;
	@ApiModelProperty(value = "会员ID", required = false)
	private Integer userId;
	@ApiModelProperty(value = "积分数", required = false)
	private Integer score;
	@ApiModelProperty(value = "来源(1：系统赠送;2:订单消费;)", required = false)
	private Integer dataSrc;
	@ApiModelProperty(value = "描述", required = false)
	private String dataRemarks;
	@ApiModelProperty(value = "积分标识(1:收入;2：支出;)", required = false)
	private Integer scoreType;
	private Date createdAt;
	
	private Users users;

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getDataSrc() {
		return dataSrc;
	}

	public void setDataSrc(Integer dataSrc) {
		this.dataSrc = dataSrc;
	}

	public String getDataRemarks() {
		return dataRemarks;
	}

	public void setDataRemarks(String dataRemarks) {
		this.dataRemarks = dataRemarks;
	}

	public Integer getScoreType() {
		return scoreType;
	}

	public void setScoreType(Integer scoreType) {
		this.scoreType = scoreType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
