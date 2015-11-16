package com.zaijiadd.app.callback.service;

public interface AlipayCallBackService {
	
	public void processAlipayCallBack( String orderId );

	void buildJdOrderInfo( String orderId, String productId, Integer userId );

}
