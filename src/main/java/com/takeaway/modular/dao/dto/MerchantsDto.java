package com.takeaway.modular.dao.dto;

import java.util.List;

import com.takeaway.modular.dao.model.Activitys;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.MerchantPictures;

public class MerchantsDto {
	private String id;
	private String typeId;
	private String typeName;
	private String name;
	private String code;
	private String shortName;
	private String provinceId;
	private String cityId;
	private String address;
	private String star;
	private String managerName;
	private String managerPhone;
	private String description;
	private String platformCommission;
	private String tel;
	private String lat;
	private String lng;
	private String logo;
	private String notice;
	private String startDate;
	private String endDate;
	private String distributionInfo;
	private String startingPrice;
	private String fullFreeDistribution;
	private String distributionFee;
	private String distributionScope;	//	店铺范围
	private String distance;	//两个坐标点间的距离
	private String duration;	//两个坐标点间的距离使用汽车所需时间(单位：分)
	private String deliveryTime;
	private String isOnline;
	private String createdAt;
	private String updatedAt;
	private String status;

	private String dayPrice; // 每天营业额
	private String dayOrder; // 每天订单数
	private String pendingOrder; // 未处理订单数
	private String refundingOrder; // 退款中的外卖订单
	private String monthPrice; // 月营业额
	private String monthOrder; // 月订单数
	private String itemCount; // 商品数量
	private String itemTypeCount;
	private String activityCount;

	private Boolean isFavorite; // 是否收藏
	private List<Coupons> coupons; // 优惠卷
	private List<Activitys> activitys; // 满减活动
	
	private String score;	//评分
	
	private List<MerchantPictures> pictures;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(String platformCommission) {
		this.platformCommission = platformCommission;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDistributionInfo() {
		return distributionInfo;
	}

	public void setDistributionInfo(String distributionInfo) {
		this.distributionInfo = distributionInfo;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getFullFreeDistribution() {
		return fullFreeDistribution;
	}

	public void setFullFreeDistribution(String fullFreeDistribution) {
		this.fullFreeDistribution = fullFreeDistribution;
	}

	public String getDistributionFee() {
		return distributionFee;
	}

	public void setDistributionFee(String distributionFee) {
		this.distributionFee = distributionFee;
	}

	public String getDistributionScope() {
		return distributionScope;
	}

	public void setDistributionScope(String distributionScope) {
		this.distributionScope = distributionScope;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
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

	public String getItemCount() {
		return itemCount;
	}

	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}

	public String getItemTypeCount() {
		return itemTypeCount;
	}

	public void setItemTypeCount(String itemTypeCount) {
		this.itemTypeCount = itemTypeCount;
	}

	public String getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(String activityCount) {
		this.activityCount = activityCount;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public List<Coupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupons> coupons) {
		this.coupons = coupons;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Activitys> getActivitys() {
		return activitys;
	}

	public void setActivitys(List<Activitys> activitys) {
		this.activitys = activitys;
	}

	public String getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}

	public String getDayOrder() {
		return dayOrder;
	}

	public void setDayOrder(String dayOrder) {
		this.dayOrder = dayOrder;
	}

	public String getPendingOrder() {
		return pendingOrder;
	}

	public void setPendingOrder(String pendingOrder) {
		this.pendingOrder = pendingOrder;
	}

	public String getRefundingOrder() {
		return refundingOrder;
	}

	public void setRefundingOrder(String refundingOrder) {
		this.refundingOrder = refundingOrder;
	}

	public List<MerchantPictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<MerchantPictures> pictures) {
		this.pictures = pictures;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


}
