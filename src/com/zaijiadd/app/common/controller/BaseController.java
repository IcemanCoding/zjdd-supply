package com.zaijiadd.app.common.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.zaijiadd.app.common.utils.StringUtils;
import com.zaijiadd.app.common.viewmodel.RequestViewmodel;

public class BaseController {

	@ModelAttribute
	public RequestViewmodel buildRequest( HttpServletRequest request ) {
		
		RequestViewmodel viewmodel = new RequestViewmodel();
		
		Enumeration headerNames = request.getHeaderNames();

		while ( headerNames.hasMoreElements() ) {
			String key = ( String ) headerNames.nextElement();
			String value = request.getHeader( key );
			if ( key.equalsIgnoreCase( "zjtoken" ) ) {
				viewmodel.setZjtoken( value );
				String zaijiaddId = StringUtils.splitString( value, "`", -1 );
				viewmodel.setZaijiaddId( zaijiaddId );
			}
			if ( key.equalsIgnoreCase( "storeId" ) ) {
				viewmodel.setStoreId( value );
			}
		}
		
		return viewmodel;
		
	}
	
}
