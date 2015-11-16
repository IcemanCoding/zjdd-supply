package com.zaijiadd.app.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.zaijiadd.app.utils.constants.ConstantsForResponse;

public class ContainerUtils {
	
	/**
	 * @Title: buildHeadMap
	 * @Description: 生成返回客户端的head报文 
	 * @param @param flag
	 * @param @param msg
	 * @param @return
	 * @return Map<String,String>
	 * @throws
	 */
	public static Map<String, Object> buildHeadMap( Map res, Integer flag, String msg ) {
		
		if ( res == null ) {
			res = new HashMap<String, Object>();
		}
		
		Map<String, Map> resHead = new HashMap<String, Map>();
		res.put( "flag", flag );
		res.put( "msg", msg );
		return res;
		
	}
	
	public static Map<String, Object> buildHeadMap( Map res, Integer flag, String msg, String responseCode ) {
		
		Map<String, Map> resHead = new HashMap<String, Map>();
		res.put( "flag", flag );
		res.put( "msg", msg );
		res.put( "responseCode", responseCode );
		return res;
		
	}
	
	/**
	 * @Title: buildBodyMap
	 * @Description: 生成返回客户端的body报文 
	 * @param @param res
	 * @param @return
	 * @return Map<String,Map>
	 * @throws
	 */
	public static Map<String, Map> buildDataMap( Map res ) {
		
		Map<String, Map> resData = new HashMap<String, Map>();
		resData.put( "data", res );
		return resData;
		
	}
	
	public static Map<String, Object> buildResMap( Map data, Integer flag, String msg ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put( "data", data );
		res.put( "flag", flag );
		res.put( "msg", msg );
		
		return res;
		
	}
	
	public static Map<String, Object> buildResTimeOutMap( Map data ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put( "data", data );
		res.put( "flag", ConstantsForResponse.RESPONSE_CODE_FAIL_TIMEOUT );
		res.put( "msg", ConstantsForResponse.RESPONSE_MESSAGE_FAIL_TIMEOUT );
		
		return res;
		
	}
	
	public static Map<String, Object> buildResSuccessMap( Map data ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put( "data", data );
		res.put( "flag", ConstantsForResponse.RESPONSE_CODE_SUCESS );
		res.put( "msg", ConstantsForResponse.RESPONSE_MESSAGE_SUCESS );
		
		return res;
		
	}
	
	public static Map<String, Object> buildResMap( Map data, Integer flag, String msg, String responseCode ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put( "data", data );
		res.put( "flag", flag );
		res.put( "msg", msg );
		res.put( "responseCode", responseCode );
		
		return res;
		
	}
	
	public static Map<String, Object> entityToMap( Object bean ) {
		
		Class<? extends Object> clazz = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        
        try {
            beanInfo = Introspector.getBeanInfo( clazz );
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for ( int i = 0; i < propertyDescriptors.length; i++ ) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if ( !propertyName.equals( "class" ) ) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = null;
                    result = readMethod.invoke( bean, new Object[0] );
                    if ( null != propertyName ) {
                        propertyName = propertyName.toString();
                    }
                    if ( null != result ) {
                        result = result.toString();
                    }
                    returnMap.put( propertyName, result );
                }
            }
        } catch ( IntrospectionException e ) {
            System.out.println( "分析类属性失败" );
        } catch ( IllegalAccessException e ) {
            System.out.println( "实例化 JavaBean 失败" );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "映射错误" );
        } catch ( InvocationTargetException e ) {
            System.out.println( "调用属性的 setter 方法失败" );
        }
        return returnMap;
		
	}
	
	public static String hashMapToJson( HashMap map ) {  
		
        String string = "{";  
        for ( Iterator it = map.entrySet().iterator(); it.hasNext(); ) {  
            Entry e = ( Entry ) it.next();  
            string += "'" + e.getKey() + "':";  
            string += "'" + e.getValue() + "',";  
        }  
        string = string.substring( 0, string.lastIndexOf( "," ) );  
        string += "}";  
        return string;  
    }  
	
	public static int[] stringToIntArray( String[] arr ) {
		
		int[] int_arr = new int[ arr.length ];
		
		for (int i = 0; i < arr.length; i++) {
			int_arr[i] = Integer.parseInt( arr[i] );
		}
		
		return int_arr;
		
	}

}
