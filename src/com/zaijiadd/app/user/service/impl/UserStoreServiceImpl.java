package com.zaijiadd.app.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.dao.UserStoreDAO;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.user.service.UserStoreService;

public class UserStoreServiceImpl implements UserStoreService {

	@Autowired
	private UserInfoDAO userInfoDao;
	
	@Autowired
	private UserStoreDAO userStoreDao;
	
	@Override
	public Boolean addUserStore( UserReceiveInfoDTO dto ) {
	
		/*
		 * 1、根据zaijiaddid获取userid
		 */
		String zaijiaddid = dto.getZaijiaddId();
		Integer userId = userInfoDao.isUserInfoExist( zaijiaddid );
		if ( userId == null ) {
			return false;
		}
		dto.setUserId( userId.toString() );
		
		/*
		 * 2、判断该userStore信息是否存在
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "storeId", dto.getStoreId() );
		Integer queryCount = userStoreDao.isUserStoreExist( userId );
		if ( queryCount > 0 ) {
			userStoreDao.updateUserStore( params );
		} else {
			userStoreDao.insertUserStore( params );
		}
		return true;
		
	}

}
