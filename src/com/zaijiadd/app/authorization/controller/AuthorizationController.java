package com.zaijiadd.app.authorization.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.authorization.service.AuthorizationService;
import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.viewmodel.RequestViewmodel;
import com.zaijiadd.app.user.service.UserInfoService;

@RequestMapping ( "/authen" )
@Controller
public class AuthorizationController extends BaseController {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping ( value = "/authenUser", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> authenUser( HttpServletRequest request, RequestViewmodel viewmodel ) throws IOException  {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
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
			return ContainerUtils.buildResTimeOutMap( resData );
		}

		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/authenUserType", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> authenUserType( HttpServletRequest request, RequestViewmodel viewmodel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		Map<String, Object> resDataMap = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
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
		
		resDataMap = userInfoService.getUserType( viewmodel.getZaijiaddId() );
		if ( resDataMap == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "用户尚未注册" );
		} else {
			return ContainerUtils.buildResSuccessMap( resDataMap );
		}

	}

}
