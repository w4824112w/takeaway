package com.takeaway.modular.dao.dto;

public class UserRanksDto {
	private String id;
	private String name;
	private String startScore;
	private String endScore;
	private String rebate;
	private String createdAt;
	
	private String userScore;

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

	public String getStartScore() {
		return startScore;
	}

	public void setStartScore(String startScore) {
		this.startScore = startScore;
	}

	public String getEndScore() {
		return endScore;
	}

	public void setEndScore(String endScore) {
		this.endScore = endScore;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

}
