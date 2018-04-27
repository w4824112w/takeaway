package com.takeaway.modular.dao.model;

public class CouponMerchants {
	private Integer id;
	private Integer type;
	private Integer targetId;
	private Integer merchantId;
	
	private Merchants merchant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Merchants getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchants merchant) {
		this.merchant = merchant;
	}

}
