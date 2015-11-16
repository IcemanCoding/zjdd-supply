package com.zaijiadd.app.user.dao;


public interface UserInfoDAO {
	
	public Integer isUserInfoExist( String zaijiaddid );
	
	public Integer insertUserInfo( String zaijiaddid );
	
	public Integer getUserType( String zaijiaddid );

}
