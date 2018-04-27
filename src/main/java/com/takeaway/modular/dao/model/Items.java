package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

import com.takeaway.modular.dao.dto.ItemMerchantsDto;
import com.takeaway.modular.dao.dto.ItemPicturesDto;
import com.takeaway.modular.dao.dto.ItemPropertysDto;

public class Items {
	private Integer id;
	private Integer itemType;
	private String name;
	private String code;
	private String label;
	private Double originPrice;
	private Double costPrice;
	private Double price;
	private Double packingCharge;
	private String unit;
	private Integer salesVolume;
	private String tips;
	private String remain;
	private Integer stock;
	private Integer stockStatus;
	private String description;
	private Integer status;
	private Date createdAt;
	private Date updatedAt;
	private Integer cityId;
	private String origin;

	private List<ItemMerchants> itemMerchants;
	private List<ItemPropertys> itemPropertys;
	private List<ItemPictures> pictures;
	
	private List<Merchants> merchants;
	private List<Propertys> propertys;

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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public List<ItemPictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<ItemPictures> pictures) {
		this.pictures = pictures;
	}


	public Double getPackingCharge() {
		return packingCharge;
	}

	public void setPackingCharge(Double packingCharge) {
		this.packingCharge = packingCharge;
	}

	public List<ItemMerchants> getItemMerchants() {
		return itemMerchants;
	}

	public void setItemMerchants(List<ItemMerchants> itemMerchants) {
		this.itemMerchants = itemMerchants;
	}

	public List<ItemPropertys> getItemPropertys() {
		return itemPropertys;
	}

	public void setItemPropertys(List<ItemPropertys> itemPropertys) {
		this.itemPropertys = itemPropertys;
	}

	public List<Merchants> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchants> merchants) {
		this.merchants = merchants;
	}

	public List<Propertys> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<Propertys> propertys) {
		this.propertys = propertys;
	}

}
