package com.zaijiadd.app.user.dao;

import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;


public interface UserReceiveInfoDAO {
	
	public Integer insertUserReceiveInfo( UserReceiveInfoDTO dto );
	
	public UserReceiveInfoDTO getUserReceiveInfo( String userId );
	
}
