package com.zaijiadd.app.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.external.service.JdRequestService;

@RequestMapping ( "/system" )
@Controller
public class SystemAddrController extends BaseController {
	
	@Autowired
	private JdRequestService jdRequestService;
	
	@RequestMapping ( value = "/provinceInfo" )
	@ResponseBody
	public Map<String, Object> provinceInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
		 */
		resData = jdRequestService.getProvinceInfo();
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/cityInfo" )
	@ResponseBody
	public Map<String, Object> cityInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		String provinceId = request.getParameter( "provinceId" );
		resData = jdRequestService.getCityInfo( Integer.parseInt( provinceId ) );
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/countyInfo" )
	@ResponseBody
	public Map<String, Object> countyInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		String cityId = request.getParameter( "cityId" );
		resData = jdRequestService.getCountyInfo( Integer.parseInt( cityId ) );
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/townInfo" )
	@ResponseBody
	public Map<String, Object> townInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		String countyId = request.getParameter( "countyId" );
		resData = jdRequestService.getTownInfo( Integer.parseInt( countyId ) );
		if ( resData == null ) {
			return ContainerUtils.buildHeadMap( resData, 0, "系统异常" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	

}
