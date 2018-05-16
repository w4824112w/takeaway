package com.takeaway.modular.dao.model;

import java.util.Date;
import java.util.List;

public class Propertys {
	private Integer id;
	private String name;
	private Integer pid;
	private Date createdAt;
	private Date updatedAt;

	private String price;
	private String isOpen;
	private String itemId;
	
	List<Propertys> subPropertys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public List<Propertys> getSubPropertys() {
		return subPropertys;
	}

	public void setSubPropertys(List<Propertys> subPropertys) {
		this.subPropertys = subPropertys;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


}
