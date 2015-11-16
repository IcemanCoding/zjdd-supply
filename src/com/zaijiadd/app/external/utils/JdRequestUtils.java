package com.zaijiadd.app.external.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class JdRequestUtils {

	public static List<Map> processProvinceResData( String res ) {

		List<Map> resList = new ArrayList<Map>();
		Map<String, Object> resMap = new HashMap<String, Object>();

		JSONObject resJson = JSONObject.parseObject( res );

		for ( java.util.Map.Entry<String, Object> entry : resJson.entrySet() ) {
			resMap = new HashMap<String, Object>();
			if ( entry.getKey().equalsIgnoreCase( "台湾" ) ) {
				continue;
			}
			resMap.put( "provinceName", entry.getKey() );
			resMap.put( "provinceId", entry.getValue() );
			resList.add( resMap );
		}

		return resList;

	}
	
	public static List<Map> processCityResData( String res ) {

		List<Map> resList = new ArrayList<Map>();
		Map<String, Object> resMap = new HashMap<String, Object>();

		JSONObject resJson = JSONObject.parseObject( res );

		for ( java.util.Map.Entry<String, Object> entry : resJson.entrySet() ) {
			resMap = new HashMap<String, Object>();
			resMap.put( "cityName", entry.getKey() );
			resMap.put( "cityId", entry.getValue() );
			resList.add( resMap );
		}

		return resList;

	}
	
	public static List<Map> processCountyResData( String res ) {

		List<Map> resList = new ArrayList<Map>();
		Map<String, Object> resMap = new HashMap<String, Object>();

		JSONObject resJson = JSONObject.parseObject( res );

		for ( java.util.Map.Entry<String, Object> entry : resJson.entrySet() ) {
			resMap = new HashMap<String, Object>();
			resMap.put( "countyName", entry.getKey() );
			resMap.put( "countyId", entry.getValue() );
			resList.add( resMap );
		}

		return resList;

	}
	
	public static List<Map> processTownResData( String res ) {

		List<Map> resList = new ArrayList<Map>();
		Map<String, Object> resMap = new HashMap<String, Object>();

		JSONObject resJson = JSONObject.parseObject( res );
		
		for ( java.util.Map.Entry<String, Object> entry : resJson.entrySet() ) {
			resMap = new HashMap<String, Object>();
			resMap.put( "townName", entry.getKey() );
			resMap.put( "townId", entry.getValue() );
			resList.add( resMap );
		}

		return resList;

	}
	
	public static void main( String[] args ) {
		
		String str = "{'北京':1,'上海':2,'香港':3}";
		
		processProvinceResData( str );
		
	}

}
