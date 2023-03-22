package com.chensi.service.impl;

import com.chensi.bean.UserAddress;
import com.chensi.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * @author  chensi
 * @date  2023/1/31
 */

/**
 * 1.将服务提供者注册到注册中心
 * 1) 引入dubbo依赖、zookeeper客户端依赖
 * 2) 配置服务提供者
 * 2.让服务消费者从注册中心订阅服务提供者的相关服务
 * (interfaceClass = UserService.class, version = "1.0.0")
 */
@Service
@DubboService(interfaceClass = UserService.class, version = "1.0.0")
public class UserServiceImpl implements UserService {
	@Value("${dubbo.application.name}")
	private String applicationName;

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress userAddress1 = new UserAddress(1, "浙江省杭州市", "1", "张三", "123456", "Y");
		UserAddress userAddress2 = new UserAddress(2, "湖北省武汉市", "1", "李四", "999999", "N");
		try {
			TimeUnit.MILLISECONDS.sleep(2000);//test timeout
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		return Arrays.asList(userAddress1, userAddress2);
	}
}
