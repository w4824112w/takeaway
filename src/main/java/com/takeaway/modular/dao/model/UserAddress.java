package com.takeaway.modular.dao.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserAddress {
	@ApiModelProperty(value = "ID(编辑时必填)", required = false)
	private Integer id;
	@ApiModelProperty(value = "会员ID", required = false)
	private Integer userId;
	@ApiModelProperty(value = "收货人名称", required = false)
	private String name;
	@ApiModelProperty(value = "收货人手机", required = false)
	private String phone;
	@ApiModelProperty(value = "收货人地址", required = false)
	private String address;
	@ApiModelProperty(value = "经度", required = false)
	private String lat;
	@ApiModelProperty(value = "纬度", required = false)
	private String lng;
	
	private Integer isDistributionScope;	// 是否在范围内(0:不在范围内 1:在范围内)
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
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

	public Integer getIsDistributionScope() {
		return isDistributionScope;
	}

	public void setIsDistributionScope(Integer isDistributionScope) {
		this.isDistributionScope = isDistributionScope;
	}


}
