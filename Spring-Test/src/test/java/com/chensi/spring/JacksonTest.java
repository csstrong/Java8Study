package com.chensi.spring;

import com.chensi.spring.json.jackson.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/*
 * @author  chensi
 * @date  2023/3/7
 */
public class JacksonTest {
	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		//配置序列化时，不序列化null值
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//配置自动通过spi发现jackson的moudle并注册
		objectMapper.findAndRegisterModules();
		//配置序列化的美化输出，true表示开启美化输出
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//表示：在反序列化时，针对哪些目标对象中没有的属性jackson会直接忽略掉，就能反序列化成功
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

		//将驼峰转下划线
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
	}

	@Test
	public void test1() throws JsonProcessingException {
		User user = new User();
		user.setId(1L);
		user.setName(null);
		user.setPwd("123");
		user.setAddr("河南");
		user.setWebstieUrl("http://www.baidu.com");
		user.setRegisterDate(new Date());
		user.setBirthDay(LocalDateTime.now());
		String jsonStr = objectMapper.writeValueAsString(user);
		System.out.println(jsonStr);
	}

	@Test
	public void test6() throws JsonProcessingException {
		String jsonStr = "{\"id\":1,\"pwd\":\"123\",\"age\":\"18\",\"addr\":\"河南\",\"webstieUrl\":\"http://www.baidu.com\",\"registerDate\":\"2022-08-31 23:19:00\",\"birthDay\":\"2022-08-31 23:19:00\"}";
		User user = objectMapper.readValue(jsonStr, User.class);
		System.out.println(user);
	}

}

