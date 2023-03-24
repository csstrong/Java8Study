package com.chensi.spring.validation.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * @author  chensi
 * @date  2023/3/23
 */
public enum Relational {
	@JsonProperty("Equals")
	Equals,
	@JsonProperty("Greater")
	Greater,
	@JsonProperty("Smaller")
	Smaller,
	@JsonProperty("NoEquals")
	NoEquals,
	@JsonProperty("Between")
	Between,
	@JsonProperty("Like")
	Like,
	@JsonProperty("GreaterEquals")
	GreaterEquals,
	@JsonProperty("SmallerEquals")
	SmallerEquals,
	@JsonProperty("In")
	In,
	@JsonProperty("Nin")
	Nin;
}
