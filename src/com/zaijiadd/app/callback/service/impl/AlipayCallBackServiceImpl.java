package com.zaijiadd.app.callback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.callback.service.AlipayCallBackService;
import com.zaijiadd.app.common.utils.StringUtils;
import com.zaijiadd.app.external.service.JdRequestService;
import com.zaijiadd.app.external.service.ZaijiaddRequestService;
import com.zaijiadd.app.order.dao.OrderInfoDAO;
import com.zaijiadd.app.order.entity.JDOrderInfoEntity;
import com.zaijiadd.app.order.entity.OrderInfoEntity;
import com.zaijiadd.app.product.dao.ProductInfoDAO;
import com.zaijiadd.app.product.dto.ProductGoodsDTO;
import com.zaijiadd.app.user.dao.UserProductDAO;
import com.zaijiadd.app.user.dao.UserReceiveInfoDAO;
import com.zaijiadd.app.user.dto.UserReceiveInfoDTO;
import com.zaijiadd.app.utils.constants.ConstantsForOrder;

public class AlipayCallBackServiceImpl implements AlipayCallBackService {

	@Autowired
	private OrderInfoDAO orderInfoDao;

	@Autowired
	private UserProductDAO userProductDao;

	@Autowired
	private ZaijiaddRequestService zaijiaddRequestService;

	@Autowired
	private JdRequestService jdRequestService;

	@Autowired
	private ProductInfoDAO productInfoDao;
	
	@Autowired
	private UserReceiveInfoDAO userReceiveInfoDao;

	@Override
	public void processAlipayCallBack( String orderId ) {

		/*
		 * update order_info status
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "orderId", orderId );
		params.put( "transStatus", ConstantsForOrder.TRANS_STATUS_SUCCESS );
		orderInfoDao.updateOrderInfo( params );

		/*
		 * get order_info entity
		 */
		OrderInfoEntity orderInfoEntity = orderInfoDao.getOrderInfo( orderId );

		/*
		 * insert user_product
		 */
		params = new HashMap<String, Object>();
		params.put( "userId", orderInfoEntity.getUserId() );
		params.put( "amount", orderInfoEntity.getAmount() );
		params.put( "productId", orderInfoEntity.getProductId() );
		params.put( "transCode", ConstantsForOrder.TRANS_CODE_RECHARGE );
		params.put( "count", 1 );

		userProductDao.insertUserProduct( params );

		/*
		 * call jd build order
		 */
		buildJdOrderInfo( orderId, orderInfoEntity.getProductId(), orderInfoEntity.getUserId() );

		/*
		 * call zaijiadd's php update goods
		 */
//		List<ProductGoodsDTO> dto = buildBatchAddGoods( orderInfoEntity
//				.getProductId() );
//		String jsonText = JSON.toJSONString( dto, true );
//		
//		BatchAddGoodsForZaijiaddDTO goodsDto = new BatchAddGoodsForZaijiaddDTO();
//		goodsDto.setGoods( jsonText );
//		goodsDto.setStoreId( "7" );
//		goodsDto.setZjtoken( "64C8B923-8ABE-42CE-ABF5-AEBACDA09DF3-75022-00009F62C13819AC`AATmZNU1hZX/rUCJTc2o4Z5bMbj9Ab7I`1" );
		
//		zaijiaddRequestService.batchAddGoods( goodsDto );

	}
	
	@Override
	public void buildJdOrderInfo( String orderId, String productId, Integer userId ) {
		
		/*
		 * get user_receive_info by userId
		 */
		UserReceiveInfoDTO userReceiveInfo = userReceiveInfoDao.getUserReceiveInfo( userId.toString() );
		
		/*
		 * get goods_info by productId
		 */
		List<ProductGoodsDTO> productGoodsList = productInfoDao.getProductgoodsInfoList( productId );
		
		/*
		 * split order  50count/order
		 */
			
		for ( int i = 1; i < 6; i++ ) {
			
			String goodsInfo = buildJdOrderGoods( productGoodsList, i );
			
			String jdTradeOrderId = StringUtils.buildJdTradeOrderId( userId.toString() );
			
			/*
			 * new JDOrderInfoEntity, and set fields
			 */
			JDOrderInfoEntity jdOrderInfoEntity = new JDOrderInfoEntity();
			jdOrderInfoEntity.setBatchId( i );
			jdOrderInfoEntity.setJdTradeOrderId( jdTradeOrderId );
			jdOrderInfoEntity.setOrderId( orderId );
			jdOrderInfoEntity.setSku( goodsInfo );
			jdOrderInfoEntity.setTransStatusStatus( ConstantsForOrder.JD_TRANS_STATUS_INITIAL );
			jdOrderInfoEntity.setUserId( userId );
			
			orderInfoDao.insertJDOrderInfo( jdOrderInfoEntity );
			
			/*
			 * post jd service for booking
			 */
			String resOrderId = jdRequestService.priceSubmit( userReceiveInfo, goodsInfo, jdTradeOrderId );
			
			Map<String, Object> params = new HashMap<String, Object>();
			Boolean successFlag = false;
			
			if ( resOrderId == null || resOrderId.equals( "" ) ) {
				
				// booking fail, update jd_order_info trans_status
				params.put( "transStatus", ConstantsForOrder.JD_TRANS_STATUS_BOOK_FAIL );
				params.put( "jdTradeOrderId", jdTradeOrderId );
				params.put( "jdOrderId", "" );
				
			} else {
				
				// booking success and to comfirm order, update jd_order_info trans_status
				params.put( "transStatus", ConstantsForOrder.JD_TRANS_STATUS_BOOK_SUCCESS );
				params.put( "jdTradeOrderId", jdTradeOrderId );
				params.put( "jdOrderId", resOrderId );
				successFlag = true;
				
			}
			
			orderInfoDao.updateJDOrderInfo( params );
			
			if ( successFlag ) {
				
				// comfirm order
				String res = jdRequestService.confirmOrder( resOrderId );
				
				if ( res == null || res.equals( "" ) ) {
					// comfirm fail
					params.put( "transStatus", ConstantsForOrder.JD_TRANS_STATUS_COMFIRM_FAIL );
					
				} else {
					// comfirm success
					params.put( "transStatus", ConstantsForOrder.JD_TRANS_STATUS_COMFIRM_SUCCESS );
				}
				params.put( "jdTradeOrderId", jdTradeOrderId );
				params.put( "jdOrderId", resOrderId );
				
				orderInfoDao.updateJDOrderInfo( params );
				
			}
			
		}
			
	}
	
	private String buildJdOrderGoods( List<ProductGoodsDTO> productGoodsList, Integer batchId ) {
		
		JSONArray resArr = new JSONArray();
		
		Integer baseCount = 50;
		Integer startIndex = ( batchId - 1 ) * baseCount + 1;
		Integer endIndex = baseCount * batchId;
		
		for ( int i = startIndex; i <= endIndex; i++ ) {
			
			if ( i > productGoodsList.size() ) {
				break;
			}
			JSONObject resJson = new JSONObject();
			ProductGoodsDTO dto = productGoodsList.get( i - 1 );
			resJson.put( "skuId", dto.getGoodsId() );
			resJson.put( "num", dto.getCount() );
			
			resArr.add( resJson );
			
		}
		
		return resArr.toString();
		
	}

	private List<ProductGoodsDTO> buildBatchAddGoods( String productId ) {

		List<ProductGoodsDTO> dto = productInfoDao
				.getProductGoodsDetailList( productId );

		return productInfoDao.getProductGoodsDetailList( productId );

	}

}
