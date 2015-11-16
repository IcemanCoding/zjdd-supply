package com.zaijiadd.app.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {

	public static String getRandomCharAndNumr( Integer length ) {
		
		String str = "";
		Random random = new Random();
		for ( int i = 0; i < length; i++ ) {
			boolean b = random.nextBoolean();
			if ( b ) { // 字符串
				str += ( char ) ( 65 + random.nextInt( 26 ) );// 取得大写字母
			} else { // 数字
				str += String.valueOf( random.nextInt( 10 ) );
			}
		}
		return str;
		
	}
	
	public static String getRandomNumber( Integer length ) {
		
		String str = "";
		Random random = new Random();
		for ( int i = 0; i < length; i++ ) {
			str += String.valueOf( random.nextInt( 10 ) );
		}
		return str;
		
	}

	public static boolean isRandomUsable( String str ) {
		
		String regExp = "^([0-9]+)|([A-Za-z]+)$";
		Pattern pat = Pattern.compile( regExp );
		Matcher mat = pat.matcher( str );
		return mat.matches();
		
	}
	
	public static void main( String[] args ) {
		
		String s = getRandomNumber( 10 );
		System.out.println( s );
		
	}

}
