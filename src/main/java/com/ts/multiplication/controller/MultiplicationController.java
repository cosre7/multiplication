package com.ts.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.multiplication.service.MultiplicationService;

@Controller
public class MultiplicationController {

	@Autowired
	MultiplicationService multiplicationService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "multiplication";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getProduct.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	public String getProduct(@RequestBody String inputValue) {
		String gsonString = multiplicationService.generateProductResult(inputValue);
		return gsonString;
	}
	
}
