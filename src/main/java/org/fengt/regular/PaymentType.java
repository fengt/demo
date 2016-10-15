package org.fengt.regular;

public enum PaymentType {
	
	CASH("现金"), POS("POS机"), MOBILEPOSPAY("移动POS机"),WECHATPAY("微信支付"), ALIPAY("支付宝");
	
	public String description;
	
	PaymentType(String description) {
		this.description = description;
	}

}
