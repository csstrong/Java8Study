package com.chensi.spring.validation.controller;

import com.chensi.spring.validation.po.TmParamEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @author  chensi
 * @date  2023/3/8
 */
@RestController
@RequestMapping("/tele")
public class TelemetryController {

	@PostMapping("/postData")
	@ResponseBody
	public String postTmData(@RequestBody @Validated TmParamEntity tmParamEntity) {
		System.out.println(tmParamEntity.getId());
		System.out.println(tmParamEntity.getName());
		return "ok";
	}

	@GetMapping("/postData2")
	@ResponseBody
	public List<String> postTmData2(@RequestBody @Valid TmParamEntity tmParamEntity, BindingResult bindingResult) {
		//所有字段是否验证通过，true-数据有误，flase-数据无误
		if (bindingResult.hasErrors()) {
			List<String> collect = bindingResult.getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList());
			collect.stream().forEach(System.out::println);
			return collect;
		}
		System.out.println(tmParamEntity.getId());
		System.out.println(tmParamEntity.getName());
		return Arrays.asList("ok");
	}

	@PostMapping("/postListData")
	public void postTmData2(@RequestBody @Valid List<TmParamEntity> tmParamEntity, HttpServletRequest httpServletRequest) {
		tmParamEntity.stream().forEach(System.out::println);
		return;
	}
}
