package com.zaijiadd.app.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.config.RequestConfig;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtils {

	// private static final String BASE_URL =
	// "http://m.test.nonobank.com/msapi";

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet( String url, String method, String param, String zjtoken ) {

		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + method + "?" + param;
			URL realUrl = new URL( urlNameString );
			// 打开和URL之间的连接
			HttpURLConnection connection = ( HttpURLConnection ) realUrl
					.openConnection();
			connection.setConnectTimeout( 60000 );
			// 设置通用的请求属性
			connection.setRequestProperty( "accept", "*/*" );
			connection.setRequestProperty( "connection", "Keep-Alive" );
			connection.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
			if ( zjtoken != null && !"".equals( zjtoken ) ) {
				connection.setRequestProperty(
						"zjtoken",
						zjtoken );
			}
			
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// // 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader( new InputStreamReader(
					connection.getInputStream() ) );
			String line;
			while ( ( line = in.readLine() ) != null ) {
				result += line;
			}
		} catch ( Exception e ) {

		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if ( in != null ) {
					in.close();
				}
			} catch ( Exception e2 ) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost( String url, String method, String param ) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			try {
				System.setProperty( "sun.net.http.retryPost", "false" );
			} catch ( Exception e ) {
			}
			URL realUrl = new URL( url + method );
			// 打开和URL之间的连接
			HttpURLConnection conn = ( HttpURLConnection ) realUrl
					.openConnection();
			conn.setConnectTimeout( 60000 );
			conn.setReadTimeout( 60000 );

			// 设置通用的请求属性
			conn.setRequestProperty( "accept", "*/*" );
			conn.setRequestProperty( "connection", "Keep-Alive" );
			conn.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );

			// 发送POST请求必须设置如下两行
			conn.setDoOutput( true );
			conn.setDoInput( true );
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter( new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8" ) );
			// 发送请求参数
			out.print( param );

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader( new InputStreamReader(
					conn.getInputStream(), "UTF-8" ) );
			String line;
			while ( ( line = in.readLine() ) != null ) {

				result += line;

			}
		} catch ( Exception e ) {
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if ( out != null ) {
					out.close();
				}
				if ( in != null ) {
					in.close();
				}
			} catch ( IOException ex ) {
			}
		}
		return result;

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendUTF8Post( String url, String method, String param, String zjtoken ) {
		// System.err.println("sendPost param:" + param);
		System.out.println( url + method + param );
		
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			try {
				System.setProperty( "sun.net.http.retryPost", "false" );
			} catch ( Exception e ) {
			}
			URL realUrl = new URL( url + method );
			// 打开和URL之间的连接
			HttpURLConnection conn = ( HttpURLConnection ) realUrl
					.openConnection();
			conn.setConnectTimeout( 60000 );
			conn.setReadTimeout( 60000 );

			// 设置通用的请求属性
			conn.setRequestProperty( "accept", "*/*" );
			conn.setRequestProperty( "connection", "Keep-Alive" );
			conn.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
			conn.setRequestProperty( "Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8" );
			if ( zjtoken != null && !"".equals( zjtoken ) ) {
				conn.setRequestProperty(
						"zjtoken",
						zjtoken );
			}
			
			// 发送POST请求必须设置如下两行
			conn.setDoOutput( true );
			conn.setDoInput( true );
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter( new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8" ) );
			// 发送请求参数
			out.print( param );

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader( new InputStreamReader(
					conn.getInputStream(), "UTF-8" ) );
			String line;
			while ( ( line = in.readLine() ) != null ) {

				result += line;

			}
		} catch ( Exception e ) {
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if ( out != null ) {
					out.close();
				}
				if ( in != null ) {
					in.close();
				}
			} catch ( IOException ex ) {
			}
		}
		return result;

	}

	public static void main( String[] args ) {

		System.out.println( "go " );
		// 模拟post请求测试
		String url = "http://localhost:8080/";
		String method = "ddhomeapp/user/saveUserReceiveInfo";
		StringBuffer params = new StringBuffer();
		params.append( "timestamp=20202020xxxxxxxx" );
		params.append( "&timestamp=20151107150900" );
		params.append( "&sign=d96fbd616e86e47028933564d5441f2a" );
//		String res = sendUTF8Post( url, method, params.toString() );
//		System.out.println( res );

		// String url = "http://test.api.zaijiadd.com/";
		// String method = "v1/store/7/seller/auth.action";
		// StringBuffer params = new StringBuffer();
		// params.append( "rstr=20202020xxxxxxxx" );
		// params.append( "&timestamp=20151107150900" );
		// params.append( "&sign=d96fbd616e86e47028933564d5441f2a" );
		// String res = sendUTF8Post( url, method, params.toString() );
		// System.out.println( res );

	}

	public static String sendJsonPost( String url, String method,
			String jsonObject ) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			try {
				System.setProperty( "sun.net.http.retryPost", "false" );
			} catch ( Exception e ) {
			}
			URL realUrl = new URL( url + method );
			// 打开和URL之间的连接
			HttpURLConnection conn = ( HttpURLConnection ) realUrl
					.openConnection();
			conn.setConnectTimeout( 60000 );
			conn.setReadTimeout( 60000 );

			// 设置通用的请求属性
			conn.setRequestProperty( "accept", "*/*" );
			conn.setRequestProperty( "connection", "Keep-Alive" );
			conn.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
			conn.setRequestProperty( "Content-Type",
					"application/json;charset=utf-8" );
			// 发送POST请求必须设置如下两行
			conn.setDoOutput( true );
			conn.setDoInput( true );
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter( new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8" ) );
			// 发送请求参数
			out.print( jsonObject );

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader( new InputStreamReader(
					conn.getInputStream(), "UTF-8" ) );
			String line;
			while ( ( line = in.readLine() ) != null ) {

				result += line;

			}
		} catch ( Exception e ) {
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if ( out != null ) {
					out.close();
				}
				if ( in != null ) {
					in.close();
				}
			} catch ( IOException ex ) {
			}
		}
		return result;

	}

	private static void getDebterInfo() {

		String url = "http://www.test.nonobank.com/DebtApi";
		String method = "/GetDebtPackageBorrows/58185";

		StringBuffer params = new StringBuffer();

		// params.append( "boid=" + boid );
		// params.append( "&lastDate=" + lastDate );

		String res = HttpRequestUtils.sendPost( url, method, params.toString() );

		System.out.println( res );

		JSONObject resJson = JSONObject.parseObject( res );
		JSONObject resData = resJson.getJSONObject( "data" );

		System.out.println( resData );

		String realName = resData.getString( "real_name" );
		String academy = resData.getString( "academy" );
		System.out.println( "realname : " + realName + ", academy : "
				+ "academy" );

	}

}