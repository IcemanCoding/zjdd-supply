package com.zaijiadd.app.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.authorization.service.AuthorizationService;
import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.common.viewmodel.RequestViewmodel;
import com.zaijiadd.app.order.service.OrderInfoService;
import com.zaijiadd.app.utils.constants.ConstantsForOrder;

@RequestMapping ( "/order" )
@Controller
public class OrderController extends BaseController {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	/**
	 * Chenzq
	 * 2015.11.06
	 * 生成订单
	 * input	sid、productId
	 */
	@RequestMapping ( value = "/addOrder", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> addOrder( HttpServletRequest request, RequestViewmodel viewmodel ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
		 */
		String zjtoken = viewmodel.getZjtoken();
		String storeId = viewmodel.getStoreId();
		if ( zjtoken == null || zjtoken.isEmpty() ) {
			return ContainerUtils.buildHeadMap( resData, 0, "zjtoken不能为空" );
		}

		if ( !authorizationService.AuthorizationUserInfo( zjtoken, storeId ) ) {
			return ContainerUtils.buildHeadMap( resData, -1, "用户尚未登录" );
		}
		
		/*
		 * 2、json request convert
		 */
		String productId = "";
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		if ( jsonRequest == null ) {
			productId = request.getParameter( "productId" ) + "";
		}
		productId = jsonRequest.getString( "productId" );
//		String transAmount = jsonRequest.getString( "transAmount" );
//		String subject = jsonRequest.getString( "subject" );
		
//		resData = orderInfoService.buildOrderInfo( viewmodel.getZaijiaddId(), productId, ConstantsForOrder.TRANS_CODE_RECHARGE, transAmount, subject );
		resData = orderInfoService.buildOrderInfo( viewmodel.getZaijiaddId(), productId, ConstantsForOrder.TRANS_CODE_RECHARGE );
		
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
