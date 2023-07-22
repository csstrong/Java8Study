package com.chensi.dubbo.service;

import com.chensi.dubbo.bean.UserAddress;

import java.util.List;

/*
 * @author  chensi
 * @date  2023/1/31
 */
public interface OrderService {
	/**
	 * 初始化订单
	 */
	void initOrder(String userId);

	List<UserAddress> initOrder2(String userId);
}
