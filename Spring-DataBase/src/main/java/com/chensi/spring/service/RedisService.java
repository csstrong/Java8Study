package com.chensi.spring.service;

/**
 * @author  chensi
 * @date  2023/1/29
 */
public interface RedisService {
	String getCache(String key);
	String getRedisConnStatus();
}
