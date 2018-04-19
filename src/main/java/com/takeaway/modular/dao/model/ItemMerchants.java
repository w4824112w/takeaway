package com.takeaway.modular.dao.model;

import java.util.Date;

public class ItemMerchants {
	private Integer id;
	private Integer itemId;
	private Integer merchantId;
	private Integer isPuton;

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

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getIsPuton() {
		return isPuton;
	}

	public void setIsPuton(Integer isPuton) {
		this.isPuton = isPuton;
	}

}
