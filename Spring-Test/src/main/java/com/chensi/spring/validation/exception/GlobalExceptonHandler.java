package com.chensi.spring.validation.exception;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @author  chensi
 * @date  2023/3/8
 */
//全局异常捕获
//MethodArgumentNotValidException的异常优先级比BindException高
@RestControllerAdvice
public class GlobalExceptonHandler {

	@ExceptionHandler(value = ConstraintViolationException.class)
	public String handleConstraintViolationException(ConstraintViolationException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		BindingResult bindingResult = e.getBindingResult();

		List<String> message = bindingResult.getAllErrors().stream()
			.map(m -> m.getDefaultMessage()).collect(Collectors.toList());
		return message;
	}

	//@ExceptionHandler(value = HttpMessageNotReadableException.class)
	//public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
	//	return e.getMessage();
	//}

	//@ExceptionHandler(value = BindException.class)
	//public List<String> validExceptionHandler(BindException e) {
	//	List<String> collect = e.getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList());
	//	collect.stream().forEach(System.out::println);
	//	return collect;
	//}
}
