package com.chensi.spring.cache.controller;

import com.chensi.spring.cache.impl.CacheServiceImpl;
import com.chensi.spring.cache.service.CacheService;
import org.springframework.web.bind.annotation.*;

/*
 * @author  chensi
 * @date  2023/1/29
 */
@RestController
@RequestMapping("/cache")
public class SpringCacheController {

	@ResponseBody
	@GetMapping("/getCache/{key}")
	public String getCache(@PathVariable(value = "key") String key) {
		CacheService cacheService = new CacheServiceImpl();
		return cacheService.getCache(key);
	}
}
