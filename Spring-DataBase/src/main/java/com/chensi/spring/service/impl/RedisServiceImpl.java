package com.chensi.spring.service.impl;

import com.chensi.spring.service.RedisService;
import io.lettuce.core.protocol.ConnectionWatchdog;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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

	@Resource
	private RedissonClient redissonClient;

	@Override
	@Cacheable(value = "MyCache", key = "#key")
	public String getCache(String key) {
		Object message = redisTemplate.opsForValue().get(key);
		return message.toString();
	}

	/**
	 * 判断redis的连接状态
	 */
	public String getRedisConnStatus() {
		RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
		RedisConnection connection = connectionFactory.getConnection();
		boolean status = connection.isClosed();
		boolean shutdown = redissonClient.isShutdown() || redissonClient.isShuttingDown();
		return status + "-" + shutdown + "-" ;
	}
}
