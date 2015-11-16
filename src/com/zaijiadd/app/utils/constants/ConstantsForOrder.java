package com.zaijiadd.app.utils.constants;

public class ConstantsForOrder {
	
	// 订单初始状态
	public static final Integer TRANS_STATUS_INITIAL = 1;
	// 订单处理成功状态
	public static final Integer TRANS_STATUS_SUCCESS = 2;
	// 订单处理失败状态
	public static final Integer TRANS_STATUS_FAIL = 3;
	// 订单处理中状态
	public static final Integer TRANS_STATUS_HANDLE = 4;
	
	// 交易码：充值交易
	public static final String TRANS_CODE_RECHARGE = "100010";
	
	// 订单初始状态
	public static final Integer JD_TRANS_STATUS_INITIAL = 1;
	// 下单成功
	public static final Integer JD_TRANS_STATUS_BOOK_SUCCESS = 2;
	// 下单失败
	public static final Integer JD_TRANS_STATUS_BOOK_FAIL = 3;
	// 确认订单成功
	public static final Integer JD_TRANS_STATUS_COMFIRM_SUCCESS = 4;
	// 确认订单失败
	public static final Integer JD_TRANS_STATUS_COMFIRM_FAIL = 5;
	// 完成收货
	public static final Integer JD_TRANS_STATUS_FINISH = 6;

}
