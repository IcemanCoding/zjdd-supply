package com.zaijiadd.app.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.common.utils.StringUtils;
import com.zaijiadd.app.external.service.AlipayResponseService;
import com.zaijiadd.app.order.dao.OrderInfoDAO;
import com.zaijiadd.app.order.entity.OrderInfoEntity;
import com.zaijiadd.app.order.service.OrderInfoService;
import com.zaijiadd.app.product.dao.ProductInfoDAO;
import com.zaijiadd.app.product.entity.ProductInfoEntity;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.utils.constants.ConstantsForOrder;

public class OrderInfoServiceImpl implements OrderInfoService {
	
	@Autowired
	private ProductInfoDAO productInfoDao;
	
	@Autowired
	private OrderInfoDAO orderInfoDao;
	
	@Autowired
	private UserInfoDAO userInfoDao;
	
	@Autowired
	private AlipayResponseService alipayResponseService;

	@Override
	public Map<String, Object> buildOrderInfo( String zaijiaddId, String productId, String transCode, String transAmount, String subject ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		/*
		 * 1、check productId is exist, and get Entity
		 */
		ProductInfoEntity productInfoEntity = productInfoDao.getProductInfo( productId );
		if ( productInfoEntity == null ) {
			return null;
		}
		
		/*
		 * 2、query user_info get user_id by zaijiadd_id
		 */
		Integer userId = userInfoDao.isUserInfoExist( zaijiaddId );
		
		/*
		 * 3、build orderId
		 */
		String orderId = StringUtils.buildOrderId( userId.toString() );
		orderId = "123456789";
		
		/*
		 * 4、build order_info data
		 */
		OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
		orderInfoEntity.setAmount( productInfoEntity.getAmount() );
		orderInfoEntity.setOrderId( orderId );
		orderInfoEntity.setProductId( productId );
		orderInfoEntity.setTransCode( transCode );
		orderInfoEntity.setTransStatus( ConstantsForOrder.TRANS_STATUS_HANDLE );
		orderInfoEntity.setUserId( userId );
		
		orderInfoDao.insertOrderInfo( orderInfoEntity );
		String sign = alipayResponseService.getSign( transAmount, orderId, subject );
		
		res.put( "orderId", orderId );
		res.put( "sign", sign );
		
		return res;
		
	}

	@Override
	public Map<String, Object> buildOrderInfo( String zaijiaddId,
			String productId, String transCode ) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		/*
		 * 1、check productId is exist, and get Entity
		 */
		ProductInfoEntity productInfoEntity = productInfoDao.getProductInfo( productId );
		if ( productInfoEntity == null ) {
			return null;
		}
		
		/*
		 * 2、query user_info get user_id by zaijiadd_id
		 */
		Integer userId = userInfoDao.isUserInfoExist( zaijiaddId );
		
		/*
		 * 3、build orderId
		 */
		String orderId = StringUtils.buildOrderId( userId.toString() );
		
		/*
		 * 4、build order_info data
		 */
		OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
		orderInfoEntity.setAmount( productInfoEntity.getAmount() );
		orderInfoEntity.setOrderId( orderId );
		orderInfoEntity.setProductId( productId );
		orderInfoEntity.setTransCode( transCode );
		orderInfoEntity.setTransStatus( ConstantsForOrder.TRANS_STATUS_HANDLE );
		orderInfoEntity.setUserId( userId );
		
		orderInfoDao.insertOrderInfo( orderInfoEntity );
		
		res.put( "orderId", orderId );
		
		return res;
		
	}

}
