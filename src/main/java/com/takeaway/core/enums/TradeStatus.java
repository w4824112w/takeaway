package com.takeaway.core.enums;

/**
 * 交易状态
 * @author yb
 *
 */
public enum TradeStatus {
	WAIT_FOR_PAYMENT("WAIT_FOR_PAYMENT"),
	TRADE_SUCCESS("TRADE_SUCCESS"),
	TRADE_FINISHED("TRADE_FINISHED"),
	DELIVERED("已配送");

    private String name;

    private TradeStatus(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
