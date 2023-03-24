package com.chensi.spring.validation.po;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.StringJoiner;

@Data
public class Site {

	@JsonAlias("pid")
	@JsonProperty("Id")
	private String id;

	@JsonAlias("pname")
	@JsonProperty("Name")
	private String name;

	@Size(message = "地址长度范围为{min}到{max}之间", min = 1, max = 5)
	@JsonAlias("paddress")
	@JsonProperty("Address")
	private String address;

	@JsonProperty("CreateTime")
	private String createTime;

	@JsonAlias("parea")
	@JsonProperty("area")
	private List area;

	@Override
	public String toString() {
		return new StringJoiner(", ", Site.class.getSimpleName() + "[", "]")
			.add("id='" + id + "'")
			.add("name='" + name + "'")
			.add("address='" + address + "'")
			.add("createTime='" + createTime + "'")
			.add("area=" + area)
			.toString();
	}
}

