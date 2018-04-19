package com.takeaway.modular.dao.model;

public class ItemShopcars {
	private Integer id;
	private Integer userId;
	private Integer itemId;
	private Integer itemPropertyId;
	private Integer num;
	private String batchNo;

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemPropertyId() {
		return itemPropertyId;
	}

	public void setItemPropertyId(Integer itemPropertyId) {
		this.itemPropertyId = itemPropertyId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
