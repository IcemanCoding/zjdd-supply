package com.zaijiadd.app.common.utils;

public class StringUtils {

	public static String splitString( String str, String splitStr, Integer index ) {

		String[] strArr = str.split( splitStr );
		String resStr = "";

		if ( index == -1 || index > strArr.length - 1 ) {
			resStr = strArr[strArr.length - 1];
		} else {
			resStr = strArr[index];
		}

		return resStr;

	}
	
	public static String buildOrderId( String key ) {
		
		// orderId format: 'DDHOME' + yyyyMMddHHmmss + random(10)
		String orderId = "DDHOME";
		
		orderId += DateUtils.getSysDate( DateUtils.FULL_DATE_TIME2 );
		
		orderId += RandomUtils.getRandomNumber( 10 );
		
		if ( key.length() >= 9 ) {
			key.substring( 0, 9 );
		}
		
		orderId += key;
		
		return orderId;
		
	}
	
	public static String buildJdTradeOrderId( String key ) {
		
		// orderId format: 'DDHOME' + yyyyMMddHHmmss + random(10)
		String orderId = "JD&DD";
		
		orderId += DateUtils.getSysDate( DateUtils.FULL_DATE_TIME2 );
		
		orderId += RandomUtils.getRandomNumber( 6 );
		
		if ( key.length() >= 9 ) {
			key.substring( 0, 9 );
		}
		
		orderId += key;
		
		return orderId;
		
	}

	public static void main( String[] args ) {

		String t = buildOrderId( "7" );
		System.out.println( t );
		
		String s = splitString(
				"64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1",
				"`", 3 );
		System.out.println( s );

	}

}
