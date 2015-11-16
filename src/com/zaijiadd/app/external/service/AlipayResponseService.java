package com.zaijiadd.app.external.service;

import java.util.Map;

public interface AlipayResponseService {
	
	public Boolean verify( Map<String, String> params );
	
	public String getSign( String transAmount, String orderId, String subject );
	
}
