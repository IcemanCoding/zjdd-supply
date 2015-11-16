package com.zaijiadd.app.callback.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.callback.service.AlipayCallBackService;
import com.zaijiadd.app.external.service.AlipayResponseService;
import com.zaijiadd.app.system.dao.TradeLogDAO;

@RequestMapping ( "/callback" )
@Controller
public class AlipayCallBackController {

	@Autowired
	private TradeLogDAO tradeLogDao;
	
	@Autowired
	private AlipayCallBackService alipayCallBackService;
	
	@Autowired
	private AlipayResponseService alipayRespService;

	@RequestMapping ( "/alipayCallback" )
	@ResponseBody
	public String alipayCallBack( HttpServletRequest request ) {

		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for ( Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = ( String ) iter.next();
			String[] values = ( String[] ) requestParams.get( name );
			String valueStr = "";
			for ( int i = 0; i < values.length; i++ ) {
				valueStr = ( i == values.length - 1 ) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String( valueStr.getBytes( "ISO-8859-1" ), "gbk" );
			} catch ( UnsupportedEncodingException e ) {
				e.printStackTrace();
			}
			params.put( name, valueStr );
		}

		// 商户订单号
		String out_trade_no = "";
		String trade_no = "";
		String trade_status = "";
		try {
			out_trade_no = new String( request.getParameter( "out_trade_no" )
					.getBytes( "ISO-8859-1" ), "UTF-8" );
			trade_no = new String( request.getParameter( "trade_no" ).getBytes(
					"ISO-8859-1" ), "UTF-8" );
			trade_status = new String( request.getParameter( "trade_status" )
					.getBytes( "ISO-8859-1" ), "UTF-8" );

		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if ( alipayRespService.verify( params ) ) {
			
			// 验证成功
			Map<String, Object> param = new HashMap<String, Object>();
			param.put( "orderId", out_trade_no );
			param.put( "responseCode", trade_status );
			param.put( "responseData", params.toString() );
			tradeLogDao.insertTradeLog( param );

			if ( trade_status.equals( "TRADE_FINISHED" ) || trade_status.equals( "TRADE_SUCCESS" ) ) {
			
				try {
					alipayCallBackService.processAlipayCallBack( out_trade_no );
				} catch ( Exception e ) {
					System.out.println( e );
				}
			
			} 

			return "success";

		} else {
			// 验证失败
			return "fail";
		}

	}

}
