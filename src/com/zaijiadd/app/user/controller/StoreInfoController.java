package com.zaijiadd.app.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.authorization.service.AuthorizationService;
import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.viewmodel.RequestViewmodel;
import com.zaijiadd.app.user.service.StoreInfoService;

@RequestMapping ( "/store" )
@Controller
public class StoreInfoController extends BaseController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	@RequestMapping ( value = "/storeNameAddrInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> storeNameAddrInfo( HttpServletRequest request, RequestViewmodel viewmodel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		/*
		 * 1、check inputs
		 */
		String zjtoken = viewmodel.getZjtoken();
		String storeId = viewmodel.getStoreId();
		
		if ( zjtoken == null || zjtoken.isEmpty() ) {
			return ContainerUtils.buildHeadMap( resData, 0, "zjtoken不能为空" );
		}
		if ( storeId == null || storeId.isEmpty() ) {
			return ContainerUtils.buildHeadMap( resData, 0, "store_id不能为空" );
		}
		
		if ( !authorizationService.AuthorizationUserInfo( zjtoken, storeId ) ) {
			return ContainerUtils.buildHeadMap( resData, -1, "用户尚未登录" );
		}
		
		/*
		 * 2、post zaijiadd's php get store_info
		 */
		resData = storeInfoService.getStoreInfo( zjtoken, storeId );
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
