package com.zaijiadd.app.external.dto;

import com.alibaba.fastjson.JSONObject;

public class BatchAddGoodsForZaijiaddDTO {
	
	private String zjtoken;
	private String storeId;
	private String goods;
	
	public String getZjtoken() {
		return zjtoken;
	}
	public void setZjtoken( String zjtoken ) {
		this.zjtoken = zjtoken;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId( String storeId ) {
		this.storeId = storeId;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods( String goods ) {
		this.goods = goods;
	}
	

}
