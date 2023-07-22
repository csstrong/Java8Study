package com.chensi.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author  chensi
 * @date  2022/9/26
 */

@RestController
@RequestMapping("/mongo")
public class MongoController {

	//@Resource
	//private MongoService mongoService;

	@GetMapping("/getStatus")
	public int getMongoStatus() {
		//return mongoService.getMongoConnStatus();
		return -1;
	}

}
