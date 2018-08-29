package com.takeaway.modular.dao.dto;

public class UserAddressDto {
	private String id;
	private String userId;
	private String name;
	private String phone;
	private String address;
	
	private String lat;
	private String lng;
	
	private String isDistributionScope;	// 是否在范围内(0:不在范围内 1:在范围内)
	private String createdAt;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getIsDistributionScope() {
		return isDistributionScope;
	}

	public void setIsDistributionScope(String isDistributionScope) {
		this.isDistributionScope = isDistributionScope;
	}

}
