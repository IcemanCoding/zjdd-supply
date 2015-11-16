package com.zaijiadd.app.common.utils;

import java.util.Date;

import com.zaijiadd.app.common.model.SignModel;
import com.zaijiadd.app.utils.constants.ConstantsForAccount;

public class SignUtils {

	public static String buildHomeDDSign( SignModel model ) {

		String tempStr = ConstantsForAccount.ZJ_SECRET_KEY
				+ model.getRstr();

		tempStr = Md5Utils.MD5( tempStr );

		return Md5Utils.MD5( tempStr + model.getTimestamp() );

	}
	
	public static void main( String[] args ) {

		SignModel model = new SignModel();
		model.setRstr( "ZBX5G7GU54" );
		model.setStore_id( "7" );
		model.setTimestamp( new Date().getTime() + "" );
		model.setZjtoken( "64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1" );
		String res = buildHomeDDSign( model );
		System.out.println( res );
		
	}

}
