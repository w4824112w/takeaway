package com.takeaway.modular.dao.dto;

import java.util.Date;

public class OrderReserves {
	private Integer id;
	private Integer orderId;
	private Integer status;
	private Integer size;
	private String name;
	private Date startDeliveryTime;
	private Date hopeDeliveryTime;
	private Date limitDeliveryTime;
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

	public Date getStartDeliveryTime() {
		return startDeliveryTime;
	}

	public void setStartDeliveryTime(Date startDeliveryTime) {
		this.startDeliveryTime = startDeliveryTime;
	}

	public Date getHopeDeliveryTime() {
		return hopeDeliveryTime;
	}

	public void setHopeDeliveryTime(Date hopeDeliveryTime) {
		this.hopeDeliveryTime = hopeDeliveryTime;
	}

	public Date getLimitDeliveryTime() {
		return limitDeliveryTime;
	}

	public void setLimitDeliveryTime(Date limitDeliveryTime) {
		this.limitDeliveryTime = limitDeliveryTime;
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
