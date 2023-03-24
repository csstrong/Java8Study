package com.chensi.spring.json.annotation;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
 * @author  chensi
 * @date  2023/3/23
 */
public class DecimalFormatJsonDeserializer extends JsonDeserializer<Double> {

	@Override
	public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		//获取反序列化的对象模型
		Class<?> clazz = p.getCurrentValue().getClass();
		BigDecimal decimalValue = p.getDecimalValue();
		Field declaredField;
		try {
			//通过字段名称获取对应字段
			//考虑继承情况
			declaredField = clazz.getDeclaredField(p.getCurrentName());
		} catch (NoSuchFieldException e) {
			return decimalValue.doubleValue();
		}
		//获取注解
		DecimalFormatTest annotation = declaredField.getAnnotation(DecimalFormatTest.class);
		if (annotation == null) {
			return decimalValue.doubleValue();
		}
		int scale = annotation.scale();
		if (decimalValue != null) {
			return decimalValue.setScale(scale, RoundingMode.HALF_UP).doubleValue();
		}
		return null;
	}
}
