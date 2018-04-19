package com.takeaway.modular.dao.dto;

public class ItemShopcarsDto {
	private String id;
	private String userId;
	private String itemId;
	private String itemPropertyId;
	private String num;
	private String batchNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemPropertyId() {
		return itemPropertyId;
	}

	public void setItemPropertyId(String itemPropertyId) {
		this.itemPropertyId = itemPropertyId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
