package com.chensi.spring.validation.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * @author  chensi
 * @date  2023/3/8
 */
//全局异常捕获
//MethodArgumentNotValidException的异常优先级比BindException高
@RestControllerAdvice
public class GlobalExceptonHandler {
	//@ExceptionHandler(value = MethodArgumentNotValidException.class)
	//public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
	//	//return ResponseWrapper.fail.setData(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	//	return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
	//}

	//@ExceptionHandler(value = HttpMessageNotReadableException.class)
	//public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
	//	return e.getMessage();
	//}

	@ExceptionHandler(value = BindException.class)
	public List<String> validExceptionHandler(BindException e) {
		List<String> collect = e.getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList());
		collect.stream().forEach(System.out::println);
		return collect;
		//return e.getAllErrors().get(0).getDefaultMessage();
	}
}
