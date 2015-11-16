package com.zaijiadd.app.product.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfoEntity {
	
	private String productId;
	private String productName;
	private BigDecimal amount;
	private Integer status;
	private Date createDate;
	private Date updateDate;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId( String productId ) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName( String productName ) {
		this.productName = productName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus( Integer status ) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate( Date updateDate ) {
		this.updateDate = updateDate;
	}
	
}
