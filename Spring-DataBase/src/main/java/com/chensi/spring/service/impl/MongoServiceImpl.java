package com.chensi.spring.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chensi
 * @date 2023/7/10
 */
@Service
public class MongoServiceImpl {

	//@Resource
	private MongoTemplate mongoTemplate;

	@Resource
	private MongoClient mongoClient;

	/**
	 * 判断mongo连接状态
	 */
	public int getMongoConnStatus() {
		int i = -1;
		try {
			MongoDatabase db = mongoTemplate.getDb();
			Document serverStatus = db.runCommand(new Document("serverStatus", 1));
			i = serverStatus.get("connections", Document.class).getInteger("current");
		} catch (Exception e) {
			//throw new MongoTimeoutException("mongo 连接超时。");
			e.printStackTrace();
			return i;
		}
		return i;
	}
}
