package com.ts.multiplication.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ts.multiplication.service.MultiplicationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml")
public class MultiplicationControllerTest {
	
	@Autowired
	MultiplicationService multiplicationService;
	
	@Test
	public void testGetProduct() {
		String jString = 
				"{"
					    +   "\"inputValue\": ["
					    +       "{"
					    +           "\"row\": \"a\","
					    +           "\"firstFactor\": 2,"
					    +           "\"secondFactor\": 3"
					    +       "},"
					    +       "{"
					    +           "\"row\": \"b\","
					    +           "\"firstFactor\": 4,"
					    +           "\"secondFactor\": 5"
					    +       "},"
					    +       "{"
					    +           "\"row\": \"d\","
					    +           "\"firstFactor\": 6,"
					    +           "\"secondFactor\": 8"
					    +       "}"
					    +   "]"
					    +"}";	
		
		List<Object> list = multiplicationService.createJsonProductList(jString);
		System.out.println(list);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		System.out.println(gsonString);
	}
}
