package com.zaijiadd.app.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.dao.UserReceiveInfoDAO;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.user.service.UserReceiveInfoService;

public class UserReceiveInfoServiceImpl implements UserReceiveInfoService {

	@Autowired
	private UserInfoDAO userInfoDao;
	
	@Autowired
	private UserReceiveInfoDAO userReceiveInfoDao;
	
	@Override
	public Boolean addUserReceiveInfo( UserReceiveInfoDTO dto ) {
		
		/*
		 * 1、根据zaijiaddid获取userid
		 */
		Integer userId = userInfoDao.isUserInfoExist( dto.getZaijiaddId() );
		if ( userId == null ) {
			return false;
		}
		dto.setUserId( userId.toString() );
		
		Integer insertCount = userReceiveInfoDao.insertUserReceiveInfo( dto );
		if ( insertCount > 0 ) {
			return true;
		}
		
		return false;
		
	}

}
