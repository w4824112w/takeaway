package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

public class OrderReserves {
	private Integer id;
	private Integer orderId;
	private String reserveNo;
	private Integer size;
	private String name;
	private Date startDeliveryTime;
	private Date hopeDeliveryTime;
	private Date limitDeliveryTime;
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

	public String getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(String reserveNo) {
		this.reserveNo = reserveNo;
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

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}


}
