package com.zaijiadd.app.external.service;

import java.util.Map;

import com.zaijiadd.app.external.dto.BatchAddGoodsForZaijiaddDTO;

public interface ZaijiaddRequestService {
	
	public Boolean authUser( String zjtoken, String storeId );
	
	public Map<String, Object> getStoreInfo( String zjtoken, String storeId );
	
	public void batchAddGoods( BatchAddGoodsForZaijiaddDTO dto );

}
