package com.takeaway.modular.dao.model;

import java.util.Date;

public class UserScores {
	private Integer id;
	private Integer userId;
	private Integer score;
	private Integer dataSrc;
	private String dataRemarks;
	private Integer scoreType;
	private Date createdAt;

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

}
