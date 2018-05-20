package com.takeaway.modular.dao.dto;

public class BossReportDto {
	private String settlTime; // 时间
	private String totalPrice; // 总营业额
	private String successCount; // 成功订单数
	private String maxMoney; // 最大订单
	private String minMoney; // 最小订单
	private String accessTimes; // 用户点击量
	private String realTotalMoney; // 实际收入

	private String merchantId;
	private String reportTime;

	public String getSettlTime() {
		return settlTime;
	}

	public void setSettlTime(String settlTime) {
		this.settlTime = settlTime;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	public String getAccessTimes() {
		return accessTimes;
	}

	public void setAccessTimes(String accessTimes) {
		this.accessTimes = accessTimes;
	}

	public String getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(String realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

}
