package com.takeaway.core.netpay.wxpay.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WxPayNotify {
	private Date timeEnd;
	private String outTradeNo;
	private String transactionId;
	private String totalFee;
	private String cashFee;
	private String bankType;
	private String errCodeDes;
	private String errCode;
	private String resultCode;
	private String sign;
	private String nonceStr;
	private String openid;
	private String tradeState;
	private String isVerify;

	public static WxPayNotify setWxPayNotify(Map<String, Object> map) {
		WxPayNotify wxPayNotify = new WxPayNotify();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date time_end = sdf.parse(map.get("time_end").toString());
			wxPayNotify.setTimeEnd(time_end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		wxPayNotify.setOutTradeNo(map.get("out_trade_no").toString());
		wxPayNotify.setTransactionId(map.get("transaction_id").toString());
		wxPayNotify.setTotalFee(map.get("total_fee").toString());
		wxPayNotify.setCashFee(map.get("cash_fee").toString());
		wxPayNotify.setBankType(map.get("bank_type").toString());
		wxPayNotify.setErrCodeDes(map.get("err_code_des").toString());
		wxPayNotify.setErrCode(map.get("err_code").toString());
		wxPayNotify.setResultCode(map.get("result_code").toString());
		wxPayNotify.setSign(map.get("sign").toString());
		wxPayNotify.setNonceStr(map.get("nonce_str").toString());
		wxPayNotify.setOpenid(map.get("openid").toString());
		wxPayNotify.setTradeState(map.get("trade_state").toString());
		wxPayNotify.setIsVerify("0");
		
/*		wxPayNotify.set("out_trade_no", map.get("out_trade_no"));
		wxPayNotify.set("transaction_id", map.get("transaction_id"));
		wxPayNotify.set("total_fee", map.get("total_fee"));
		wxPayNotify.set("cash_fee", map.get("cash_fee"));
		wxPayNotify.set("bank_type", map.get("bank_type"));
		wxPayNotify.set("err_code_des", map.get("err_code_des"));
		wxPayNotify.set("err_code", map.get("err_code"));
		wxPayNotify.set("result_code", map.get("result_code"));
		wxPayNotify.set("sign", map.get("sign"));
		wxPayNotify.set("nonce_str", map.get("nonce_str"));
		wxPayNotify.set("openid", map.get("openid"));
		wxPayNotify.set("trade_state", map.get("trade_state"));
		wxPayNotify.set("is_verify", 0);*/
		return wxPayNotify;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
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

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getCashFee() {
		return cashFee;
	}

	public void setCashFee(String cashFee) {
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

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

}
