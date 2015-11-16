package com.zaijiadd.app.user.controller;

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
import com.zaijiadd.app.common.utils.UserConvertUtils;
import com.zaijiadd.app.common.viewmodel.RequestViewmodel;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.user.service.UserInfoService;
import com.zaijiadd.app.user.service.UserReceiveInfoService;
import com.zaijiadd.app.user.service.UserStoreService;

@RequestMapping ( "/user" )
@Controller
public class UserInfoController extends BaseController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserStoreService userStoreService;
	
	@Autowired
	private UserReceiveInfoService userReceiveInfoService;
	
	@RequestMapping ( value = "/saveUserReceiveInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> saveUserReceiveInfo( HttpServletRequest request, RequestViewmodel viewmodel ) {

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
		 * 2、json request convert
		 */
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		UserReceiveInfoDTO userReceiveInfoDto = UserConvertUtils.ConvertUserReceiveInfoDTO( jsonRequest );
		userReceiveInfoDto.setZaijiaddId( viewmodel.getZaijiaddId() );
		userReceiveInfoDto.setStoreId( storeId );
		
		/*
		 * 3、build user_info
		 */
		if ( !userInfoService.addUserInfo( userReceiveInfoDto ) ) {
			return ContainerUtils.buildHeadMap( resData, 0, "操作失败" );
		}
		
		/*
		 * 4、build user_store
		 */
		if ( !userStoreService.addUserStore( userReceiveInfoDto ) ) {
			return ContainerUtils.buildHeadMap( resData, 0, "操作失败" );
		}
		
		/*
		 * 5、build user_receive_info
		 */
		if ( !userReceiveInfoService.addUserReceiveInfo( userReceiveInfoDto ) ) {
			return ContainerUtils.buildHeadMap( resData, 0, "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
}
