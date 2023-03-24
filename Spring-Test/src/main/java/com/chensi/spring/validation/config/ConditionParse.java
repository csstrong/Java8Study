package com.chensi.spring.validation.config;

import java.lang.annotation.*;

/*
 * @author  chensi
 * @date  2023/3/23
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionParse {
	Class<? extends Object> value();
}
