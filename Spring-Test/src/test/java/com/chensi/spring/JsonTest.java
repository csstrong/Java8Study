package com.chensi.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chensi.spring.json.fastjson.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author  chensi
 * @date  2023/3/7
 */
@SpringBootTest
public class JsonTest {
	@Test
	void contextLoads() {

	}

	@Test
	public void test1() {
		Person person = new Person();
		person.setId(1L);
		//person.setName("周星驰");
		person.setPwd("123");
		person.setAddr("河南");
		person.setWebstieUrl("http://www.baidu.com");
		person.setRegisterDate(new Date());
		person.setBirthDay(LocalDateTime.now());
		//调用fastjson的工具类JSON的toJSONString方法就能将任意一个对象序列化成json字符串；
		String jsonStr = JSON.toJSONString(person);
		System.out.println(jsonStr);
		//序列化null值
		jsonStr = JSON.toJSONString(person, SerializerFeature.WriteMapNullValue);
		System.out.println(jsonStr);

		Person person2 = JSON.parseObject(jsonStr, Person.class);
		person2.setName("胡歌");
		System.out.println(person2);
	}

	@Test
	public void test2() {
		Person person = new Person();
		person.setId(1L);
		person.setName(null);
		person.setPwd("123");
		List<Person> list = new ArrayList<>();
		list.add(person);
		list.add(person);
		list.add(person);
		String jsonStr = JSON.toJSONString(list);
		System.out.println(jsonStr);

		jsonStr = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.WriteMapNullValue);
		System.out.println(jsonStr);
	}

	@Test
	public void test3() {
		String jsonStr = "{\"addr\":\"河南\",\"birthDay\":\"2022/08/31 00:00:00\",\"id\":1,\"name\":null,\"pwd\":\"123\",\"registerDate\":\"2022/08/31 00:00:00\",\"webstieUrl\":\"http://www.baidu.com\"}";
		Person person = JSON.parseObject(jsonStr, Person.class);
		System.out.println(person);

	}

}
