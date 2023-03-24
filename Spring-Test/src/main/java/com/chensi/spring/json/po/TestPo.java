package com.chensi.spring.json.po;

import com.chensi.spring.json.annotation.DecimalFormatTest;
import lombok.Data;

import java.util.StringJoiner;

/*
 * @author  chensi
 * @date  2023/3/23
 */
@Data
public class TestPo {
	@DecimalFormatTest(scale = 1)
	private Double amount;
	private String name;

	@Override
	public String toString() {
		return new StringJoiner(", ", TestPo.class.getSimpleName() + "[", "]")
			.add("amount=" + amount)
			.add("anme='" + name + "'")
			.toString();
	}
}
