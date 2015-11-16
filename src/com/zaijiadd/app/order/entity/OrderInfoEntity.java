package com.zaijiadd.app.order.entity;

import java.math.BigDecimal;

public class OrderInfoEntity {
	
	private String orderId;
	private Integer userId;
	private String transCode;
	private String productId;
	private BigDecimal amount;
	private Integer transStatus;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId( String orderId ) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode( String transCode ) {
		this.transCode = transCode;
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
	public Integer getTransStatus() {
		return transStatus;
	}
	public void setTransStatus( Integer transStatus ) {
		this.transStatus = transStatus;
	}

}
