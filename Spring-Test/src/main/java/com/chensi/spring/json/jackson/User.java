package com.chensi.spring.json.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringJoiner;

/*
 * @author  chensi
 * @date  2023/3/7
 */

public class User {
	private Long id;
	private String name;
	@JsonIgnore
	private String pwd;
	private String addr;
	private String webstieUrl; //个人网站连接
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
	private Date registerDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
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
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
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

