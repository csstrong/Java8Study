package com.chensi.spring.validation.po;

import java.io.Serializable;
import java.util.StringJoiner;

/*
 * @author  chensi
 * @date  2023/3/8
 */
public class User implements Serializable {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
			.add("id=" + id)
			.add("name='" + name + "'")
			.toString();
	}
}