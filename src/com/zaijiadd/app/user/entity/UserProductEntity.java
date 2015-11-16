package com.zaijiadd.app.user.entity;

import java.math.BigDecimal;

public class UserProductEntity {
	
	private Integer userId;
	private String productId;
	private BigDecimal amount;
	private Integer count;
	private String transCode;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId( String productId ) {
		this.productId = productId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount( Integer count ) {
		this.count = count;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode( String transCode ) {
		this.transCode = transCode;
	}

}
