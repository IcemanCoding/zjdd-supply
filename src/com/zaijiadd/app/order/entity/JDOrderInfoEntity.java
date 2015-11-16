package com.zaijiadd.app.order.entity;

public class JDOrderInfoEntity {
	
	private String orderId;
	private String jdTradeOrderId;
	private Integer userId;
	private Integer batchId;
	private String sku;
	private Integer transStatus;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId( String orderId ) {
		this.orderId = orderId;
	}
	public String getJdTradeOrderId() {
		return jdTradeOrderId;
	}
	public void setJdTradeOrderId( String jdTradeOrderId ) {
		this.jdTradeOrderId = jdTradeOrderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId( Integer userId ) {
		this.userId = userId;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId( Integer batchId ) {
		this.batchId = batchId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku( String sku ) {
		this.sku = sku;
	}
	public Integer getTransStatus() {
		return transStatus;
	}
	public void setTransStatusStatus( Integer transStatus ) {
		this.transStatus = transStatus;
	}

}
