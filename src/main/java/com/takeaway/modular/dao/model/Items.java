package com.takeaway.modular.dao.model;

import java.util.Date;

public class Items {
	private Integer id;
	private Integer itemType;
	private String name;
	private String code;
	private String label;
	private Date putonDate;
	private Date putoffDate;
	private Date startDate;
	private Date endDate;
	private Date deliveryStartTime;
	private Date deliveryEndTime;
	private Double originPrice;
	private Double costPrice;
	private Double price;
	private String unit;
	private Integer salesVolume;
	private String tips;
	private String remain;
	private Integer stock;
	private Integer stockStatus;
	private String description;
	private Integer status;
	private Date createdAt;
	private Integer cityId;
	private Integer isPuton;
	private String origin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getPutonDate() {
		return putonDate;
	}

	public void setPutonDate(Date putonDate) {
		this.putonDate = putonDate;
	}

	public Date getPutoffDate() {
		return putoffDate;
	}

	public void setPutoffDate(Date putoffDate) {
		this.putoffDate = putoffDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Date deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Date getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Date deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public Double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getIsPuton() {
		return isPuton;
	}

	public void setIsPuton(Integer isPuton) {
		this.isPuton = isPuton;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
