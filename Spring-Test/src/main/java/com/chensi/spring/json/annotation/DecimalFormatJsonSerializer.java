package com.chensi.spring.json.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import sun.reflect.misc.FieldUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/*
 * @author  chensi
 * @date  2023/3/23
 */
public class DecimalFormatJsonSerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		JsonStreamContext outputContext = jsonGenerator.getOutputContext();
		//获取当前class对象
		Class<?> clazz = outputContext.getCurrentValue().getClass();
		//获取当前属性名
		String fieldName = outputContext.getCurrentName();
		//获取当前属性的field
		Field[] declaredFields = FieldUtil.getDeclaredFields(clazz);
		Field field = declaredFields[0];
		//获取当前属性的注解
		DecimalSerializerFormat annotation = field.getAnnotation(DecimalSerializerFormat.class);
		if (annotation == null) {
			return;
		}
		String pattern = annotation.pattern();
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		//jsonGenerator.writeString(decimalFormat.format(bigDecimal));
		jsonGenerator.writeString("haha...");
	}
}
