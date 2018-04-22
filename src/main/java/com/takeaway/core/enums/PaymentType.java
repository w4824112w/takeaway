package com.takeaway.core.enums;

/**
 * 支付类型
 * @author yb
 *
 */
public enum PaymentType {
	alipay("alipay"),
	weixin("weixin");

    private String name;

    private PaymentType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
