package com.takeaway.modular.dao.dto;

public class OrderCanclesDto {
	private String id;
	private String orderId;
	private String refundNo;
	private String size;
	private String name;
	private String totalPrice;
	private String createdAt;
	private String operAt;
	private String operMan;
	
	private String orderNo;
	private String merchantId;

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

}
