package com.zaijiadd.app.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.authorization.service.AuthorizationService;
import com.zaijiadd.app.external.service.ZaijiaddRequestService;

public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	private ZaijiaddRequestService zaijiaddRequestService;
	
	@Override
	public Boolean AuthorizationUserInfo( String zjtoken, String storeId ) {
		
		/**
		 * 调用zaijiandd php后台进行身份认证
		 */
		if ( zaijiaddRequestService.authUser( zjtoken, storeId ) ) {
			return true;
		}
		
		return false;
		
	}

}
