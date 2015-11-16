package com.zaijiadd.app.user.dao;

import java.util.Map;


public interface UserStoreDAO {

	public Integer isUserStoreExist( Integer userId );
	
	public Integer insertUserStore( Map<String, Object> params );
	
	public Integer updateUserStore( Map<String, Object> params );
	
}
