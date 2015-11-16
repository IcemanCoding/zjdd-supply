package com.zaijiadd.app.product.dto;

import java.math.BigDecimal;

public class ProductGoodsDTO {
	
	private Integer categoryId;
	private String categoryCode;
	private String categoryName;
	private String goodsId;
	private String goodsName;
	private Integer count;
	private BigDecimal unitPrice;
	private BigDecimal totalAmount;
	private Integer id;
	private String spec;
	private String pic;
	private String name;
	private Integer category_id;
	private BigDecimal purchase_price;
	private Integer stock;
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id( Integer category_id ) {
		this.category_id = category_id;
	}
	public BigDecimal getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price( BigDecimal purchase_price ) {
		this.purchase_price = purchase_price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock( Integer stock ) {
		this.stock = stock;
	}
	
	public String getSpec() {
		return spec;
	}
	public void setSpec( String spec ) {
		this.spec = spec;
	}
	public String getPic() {
		return pic;
	}
	public void setPic( String pic ) {
		this.pic = pic;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId( Integer categoryId ) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode( String categoryCode ) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName( String categoryName ) {
		this.categoryName = categoryName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId( String goodsId ) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName( String goodsName ) {
		this.goodsName = goodsName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount( Integer count ) {
		this.count = count;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice( BigDecimal unitPrice ) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount( BigDecimal totalAmount ) {
		this.totalAmount = totalAmount;
	}
	public Integer getId() {
		return id;
	}
	public void setId( Integer id ) {
		this.id = id;
	}
	
}
