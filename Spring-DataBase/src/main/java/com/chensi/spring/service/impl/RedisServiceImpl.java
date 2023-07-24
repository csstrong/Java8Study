package com.chensi.spring.service.impl;

import com.chensi.spring.service.RedisService;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author  chensi
 * @date  2023/1/29
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate redisTemplate;

	//@Resource
	//private RedissonClient redissonClient;

	@Cacheable(value = "MyCache", key = "#key")
	public String getCache(String key) {
		Object message = redisTemplate.opsForValue().get(key);
		return message.toString();
	}

	/**
	 * 判断redis的连接状态
	 */
	public String getRedisConnStatus() {
		//method1();
		//method2();
		//method3();
		boolean b = method5();
		return String.valueOf(b);
	}

	public void method1() {
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		try {
			String ping = connection.ping();
			System.out.println(ping);
			// 连接正常
		} catch (Exception e) {
			// 连接已断开
			System.out.println("method1 exception...");
		}
	}

	public void method2() {
		try {
			redisTemplate.opsForValue().set("hello", "world");
			Object hello = redisTemplate.opsForValue().get("hello");
			System.out.println(hello);
			// 连接正常
		} catch (RedisConnectionFailureException e) {
			// 连接已断开
			System.out.println("method2 exception...");
		}
	}

	public void method3() {
		LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
		boolean validateConnection = factory.getValidateConnection();
		RedisConnection connection = factory.getConnection();
		boolean closed = connection.isClosed();
		boolean pipelined = connection.isPipelined();
		boolean queueing = connection.isQueueing();
		System.out.println(validateConnection);
		//factory.addConnectionListener(new RedisConnectionFailureListener() {
		//	@Override
		//	public void onRedisConnectionFailure(RedisConnection connection, Exception exception) {
		//		// 连接已断开
		//		System.out.println("method3 exception...");
		//	}
		//});
	}

	public void method4() {
		try {
			//boolean shutdown = redissonClient.isShutdown() || redissonClient.isShuttingDown();
		} catch (Exception e) {

		}
	}

	public boolean method5() {
		boolean status = false;
		//创建RedisURI对象，指定服务器的基本配置
		RedisURI redisURI = RedisURI.builder()
			.withHost("110.40.151.182")
			.withPort(83)
			.build();

		//创建RedisClient对象并设置ClientOptions
		RedisClient redisClient = RedisClient.create(redisURI);
		ClientOptions options = ClientOptions.builder()
			//开启ping命令检测连接可用性
			.pingBeforeActivateConnection(true)
			.build();
		redisClient.setOptions(options);

		//尝试连接redis服务器
		try {
			redisClient.connect().async();
			System.out.println("连接成功");
			status = true;
		} catch (Exception e) {
			System.out.println("连接失败：" + e.getMessage());
			return false;
		} finally {
			redisClient.shutdown();
		}
		return status;
	}

}
