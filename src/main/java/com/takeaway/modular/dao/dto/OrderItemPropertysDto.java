package com.takeaway.modular.dao.dto;

import java.util.Date;

public class OrderItemPropertysDto {
	private String id;
	private String orderItemId;
	private String itemPropertyId;
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getItemPropertyId() {
		return itemPropertyId;
	}

	public void setItemPropertyId(String itemPropertyId) {
		this.itemPropertyId = itemPropertyId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
