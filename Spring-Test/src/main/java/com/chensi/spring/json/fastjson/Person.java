package com.chensi.spring.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringJoiner;

/*
 * @author  chensi
 * @date  2023/3/7
 */

public class Person {
	private Long id;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "password")
	private String pwd;
	@JSONField(name = "Address")
	private String addr;
	@JSONField(serialize = false, deserialize = false)
	private String webstieUrl; //个人网站连接
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date registerDate;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime birthDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getWebstieUrl() {
		return webstieUrl;
	}

	public void setWebstieUrl(String webstieUrl) {
		this.webstieUrl = webstieUrl;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public LocalDateTime getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
			.add("id=" + id)
			.add("name='" + name + "'")
			.add("pwd='" + pwd + "'")
			.add("addr='" + addr + "'")
			.add("webstieUrl='" + webstieUrl + "'")
			.add("registerDate=" + registerDate)
			.add("birthDay=" + birthDay)
			.toString();
	}
}
