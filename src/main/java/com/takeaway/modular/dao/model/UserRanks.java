package com.takeaway.modular.dao.model;

import java.util.Date;

public class UserRanks {
	private Integer id;
	private String name;
	private Integer startScore;
	private Integer endScore;
	private Integer rebate;
	private Date createdAt;

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

	public Integer getStartScore() {
		return startScore;
	}

	public void setStartScore(Integer startScore) {
		this.startScore = startScore;
	}

	public Integer getEndScore() {
		return endScore;
	}

	public void setEndScore(Integer endScore) {
		this.endScore = endScore;
	}

	public Integer getRebate() {
		return rebate;
	}

	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
