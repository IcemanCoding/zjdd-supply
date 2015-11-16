package com.zaijiadd.app.user.dao;

import java.util.Map;

import com.zaijiadd.app.user.dto.StoreInfoDTO;

public interface StoreInfoDAO {
	
	public Integer isStoreInfoExist( String storeId );
	
	public StoreInfoDTO getStoreInfo( String storeId );
	
	public Integer insertStoreInfo( Map<String, Object> params );

}
