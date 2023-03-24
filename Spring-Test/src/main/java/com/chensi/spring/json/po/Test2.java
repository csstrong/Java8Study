package com.chensi.spring.json.po;

import com.chensi.spring.json.annotation.DecimalSerializerFormat;

import java.math.BigDecimal;

/*
 * @author  chensi
 * @date  2023/3/23
 */
public class Test2 {

	@DecimalSerializerFormat
	private BigDecimal bigDecimal;

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

}
