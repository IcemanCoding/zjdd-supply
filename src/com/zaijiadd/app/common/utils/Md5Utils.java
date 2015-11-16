package com.zaijiadd.app.common.utils;

import java.security.MessageDigest;

public class Md5Utils {

	public final static String MD5( String s ) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance( "MD5" );
			// 使用指定的字节更新摘要
			mdInst.update( btInput );
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[ j * 2 ];
			int k = 0;
			for ( int i = 0; i < j; i++ ) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String( str );
		} catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main( String[] args ) {
		System.out.println( MD5( "7" + "64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1" + "20202020xxxxxxxx" ) );
		
		System.out.println( MD5( "44ce09b731e939fab4802404f70a83ce" + "20151107150900" ) );
		//1EC32FF01576E66ACCF0D0F9362F7AFE
		//
		System.out.println( MD5( "加密" ) );
	}

}
