package com.chensi.spring.validation.controller;

import com.chensi.spring.validation.po.User;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/*
 * @author  chensi
 * @date  2023/3/8
 */
@Validated
@RestController
@RequestMapping("/load")
public class PayloadController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/validation")
	public void validation(@NotNull(message = "订单ID不能为空") @RequestParam("id") Long id,
	                       @NotNull(message = "退回原因不能为空")
	                       @Length(max = 10, message = "退回原因最多允许输入200个字符")
	                       @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\s\\u00a0\\u3000`~!@#$%^&*()+=_|{}':;',\\\\\\[\\].<>/?~！@#￥%……&*（）——+|｜{}【】‘；：”“’\"。，、？《》「」¥～-]*$", message = "退回原因不允许输入特殊字符")
	                       @RequestParam("reason") String reason) {
		System.out.println("id:" + id);
		System.out.println("reason:" + reason);
		return;
	}

	@PostMapping("/manage")
	public void postEarthStation(
		@RequestParam("id") @Min(0) @Max(10) int id
	) {
		System.out.println("id:" + id);
	}

	@GetMapping("/test")
	public String test(@RequestParam("name") String name, @RequestParam("id") int id, HttpServletRequest httpServletRequest) {
		logger.info("请求url {}", httpServletRequest.getRequestURI());
		logger.info("查询字符串参数 {}", httpServletRequest.getQueryString());
		logger.info("参数编码方式 {}", httpServletRequest.getContentType());
		logger.info("请求方式 {}", httpServletRequest.getMethod());
		logger.info("name {}", name);
		logger.info("id {}", id);
		return "ok";
	}

	@GetMapping("signGet")
	public String signin(@RequestBody User user, HttpServletRequest httpServletRequest) {
		logger.info("请求url {}", httpServletRequest.getRequestURI());
		logger.info("查询字符串参数 {}", httpServletRequest.getQueryString());
		logger.info("参数编码方式 {}", httpServletRequest.getContentType());
		logger.info("请求方式 {}", httpServletRequest.getMethod());
		logger.info("name {}", user.getName());
		logger.info("id {}", user.getId());
		return "ok";
	}

	@PostMapping("signPost")
	public String signout(@RequestBody User user, HttpServletRequest httpServletRequest) {
		logger.info("请求url {}", httpServletRequest.getRequestURI());
		logger.info("查询字符串参数 {}", httpServletRequest.getQueryString());
		logger.info("参数编码方式 {}", httpServletRequest.getContentType());
		logger.info("请求方式 {}", httpServletRequest.getMethod());
		logger.info("name {}", user.getName());
		logger.info("id {}", user.getId());
		return "ok";
	}
}
