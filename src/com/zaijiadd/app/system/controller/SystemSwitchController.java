package com.zaijiadd.app.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.callback.service.AlipayCallBackService;
import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.system.dao.SystemParamsDAO;
import com.zaijiadd.app.utils.constants.ConstantsForSystemParams;

@RequestMapping ( "/system" )
@Controller
public class SystemSwitchController extends BaseController {
	
	@Autowired
	private SystemParamsDAO systemParamsDao;
	
	@Autowired
	private AlipayCallBackService service;
	
	@RequestMapping ( value = "/activitySwitch" )
	@ResponseBody
	public Map<String, Object> activitySwitch( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
		 */
		String activityFlag = systemParamsDao.getSystemParams( ConstantsForSystemParams.GYL_ACTIVITY_SWITCH );
		if ( activityFlag == null || activityFlag.equals( "" ) || activityFlag.equals( "0" ) ) {
			return ContainerUtils.buildHeadMap( resData, 0, "活动尚未开启" );
		}
		
		return ContainerUtils.buildHeadMap( resData, 1, "活动开启" );

	}

	@RequestMapping ( value = "/test" )
	@ResponseBody
	public Map<String, Object> test( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
		 */
		service.buildJdOrderInfo( "DDHOME2015111022031047391526141", "100001", 1 );
		
		return ContainerUtils.buildHeadMap( resData, 1, "活动开启" );

	}
	
}
