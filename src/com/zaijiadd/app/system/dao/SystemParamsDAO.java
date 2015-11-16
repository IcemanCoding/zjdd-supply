package com.zaijiadd.app.system.dao;

import java.util.Map;

public interface SystemParamsDAO {
	
	public String getSystemParams( String paramKey );
	
	public Integer updateSystemParams( Map<String, Object> params );

}
