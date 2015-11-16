package com.zaijiadd.app.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class ParseUtils {
	
	public static JSONObject loadJsonPostRequest( HttpServletRequest request ) {
		
		StringBuffer info = new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf = new BufferedInputStream( in );
			byte[] buffer = new byte[1024];
			int iRead;
			while ( ( iRead = buf.read( buffer ) ) != -1 ) {
				info.append( new String( buffer, 0, iRead, "UTF-8" ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
		JSONObject inputJson = JSONObject.parseObject( info.toString() );
		
		return inputJson;
		
	}

}
