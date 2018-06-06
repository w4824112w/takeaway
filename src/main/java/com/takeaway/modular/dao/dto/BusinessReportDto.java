package com.takeaway.modular.dao.dto;

public class BusinessReportDto {
	private String settlTime; // 时间
	private String totalPrice; // 总营业额
	private String totalRealTotalMoney; // 总实际收入
	private String allCount; // 全部订单数
	private String successCount; // 成功订单数
	private String refundCount; // 退款订单数
	private String refundTotalMoney; // 退款总额
	private String totalPackingCharge; // 餐盒费
	private String totalDeliverMoney; // 配送费
	private String totalCouponMoney; // 优惠券使用总金额
	private String totalActivityMoney; // 满立减总金额
	
	private String merchantId;
	private String startTime; // 开始日期
	private String endTime; // 结束日期

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

	public String getAllCount() {
		return allCount;
	}

	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(String refundCount) {
		this.refundCount = refundCount;
	}

	public String getRefundTotalMoney() {
		return refundTotalMoney;
	}

	public void setRefundTotalMoney(String refundTotalMoney) {
		this.refundTotalMoney = refundTotalMoney;
	}

	public String getTotalPackingCharge() {
		return totalPackingCharge;
	}

	public void setTotalPackingCharge(String totalPackingCharge) {
		this.totalPackingCharge = totalPackingCharge;
	}

	public String getTotalDeliverMoney() {
		return totalDeliverMoney;
	}

	public void setTotalDeliverMoney(String totalDeliverMoney) {
		this.totalDeliverMoney = totalDeliverMoney;
	}

	public String getTotalCouponMoney() {
		return totalCouponMoney;
	}

	public void setTotalCouponMoney(String totalCouponMoney) {
		this.totalCouponMoney = totalCouponMoney;
	}

	public String getTotalActivityMoney() {
		return totalActivityMoney;
	}

	public void setTotalActivityMoney(String totalActivityMoney) {
		this.totalActivityMoney = totalActivityMoney;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTotalRealTotalMoney() {
		return totalRealTotalMoney;
	}

	public void setTotalRealTotalMoney(String totalRealTotalMoney) {
		this.totalRealTotalMoney = totalRealTotalMoney;
	}

}
