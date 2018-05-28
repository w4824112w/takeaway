package com.takeaway.modular.dao.model;

import java.util.List;

public class ItemPropertys {
	private Integer id;
	private Integer itemId;
	private Integer propertyId;
	private Double price;
	private Integer isOpen;

	Propertys parentPropertys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Propertys getParentPropertys() {
		return parentPropertys;
	}

	public void setParentPropertys(Propertys parentPropertys) {
		this.parentPropertys = parentPropertys;
	}

}
