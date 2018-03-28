package com.takeaway.modular.dao.model;

import java.util.Date;

public class OrderCancles {
	private Integer id;
	private Integer orderId;
	private Integer status;
	private Integer size;
	private String name;
	private Double totalPrice;
	private Date createdAt;
	private Date operAt;
	private Integer operMan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getOperAt() {
		return operAt;
	}

	public void setOperAt(Date operAt) {
		this.operAt = operAt;
	}

	public Integer getOperMan() {
		return operMan;
	}

	public void setOperMan(Integer operMan) {
		this.operMan = operMan;
	}

}
