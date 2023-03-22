package com.chensi.spring.validation.po;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/*
 * @author  chensi
 * @date  2023/3/8
 */

/**
 * @RequestBody一般对 content-type:application/json 类型的参数进行校验，
 * 需要定义一个实体类，字段和前端参数需要一一对应。
 */

public class TmParamEntity {
	@NotNull(message = "id字段不能为空")
	private String id;

	@NotNull(message = "name字段不能为空")
	private String name;

	@NotNull(message = "age字段不能为空")
	@Range(message = "年龄范围为{min}到{max}之间", min = 1, max = 100)
	private Integer age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", TmParamEntity.class.getSimpleName() + "[", "]")
			.add("id='" + id + "'")
			.add("name='" + name + "'")
			.add("age=" + age)
			.toString();
	}
}
