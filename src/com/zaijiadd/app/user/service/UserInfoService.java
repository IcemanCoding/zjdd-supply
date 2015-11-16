package com.zaijiadd.app.user.service;

import java.util.Map;

import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;

public interface UserInfoService {
	
	public Map<String, Object> getUserType( String zaijiaddId );
	
	public Boolean addUserInfo( UserReceiveInfoDTO dto );

}
