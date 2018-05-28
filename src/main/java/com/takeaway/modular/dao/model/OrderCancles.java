package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

public class OrderCancles {
	private Integer id;
	private Integer orderId;
	private String refundNo;
	private Integer size;
	private String name;
	private Double totalPrice;
	private Date createdAt;
	private Date operAt;
	private Integer operMan;
	
	private Orders orders;
	
	private List<OrderItems> orderItems;

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

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}


}
