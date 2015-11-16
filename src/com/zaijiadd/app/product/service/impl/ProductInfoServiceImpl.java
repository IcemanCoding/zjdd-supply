package com.zaijiadd.app.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.product.dao.ProductInfoDAO;
import com.zaijiadd.app.product.dto.ProductGoodsDTO;
import com.zaijiadd.app.product.entity.ProductInfoEntity;
import com.zaijiadd.app.product.service.ProductInfoService;

public class ProductInfoServiceImpl implements ProductInfoService {
	
	@Autowired
	private ProductInfoDAO productInfoDao;

	@Override
	public Map<String, Object> getProductInfoList() {
		
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> resData = new HashMap<String, Object>();
		List<Map> resList = new ArrayList<Map>();
		/*
		 * 1、get product_info list
		 */
		List<ProductInfoEntity> productList = productInfoDao.getProductInfoList();
		for ( int i = 0; i < productList.size(); i++ ) {
			
			resData = new HashMap<String, Object>();
			
			ProductInfoEntity productInfoEntity = productList.get( i );
			String productId = productInfoEntity.getProductId();
			
			/*
			 * 2、get goods_info list
			 */
			List<ProductGoodsDTO> productGoodsList = productInfoDao.getProductGoodsList( productId );
			resData.put( "productId", productId );
			resData.put( "productList", productGoodsList );
			resList.add( resData );
			
		}
		res.put( "productInfo", resList );
		
		return res;
	
	}

}
