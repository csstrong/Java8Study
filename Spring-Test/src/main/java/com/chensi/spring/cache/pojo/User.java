package com.chensi.spring.cache.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



/*
 * @author  chensi
 * @date  2023/1/9
 */
//@Entity
@Data
@NoArgsConstructor
public class User {

	/**
	 * @GeneratedValue注解有两个属性，分别是strategy和generator -AUTO主键由程序控制, 是默认选项 ,不设置就是这个
	 * -IDENTITY 主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
	 * -SEQUENCE 通过数据库的序列产生主键, MYSQL  不支持
	 * -Table 提供特定的数据库产生主键, 该方式更有利于数据库的移植
	 */
	@Id
	//@GeneratedValue
	private Long id;

	private String name;

	private Integer age;

	public User(String name, Integer age) {
		this.name = name;
	}
}
