package com.zaijiadd.app.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaijiadd.app.common.controller.BaseController;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.product.service.ProductInfoService;

@RequestMapping ( "/product" )
@Controller
public class ProductInfoController extends BaseController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@RequestMapping ( value = "/productList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> productList( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		resData = productInfoService.getProductInfoList();
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
