package com.chensi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @author  chensi
 * @date  2023/1/31
 */
@EnableDubbo
@SpringBootApplication
public class BootUserServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProviderApplication.class, args);
	}
}
