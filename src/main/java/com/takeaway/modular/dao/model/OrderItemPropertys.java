package com.takeaway.modular.dao.model;

import java.util.Date;

public class OrderItemPropertys {
	private Integer id;
	private Integer orderItemId;
	private Integer itemPropertyId;
	private Date createdAt;
	
	private String propertyName;
	private Double price;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getItemPropertyId() {
		return itemPropertyId;
	}

	public void setItemPropertyId(Integer itemPropertyId) {
		this.itemPropertyId = itemPropertyId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
