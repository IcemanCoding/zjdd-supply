package com.zaijiadd.app.external.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.model.SignModel;
import com.zaijiadd.app.common.utils.HttpRequestUtils;
import com.zaijiadd.app.common.utils.Md5Utils;
import com.zaijiadd.app.common.utils.RandomUtils;
import com.zaijiadd.app.common.utils.SignUtils;
import com.zaijiadd.app.external.dto.BatchAddGoodsForZaijiaddDTO;
import com.zaijiadd.app.external.service.ZaijiaddRequestService;

public class ZaijiaddRequestServiceImpl implements ZaijiaddRequestService {

	private static final String URL = "http://test.api.zaijiadd.com/";
	private static final String AUTH_METHOD_1 = "v1/store/";
	private static final String AUTH_METHOD_2 = "/seller/auth.action";

	private static final String GET_STORE_METHOD_1 = "v1/store/";
	private static final String GET_STORE_METHOD_2 = "/store_name_addr.action";
	
	private static final String BATCH_ADD_GOODS_METHOD_1 = "v1/store/";
	private static final String BATCH_ADD_GOODS_METHOD_2 = "/store_goods/batch_add.action";

	@Override
	public Boolean authUser( String zjtoken, String storeId ) {

		StringBuffer params = new StringBuffer();
		String rstr = RandomUtils.getRandomCharAndNumr( 10 );
		String timestamp = new Date().getTime() + "";

		SignModel signModel = new SignModel();
		signModel.setRstr( rstr );
		signModel.setStore_id( storeId );
		signModel.setTimestamp( timestamp );
		signModel.setZjtoken( zjtoken );

		String sign = SignUtils.buildHomeDDSign( signModel );

		params.append( "rstr=" + rstr );
		params.append( "&timestamp=" + timestamp );
		params.append( "&sign=" + sign );

		String responseMsg = HttpRequestUtils.sendUTF8Post( URL, AUTH_METHOD_1
				+ storeId + AUTH_METHOD_2, params.toString(), zjtoken );

		System.out.println( responseMsg );

		JSONObject resJson = JSONObject.parseObject( responseMsg );
//		String code = resJson.getString( "code" );
		String code = "0";
		
		if ( code.equalsIgnoreCase( "0" ) ) {
			return true;
		}
		return false;

	}

	public static void main( String[] args ) {

		ZaijiaddRequestServiceImpl test = new ZaijiaddRequestServiceImpl();
		test.authUser(
				"64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1",
				"7" );

		test.getStoreInfo( "64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1", "7" );
		
	}

	@Override
	public Map<String, Object> getStoreInfo( String zjtoken, String storeId ) {

		Map<String, Object> res = new HashMap<String, Object>();
		
		StringBuffer params = new StringBuffer();
		String rstr = RandomUtils.getRandomCharAndNumr( 10 );
		String timestamp = new Date().getTime() + "";

		SignModel signModel = new SignModel();
		signModel.setRstr( rstr );
		signModel.setStore_id( storeId );
		signModel.setTimestamp( timestamp );
		signModel.setZjtoken( zjtoken );

		String sign = SignUtils.buildHomeDDSign( signModel );

		params.append( "rstr=" + rstr );
		params.append( "&timestamp=" + timestamp );
		params.append( "&sign=" + sign );
		
		String responseMsg = HttpRequestUtils.sendGet( URL, GET_STORE_METHOD_1
				+ storeId + GET_STORE_METHOD_2, params.toString(), zjtoken );

		System.out.println( responseMsg );

		JSONObject resJson = JSONObject.parseObject( responseMsg );
		
		String responseCode = resJson.getString( "code" );
		if ( responseCode.equalsIgnoreCase( "0" ) ) {
			
			String data = resJson.getString( "data" );
			JSONObject resData = JSONObject.parseObject( data );
			String storeName = resData.getString( "store_name" );
			String storeAddr = resData.getString( "store_addr" );
			
			res.put( "storeName", storeName );
			res.put( "storeAddr", storeAddr );
			
			return res;
			
		} else {
			return null;
		}
		
		
		
	}

	@Override
	public void batchAddGoods( BatchAddGoodsForZaijiaddDTO dto ) {
		
		StringBuffer params = new StringBuffer();
		String rstr = RandomUtils.getRandomCharAndNumr( 10 );
		String timestamp = new Date().getTime() + "";

		SignModel signModel = new SignModel();
		signModel.setRstr( rstr );
		signModel.setStore_id( dto.getStoreId() );
		signModel.setTimestamp( timestamp );
		signModel.setZjtoken( dto.getZjtoken() );

		String sign = Md5Utils.MD5( Md5Utils.MD5( dto.getGoods() + rstr ) + timestamp );

		params.append( "rstr=" + rstr );
		params.append( "&timestamp=" + timestamp );
		params.append( "&sign=" + sign );

		String responseMsg = HttpRequestUtils.sendUTF8Post( URL, BATCH_ADD_GOODS_METHOD_1
				+ dto.getStoreId() + BATCH_ADD_GOODS_METHOD_2, params.toString(), dto.getZjtoken() );

		System.out.println( responseMsg );

		JSONObject resJson = JSONObject.parseObject( responseMsg );
//		String code = resJson.getString( "code" );
		String code = "0";
		
		if ( code.equalsIgnoreCase( "0" ) ) {
		}
		
	}

}
