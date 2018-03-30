package com.takeaway.modular.dao.dto;

public class OrderReservesDto {
	private String id;
	private String orderId;
	private String status;
	private String size;
	private String name;
	private String startDeliveryTime;
	private String hopeDeliveryTime;
	private String limitDeliveryTime;
	private String totalPrice;
	private String createdAt;
	private String operAt;
	private String operMan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDeliveryTime() {
		return startDeliveryTime;
	}

	public void setStartDeliveryTime(String startDeliveryTime) {
		this.startDeliveryTime = startDeliveryTime;
	}

	public String getHopeDeliveryTime() {
		return hopeDeliveryTime;
	}

	public void setHopeDeliveryTime(String hopeDeliveryTime) {
		this.hopeDeliveryTime = hopeDeliveryTime;
	}

	public String getLimitDeliveryTime() {
		return limitDeliveryTime;
	}

	public void setLimitDeliveryTime(String limitDeliveryTime) {
		this.limitDeliveryTime = limitDeliveryTime;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getOperAt() {
		return operAt;
	}

	public void setOperAt(String operAt) {
		this.operAt = operAt;
	}

	public String getOperMan() {
		return operMan;
	}

	public void setOperMan(String operMan) {
		this.operMan = operMan;
	}

}
