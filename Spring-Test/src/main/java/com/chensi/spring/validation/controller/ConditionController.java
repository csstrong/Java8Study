package com.chensi.spring.validation.controller;

import com.chensi.spring.validation.base.QueryCondition;
import com.chensi.spring.validation.config.ConditionParse;
import com.chensi.spring.validation.po.Site;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * @author  chensi
 * @date  2023/3/23
 */
@RestController
@RequestMapping("/condition")
public class ConditionController {

	@RequestMapping(path = "/query", method = RequestMethod.POST)
	@ResponseBody
	public void test(String condition) {
		System.out.println(condition);
		return;
	}

	@RequestMapping(path = "/query2", method = RequestMethod.POST)
	@ResponseBody
	public void test2(@ConditionParse(Site.class) @Validated List<QueryCondition> condition) {
		System.out.println(condition);
		return;
	}
}


