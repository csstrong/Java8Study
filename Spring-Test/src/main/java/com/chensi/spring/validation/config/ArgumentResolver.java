package com.chensi.spring.validation.config;

import com.alibaba.fastjson.JSONObject;
import com.chensi.spring.validation.base.FieldType;
import com.chensi.spring.validation.base.QueryCondition;
import com.chensi.spring.validation.base.Relational;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.ValidationAnnotationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @author  chensi
 * @date  2023/3/23
 */

@Component
public class ArgumentResolver implements HandlerMethodArgumentResolver {


	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		//配置序列化时，不序列化null值
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 所有方法中的参数都需要调用此方法判断此解析器是否支持此参数
	 *
	 * @param parameter 请求参数
	 * @return 支持返回true, 不支持返回false
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		//String parameterName = parameter.getParameterName();
		//if ("condition".equals(parameterName)) {
		//	return true;
		//}
		if (parameter.hasParameterAnnotation(ConditionParse.class)) {
			return true;
		}
		return false;
	}

	/**
	 * 在supportParameter()方法判断后，方法中的参数解析器是否支持后，
	 * 所有请求此路径的方法，调用此方法对请求参数进行处理。
	 * <p>
	 * 将方法参数解析为来自给定请求的参数值。
	 * ModelAndViewContainer提供对*请求的模型的访问。
	 * WebDataBinderFactory提供了一种在需要进行数据绑定和*类型转换时创建WebDataBinder实例的方法
	 *
	 * @param parameter     请求参数（这里的请求参数都是已经调用过上面的supportParameter判断过解析器是否支持）
	 * @param mavContainer  当前请求的ModelAndViewContainer
	 * @param webRequest    当前的请求
	 * @param binderFactory 用于创建 WebDataBinder实例的工厂
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<QueryCondition> resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
	                                            WebDataBinderFactory binderFactory) throws Exception {
		//获取请求和响应对象
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeRequest(HttpServletResponse.class);

		//从请求参数中获取值
		String condition = request.getParameter("condition");

		//判断是否为空
		if (!StringUtils.hasText(condition)) {
			return null;
		}
		//解析参数
		List<QueryCondition> queryConditions = new ArrayList<>();
		try {
			queryConditions = parseCondition(condition, parameter);
		} catch (MethodArgumentNotValidException e) {
			throw e;
		}

		//组织json
		JSONObject queryJson = new JSONObject();
		if (!queryConditions.isEmpty()) {
			for (QueryCondition queryCondition : queryConditions) {
				String key = queryCondition.getFieldName();
				Object value = queryCondition.getValue();
				queryJson.put(key, value);
			}
		}

		if (binderFactory != null) {
			//json 转实体模型 并校验
			String queryStr = queryJson.toString();
			Class clazz = getValidationClass(parameter);
			Object obj = null;
			String name = null;
			WebDataBinder binder = null;
			if (clazz != null) {
				obj = objectMapper.readValue(queryStr, clazz);
				//判断是否反序列化成功
				boolean b = deserializerIfSuccess(obj, queryJson);
				if (b) {
					Class<?> aClass = obj.getClass();
					name = aClass.getSimpleName();
					binder = binderFactory.createBinder(webRequest, obj, name);
				} else {
					throw new MethodArgumentNotValidException(parameter, getBindingResult());
				}
			}
			if (binder != null) {
				validateIfApplicable(binder, parameter);
			}
			if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
				throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
			}
			if (mavContainer != null) {
				mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
			}
		}
		return queryConditions;
	}

	protected boolean deserializerIfSuccess(Object obj, JSONObject queryJson) throws IllegalAccessException {
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		List<String> keyList = new ArrayList<>(queryJson.keySet());

		List<Boolean> statusL = new ArrayList<>();
		for (String s : keyList) {
			boolean status = false;
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				JsonAlias jsonAlias = f.getAnnotation(JsonAlias.class);
				if (jsonAlias != null) {
					String[] value = jsonAlias.value();
					if (value.length > 0) {
						String aliasName = value[0];
						if (s.equals(aliasName)) {
							Object o = f.get(obj);
							if (o != null) {
								status = true;
								break;
							}
						}
					}
				}
			}
			statusL.add(status);
		}
		return statusL.stream().allMatch(a -> a.equals(true));
	}

	protected Class getValidationClass(MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation ann : annotations) {
			Class<? extends Annotation> annotationType = ann.annotationType();
			String annotationName = annotationType.getSimpleName();
			if (!annotationName.equals("ConditionParse")) {
				continue;
			}
			ConditionParse conditionParse = AnnotationUtils.getAnnotation(ann, ConditionParse.class);
			if (conditionParse != null) {
				Class<?> value = conditionParse.value();
				return value;
			}
		}
		return null;
	}

	protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation ann : annotations) {
			Object[] validationHints = ValidationAnnotationUtils.determineValidationHints(ann);
			if (validationHints != null) {
				binder.validate(validationHints);
				break;
			}
		}
	}

	protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
		int i = parameter.getParameterIndex();
		Class<?>[] paramTypes = parameter.getExecutable().getParameterTypes();
		boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
		return !hasBindingResult;
	}

	private List<QueryCondition> parseCondition(String condition, MethodParameter parameter) throws MethodArgumentNotValidException {
		List<QueryCondition> fieldList = new ArrayList<>();
		if (condition == null || condition.isEmpty()) {
			return fieldList;
		}
		List<String> likeFieldList = new ArrayList<>();
		List<String> inFieldList = new ArrayList<>();

		try {
			JSONObject object = JSONObject.parseObject(condition);

			if (object.containsKey("Like")) {
				List like = object.getObject("Like", List.class);
				likeFieldList.addAll(like);
				object.remove("Like");
			}
			if (object.containsKey("In")) {
				List in = object.getObject("In", List.class);
				inFieldList.addAll(in);
				object.remove("In");
			}

			for (Map.Entry<String, Object> entry : object.entrySet()) {
				QueryCondition field = new QueryCondition();
				String name = entry.getKey();
				Object value = entry.getValue();
				field.setFieldName(name);
				field.setValue(value);
				field.setType(FieldType.String);

				if (likeFieldList.contains(name)) {
					field.setRelationalOptor(Relational.Like);
				}
				if (inFieldList.contains(name)) {
					field.setRelationalOptor(Relational.In);
				}
				fieldList.add(field);
			}
		} catch (Exception e) {
			throw new MethodArgumentNotValidException(parameter, getBindingResult());
		}
		return fieldList;
	}

	private BeanPropertyBindingResult getBindingResult() {
		BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult(new Object(), "");
		beanPropertyBindingResult.addError(new ObjectError("condition", "condition参数格式或参数名称错误！"));
		return beanPropertyBindingResult;
	}
}
