package com.chensi.spring.validation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
 * @author  chensi
 * @date  2023/3/23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ArgumentResolver argumentResolver;

	/**
	 * 将ArgumentResolver将入到处理器队列中来
	 *
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(argumentResolver);
	}
}
