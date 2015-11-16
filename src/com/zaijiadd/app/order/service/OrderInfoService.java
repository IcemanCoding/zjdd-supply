package com.zaijiadd.app.order.service;

import java.util.Map;

public interface OrderInfoService {
	
	public Map<String, Object> buildOrderInfo( String zaijiaddId, String productId, String transCode, String transAmount, String subject );

	public Map<String, Object> buildOrderInfo( String zaijiaddId, String productId, String transCode );

}
