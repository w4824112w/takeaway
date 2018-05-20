package com.takeaway.modular.dao.dto;

import java.util.List;

import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.Merchants;
import com.takeaway.modular.dao.model.Propertys;

public class ItemsDto {
	private String id;
	private String itemType;
	private String name;
	private String code;
	private String label;
	private String originPrice;
	private String costPrice;
	private String price;
	private String packingCharge;
	private String unit;
	private String salesVolume;
	private String tips;
	private String remain;
	private String stock;
	private String stockStatus;
	private String description;
	private String status;
	private String createdAt;
	private String updatedAt;
	private String cityId;
	private String origin;

	private String itemTypeName;
	private String isPuton;
	private String merchantId;
	private String merchantName;
	
	private String monthPrice; // 月营业额
	private String monthOrder; // 月订单数

	private List<ItemMerchants> itemMerchants;
	private List<ItemPropertys> itemPropertys;
	private List<ItemPicturesDto> pictures;
	
	private List<Merchants> merchants;
	private List<Propertys> propertys;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
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
	public String getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}
	public String getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPackingCharge() {
		return packingCharge;
	}
	public void setPackingCharge(String packingCharge) {
		this.packingCharge = packingCharge;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(String salesVolume) {
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
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getIsPuton() {
		return isPuton;
	}
	public void setIsPuton(String isPuton) {
		this.isPuton = isPuton;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	public List<ItemPicturesDto> getPictures() {
		return pictures;
	}
	public void setPictures(List<ItemPicturesDto> pictures) {
		this.pictures = pictures;
	}
	public String getMonthPrice() {
		return monthPrice;
	}
	public void setMonthPrice(String monthPrice) {
		this.monthPrice = monthPrice;
	}
	public String getMonthOrder() {
		return monthOrder;
	}
	public void setMonthOrder(String monthOrder) {
		this.monthOrder = monthOrder;
	}


}
