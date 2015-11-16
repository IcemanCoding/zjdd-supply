package com.zaijiadd.app.external.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.DateUtils;
import com.zaijiadd.app.common.utils.HttpRequestUtils;
import com.zaijiadd.app.common.utils.Md5Utils;
import com.zaijiadd.app.external.service.JdRequestService;
import com.zaijiadd.app.external.utils.JdRequestUtils;
import com.zaijiadd.app.system.dao.SystemParamsDAO;
import com.zaijiadd.app.system.dao.TradeLogDAO;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.utils.constants.ConstantsForAccount;
import com.zaijiadd.app.utils.constants.ConstantsForSystemParams;

public class JdRequestServiceImpl implements JdRequestService {
	
	@Autowired
	private SystemParamsDAO systemParamsDao;
	
	@Autowired
	private TradeLogDAO tradeLogDao;
	
	private static final String JD_BIZ_URL = "http://bizapi.jd.com/";
	private static final String ACCESS_TOKEN_METHOD = "oauth2/access_token";
	private static final String PROVINCE_INFO_METHOD = "api/area/getProvince";
	private static final String CITY_INFO_METHOD = "api/area/getCity";
	private static final String COUNTY_INFO_METHOD = "api/area/getCounty";
	private static final String TOWN_INFO_METHOD = "api/area/getTown";
	private static final String PRICE_SUBMIT_METHOD = "api/order/jdPriceSubmit";
	private static final String CANCEL_ORDER_METHOD = "api/order/cancel";
	private static final String COMFIRM_ORDER_METHOD = "api/order/confirmOrder";

	@Override
	public String getAccessTokenByCurrent() {
		
//		String accessToken = systemParamsDao.getSystemParams( ConstantsForSystemParams.JD_ACCESS_TOKEN );
		String accessToken = "dnpaXWUl6gYZJn5LqRG1balLt";
		
		return accessToken;
		
	}
	
	public static void main( String[] args ) {
		
		JdRequestServiceImpl jd = new JdRequestServiceImpl();
		jd.testPriceSubmit();
//		jd.getAccessTokenInfo();
//		jd.getProvinceInfo();
//		jd.getCityInfo( 2 );
//		jd.getCountyInfo( 2813 );
//		jd.getTownInfo( 51976 );
	}
	
	@Override
	public String getAccessTokenInfo() {
		
		StringBuffer params = new StringBuffer();
		
		String timestamp = DateUtils.getSysDate( DateUtils.FULL_DATE_TIME );
		
		String sign = ConstantsForAccount.JD_CLIENT_SECRET + timestamp + 
				ConstantsForAccount.JD_CLIENT_ID + ConstantsForAccount.JD_USERNAME + 
				Md5Utils.MD5( ConstantsForAccount.JD_PASSWORD ) + "access_token" + 
				ConstantsForAccount.JD_CLIENT_SECRET;
		
		sign = Md5Utils.MD5( sign ).toUpperCase();
		
		params.append( "grant_type=access_token" );
		params.append( "&client_id=" + ConstantsForAccount.JD_CLIENT_ID );
		params.append( "&timestamp=" + timestamp );
		params.append( "&username=" + ConstantsForAccount.JD_USERNAME );
		params.append( "&password=" + Md5Utils.MD5( ConstantsForAccount.JD_PASSWORD ) );
		params.append( "&sign=" + sign );
		params.append( "&scope=" );
		
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, ACCESS_TOKEN_METHOD, params.toString(), "" );
		
System.out.println( res );

// {"success":true,"resultMessage":"","resultCode":"0000","result":{"uid":"3195750468","refresh_token_expires":1462877343418,"time":1447152543418,"expires_in":86400,"refresh_token":"Gj0RHvDaUkTMSmx7wI2Mq3TFXWcy2ciJ6lzOih8E","access_token":"DLJp9m1JNKG04Vf5OUaaZqLLU"}}

