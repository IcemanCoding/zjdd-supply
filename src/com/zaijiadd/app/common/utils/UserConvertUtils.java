package com.zaijiadd.app.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;

public class UserConvertUtils {
	
	public static UserReceiveInfoDTO ConvertUserReceiveInfoDTO( JSONObject jsonRequest ) {
		
		UserReceiveInfoDTO dto = new UserReceiveInfoDTO();
		dto.setAddress( jsonRequest.getString( "address" ) );
		dto.setMobile( jsonRequest.getString( "mobile" ) );
		dto.setPostCode( jsonRequest.getString( "postCode" ) );
		dto.setRealName( jsonRequest.getString( "realName" ) );
		dto.setCityId( jsonRequest.getInteger( "cityId" ) );
		dto.setCountyId( jsonRequest.getInteger( "countyId" ) );
		dto.setProvinceId( jsonRequest.getInteger( "provinceId" ) );
		dto.setTownId( jsonRequest.getInteger( "townId" ) );
		
		return dto;
		
	}

}
