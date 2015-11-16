package com.zaijiadd.app.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.user.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDAO userInfoDao;

	@Override
	public Map<String, Object> getUserType( String zaijiaddId ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		/*
		 * 1、查询用户是否存在
		 * 存在 		return true
		 */
		Integer queryCount = userInfoDao.getUserType( zaijiaddId );
		if ( queryCount == null ) {
			// 用户不存在
			return null;
		} else {
			res.put( "usertype", queryCount );
			return res;
		}
		
	}

	@Override
	public Boolean addUserInfo( UserReceiveInfoDTO dto ) {

		/*
		 * 1、查询用户是否存在
		 * 存在 		return true
		 * 不存在	insert
		 */
		String zaijiaddid = dto.getZaijiaddId();
		Integer queryCount = userInfoDao.isUserInfoExist( zaijiaddid );
		if ( queryCount != null && queryCount > 0 ) {
			return true;
		}
		
		/*
		 * insert userInfo
		 */
		Integer insertCount = userInfoDao.insertUserInfo( zaijiaddid );
		if ( insertCount > 0 ) {
			return true;
		}
		
		return false;
	
	}

}
