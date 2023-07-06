package com.chensi.spring.impl;

//import com.chensi.spring.cache.service.CacheService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;

/*
 * @author  chensi
 * @date  2023/1/29
 */
//@Service
//public class CacheServiceImpl implements CacheService {
//
//	private static RedisTemplate redisTemplate;
//
//	@Autowired
//	public void setRedisTemplate(RedisTemplate redisTemplate) {
//		CacheServiceImpl.redisTemplate = redisTemplate;
//	}
//
//	@Override
//	@Cacheable(value="MyCache",key="#key")
//	public String getCache(String key) {
//		Object message = redisTemplate.opsForValue().get(key);
//		return message.toString();
//	}
//}