		JSONObject result = JSONObject.parseObject( res );
		String resultStr = result.getString( "result" );
		JSONObject resData = JSONObject.parseObject( resultStr );
		String accessToken = resData.getString( "access_token" );
		String refreshToken = resData.getString( "refresh_token" );
		String expiresIn = resData.getString( "expires_in" );
		String refreshTokenExpires = resData.getString( "refresh_token_expires" );
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "paramKey", ConstantsForSystemParams.JD_ACCESS_TOKEN );
		param.put( "paramValue", accessToken );
		systemParamsDao.updateSystemParams( param );
		
		param = new HashMap<String, Object>();
		param.put( "paramKey", ConstantsForSystemParams.JD_REFRESH_TOKEN );
		param.put( "paramValue", refreshToken );
		systemParamsDao.updateSystemParams( param );
		
		param = new HashMap<String, Object>();
		param.put( "paramKey", ConstantsForSystemParams.JD_EXPIRES_IN );
		param.put( "paramValue", expiresIn );
		systemParamsDao.updateSystemParams( param );
		
		param = new HashMap<String, Object>();
		param.put( "paramKey", ConstantsForSystemParams.JD_REFRESH_TOKEN_EXPIRES );
		param.put( "paramValue", refreshTokenExpires );
		systemParamsDao.updateSystemParams( param );

		return accessToken;
	
	}
	
	@Override
	public Map<String, Object> getProvinceInfo() {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map> resList = new ArrayList<Map>();
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, PROVINCE_INFO_METHOD, params.toString(), "" );
		
		System.out.println( res );
		
		JSONObject resData = JSONObject.parseObject( res );
		
		System.out.println( resData );
		
		String responseCode = resData.getString( "success" );
		if ( responseCode.equals( "true" ) ) {
			String resStr = resData.getString( "result" );
			resList = JdRequestUtils.processProvinceResData( resStr );
			resMap.put( "provinceInfo", resList );
			return resMap;
		}
		
		return null;
		
	}

	@Override
	public Map<String, Object> getCityInfo( Integer provinceId ) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map> resList = new ArrayList<Map>();
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&id=" + provinceId );
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, CITY_INFO_METHOD, params.toString(), "" );
		
		JSONObject resData = JSONObject.parseObject( res );
		
		System.out.println( resData );
		
		String responseCode = resData.getString( "success" );
		if ( responseCode.equals( "true" ) ) {
			String resStr = resData.getString( "result" );
			resList = JdRequestUtils.processCityResData( resStr );
			resMap.put( "cityInfo", resList );
			return resMap;
		}
		
		return null;
		
	}

	@Override
	public Map<String, Object> getCountyInfo( Integer cityId ) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map> resList = new ArrayList<Map>();
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&id=" + cityId );
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, COUNTY_INFO_METHOD, params.toString(), "" );
		
		JSONObject resData = JSONObject.parseObject( res );
		
		System.out.println( resData );
		
		String responseCode = resData.getString( "success" );
		if ( responseCode.equals( "true" ) ) {
			String resStr = resData.getString( "result" );
			resList = JdRequestUtils.processCountyResData( resStr );
			resMap.put( "countyInfo", resList );
			return resMap;
		}
		
		return null;
		
	}

	@Override
	public Map<String, Object> getTownInfo( Integer counryId ) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map> resList = new ArrayList<Map>();
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&id=" + counryId );
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, TOWN_INFO_METHOD, params.toString(), "" );
		
		if ( res == null || res.equals( "" ) ) {
			return null;
		}
		
		JSONObject resData = JSONObject.parseObject( res );
		String responseCode = resData.getString( "success" );
		if ( responseCode.equals( "true" ) ) {
			String resStr = resData.getString( "result" );
			resList = JdRequestUtils.processTownResData( resStr );
			resMap.put( "townInfo", resList );
			return resMap;
		}
		
		return null;
		
	}
	
	private void testPriceSubmit() {
		
		UserReceiveInfoDTO userReceiveInfoDto = new UserReceiveInfoDTO();
		userReceiveInfoDto.setAddress( "上海市闵行区梅陇二村" );
		userReceiveInfoDto.setCityId( 2813 );
		userReceiveInfoDto.setCountyId( 51976 );
		userReceiveInfoDto.setMobile( "13611920055" );
		userReceiveInfoDto.setPostCode( "200237" );
		userReceiveInfoDto.setProvinceId( 2 );
		userReceiveInfoDto.setRealName( "陈子卿" );
		userReceiveInfoDto.setTownId( 1 );
		JSONObject json = new JSONObject();
		json.put( "skuId", 1743181 );
		json.put( "num", 1 );
		JSONArray arr = new JSONArray();
		arr.add( json );
		String res = priceSubmit( userReceiveInfoDto, arr.toString(), "12312312331243test" );
		System.out.println( res );
		
	}

	@Override
	public String priceSubmit( UserReceiveInfoDTO userReceiveInfoDto, String goods, String jdOrderId ) {
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&thirdOrder=" + jdOrderId );
		params.append( "&sku=" + goods );
		params.append( "&name=" + userReceiveInfoDto.getRealName() );
		params.append( "&province=" + userReceiveInfoDto.getProvinceId() );
		params.append( "&city=" + userReceiveInfoDto.getCityId() );
		params.append( "&county=" + userReceiveInfoDto.getCountyId() );
		params.append( "&town=" + userReceiveInfoDto.getTownId() );
		params.append( "&address=" + userReceiveInfoDto.getAddress() );
		params.append( "&zip=" + userReceiveInfoDto.getPostCode() );
		params.append( "&mobile=" + userReceiveInfoDto.getMobile() );
		params.append( "&email=" + ConstantsForAccount.JD_EMAIL );
		params.append( "&invoiceState=" + ConstantsForAccount.JD_INVOICE_STATE );
		params.append( "&invoiceType=" + ConstantsForAccount.JD_INVOICE_TYPE );
		params.append( "&selectedInvoiceTitle=" + ConstantsForAccount.SELECTED_INVOICE_TITLE );
		params.append( "&companyName=" + ConstantsForAccount.JD_COMPANY_NAME );
		params.append( "&invoiceContent=" + ConstantsForAccount.JD_INVOICE_CONTENT );
		params.append( "&paymentType=" + ConstantsForAccount.JD_PAYMENT_TYPE );
		params.append( "&isUseBalance=" + ConstantsForAccount.JD_IS_USE_BALANCE );
		params.append( "&submitState=" + ConstantsForAccount.JD_SUBMIT_STATE );
		params.append( "&invoiceName=" + ConstantsForAccount.JD_INVOCE_NAME );
		params.append( "&invoicePhone=" + ConstantsForAccount.JD_INVOICE_PHONE );
		params.append( "&invoiceProvice=" + ConstantsForAccount.JD_INVOICE_PROVINCE );
		params.append( "&invoiceCity=" + ConstantsForAccount.JD_INVOICE_CITY );
		params.append( "&invoiceCounty=" + ConstantsForAccount.JD_INVOICE_COUNTY );
		params.append( "&invoiceAddress=" + ConstantsForAccount.JD_INVOICE_ADDRESS );
		
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, PRICE_SUBMIT_METHOD, params.toString(), "" );
		
		System.out.println( res );
		
		// {"success":false,"resultMessage":"商品列表中含有不能购买的商品[0]","resultCode":"2004","result":null}
		
		if ( res == null || res.equals( "" ) ) {
			
			return null;
			
		} else {
			
			JSONObject resData = JSONObject.parseObject( res );
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put( "orderId", jdOrderId );
			param.put( "responseData", res );
			param.put( "responseCode", resData.getString( "resultCode" ) );
			tradeLogDao.insertTradeLog( param );
			
			String success = resData.getString( "success" );
			if ( success.equalsIgnoreCase( "false" ) ) {
				return null;
			} else {
				String result = resData.getString( "result" );
				JSONObject resultJson = JSONObject.parseObject( result );
				String resJdOrderId = resultJson.getString( "jdOrderId" );
				
				return resJdOrderId;
			}
			
		}
		
	}

	@Override
	public void cancelJDOrder( String jdOrderId ) {
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&jdOrderId=" + jdOrderId );
		
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, CANCEL_ORDER_METHOD, params.toString(), "" );
		
		JSONObject resData = JSONObject.parseObject( res );
		
		System.out.println( resData );
		
	}

	@Override
	public String confirmOrder( String jdOrderId ) {
		
		String accessToken = getAccessTokenByCurrent();
		StringBuffer params = new StringBuffer();
		params.append( "token=" + accessToken );
		params.append( "&jdOrderId=" + jdOrderId );
		
		String res = HttpRequestUtils.sendUTF8Post( JD_BIZ_URL, COMFIRM_ORDER_METHOD, params.toString(), "" );
		
		JSONObject resData = JSONObject.parseObject( res );
		
		System.out.println( resData );
		
		return resData.toString();
		
	}

}
