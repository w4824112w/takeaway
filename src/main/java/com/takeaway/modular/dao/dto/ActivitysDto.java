package com.takeaway.modular.dao.dto;

public class ActivitysDto {
	private String id;
	private String name;
	private String fullMoney;
	private String reduceMoney;
	private String description;
	private String createdAt;
	private String startDate;
	private String endDate;
	private String updatedAt;
	private String status;

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

	public String getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(String fullMoney) {
		this.fullMoney = fullMoney;
	}

	public String getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
