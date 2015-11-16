package com.zaijiadd.app.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.external.service.ZaijiaddRequestService;
import com.zaijiadd.app.user.dao.StoreInfoDAO;
import com.zaijiadd.app.user.dto.StoreInfoDTO;
import com.zaijiadd.app.user.service.StoreInfoService;

public class StoreInfoServiceImpl implements StoreInfoService {

	@Autowired
	private StoreInfoDAO storeInfoDao;
	
	@Autowired
	private ZaijiaddRequestService zaijiaddRequestService;
	
	@Override
	public Map<String, Object> getStoreInfo( String zjtoken, String storeId ) {

		Map<String, Object> res = new HashMap<String, Object>();
		
		/*
		 * 1、query current db get store_info
		 */
//		Integer queryCount = storeInfoDao.isStoreInfoExist( storeId );
		StoreInfoDTO storeInfo = storeInfoDao.getStoreInfo( storeId );
		
		if ( storeInfo == null ) {
			
			/*
			 * 2、not exist, query zaijiadd's php get data
			 */
			res = zaijiaddRequestService.getStoreInfo( zjtoken, storeId );
			res.put( "storeId", storeId );
			Integer insertCount = storeInfoDao.insertStoreInfo( res );
			
			return res;
			
		} else {
			
			/*
			 * 3、exist, return store_info entity
			 */
			
			res.put( "storeName", storeInfo.getStoreName() );
			res.put( "storeAddr", storeInfo.getStoreAddr() );
			return res;
			
		}
		
	}

}
