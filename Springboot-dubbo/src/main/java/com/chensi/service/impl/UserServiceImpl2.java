package com.chensi.service.impl;

/*
 * @author  chensi
 * @date  2023/1/31
 */

import com.chensi.bean.UserAddress;
import com.chensi.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 1.将服务提供者注册到注册中心
 * 1) 引入dubbo依赖、zookeeper客户端依赖
 * 2) 配置服务提供者
 * 2.让服务消费者从注册中心订阅服务提供者的相关服务
 */
@Service
@DubboService(interfaceClass = UserService.class, version = "2.0.0")
public class UserServiceImpl2 implements UserService {

	//The default value of ${dubbo.application.name} is ${spring.application.name}
	@Value("${dubbo.application.name}")
	private String applicationName;

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress userAddress1 = new UserAddress(1, "浙江省杭州市", "1", "张三", "123456", "Y");
		UserAddress userAddress2 = new UserAddress(2, "湖北省武汉市", "1", "李四", "999999", "N");
		try {
			TimeUnit.MILLISECONDS.sleep(2000); //测试timeout
//            TimeUnit.MILLISECONDS.sleep(4000); //测试重试次数
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(applicationName + " new....");
		return Arrays.asList(userAddress1, userAddress2);
	}
}

