package com.takeaway.modular.dao.dto;

public class ItemMerchantsDto {
	private String id;
	private String itemId;
	private String merchantId;
	private String isPuton;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIsPuton() {
		return isPuton;
	}

	public void setIsPuton(String isPuton) {
		this.isPuton = isPuton;
	}

}
