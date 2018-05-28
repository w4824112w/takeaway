package com.takeaway.modular.dao.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.takeaway.core.netpay.wxpay.bean.WxPayNotify;
import com.takeaway.modular.dao.dto.WxPayNotifysDto;

/**
 * 微信支付回调通知
 * 
 * @author hk
 *
 */
public class WxPayNotifys implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String timeEnd;
	private String outTradeNo;
	private String transactionId;
	private Double totalFee;
	private Double cashFee;
	private String bankType;
	private String errCodeDes;
	private String errCode;
	private String resultCode;
	private String sign;
	private String nonceStr;
	private String openid;
	private Integer isVerify;
	private Date verifyDate;
	private String tradeState;
	
	private String attach;
	
	public static WxPayNotifys setWxPayNotify(Map<String, Object> map) {
		WxPayNotifys wxPayNotify = new WxPayNotifys();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wxPayNotify.setTimeEnd(map.get("time_end").toString());
		wxPayNotify.setOutTradeNo(map.get("out_trade_no").toString());
		wxPayNotify.setTransactionId(map.get("transaction_id").toString());
		wxPayNotify.setTotalFee(Double.valueOf(map.get("total_fee").toString()));
		wxPayNotify.setCashFee(Double.valueOf(map.get("cash_fee").toString()));
		wxPayNotify.setBankType(map.get("bank_type").toString());
		wxPayNotify.setResultCode(map.get("result_code").toString());
		wxPayNotify.setSign(map.get("sign").toString());
		wxPayNotify.setNonceStr(map.get("nonce_str").toString());
		wxPayNotify.setOpenid(map.get("openid").toString());
		wxPayNotify.setIsVerify(0);
		
		return wxPayNotify;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getCashFee() {
		return cashFee;
	}

	public void setCashFee(Double cashFee) {
		this.cashFee = cashFee;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

}
