package com.zaijiadd.app.external.service;

import java.util.Map;

import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;

public interface JdRequestService {
	
	public String getAccessTokenByCurrent();
	
	public String getAccessTokenInfo();
	
	public Map<String, Object> getProvinceInfo();
	
	public Map<String, Object> getCityInfo( Integer provinceId );
	
	public Map<String, Object> getCountyInfo( Integer cityId );
	
	public Map<String, Object> getTownInfo( Integer counryId );
	
	public String priceSubmit( UserReceiveInfoDTO userReceiveInfoDto, String goods, String jdOrderId );
	
	public void cancelJDOrder( String jdOrderId );
	
	public String confirmOrder( String jdOrderId );

}
