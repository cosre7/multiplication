package com.ts.multiplication.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ts.multiplication.service.MultiplicationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml")
public class MultiplicationControllerTest {
	
	@Autowired
	MultiplicationService multiplicationService;
	
	@Test
	public void testGetProduct() {
		String jString = 
				"["
					    +       "{"
					    +           "\"firstFactor\": 1,"
					    +           "\"secondFactor\": 2,"
					    +			"\"product\": \"product1\""
					    +       "},"
					    +       "{"
					    +           "\"firstFactor\": 3,"
					    +           "\"secondFactor\": 4,"
					    +			"\"product\": \"product3\""
					    +       "},"
					    +       "{"
					    +           "\"firstFactor\": 21474836479449,"
					    +           "\"secondFactor\": 33333333333¤·33333333333,"
					    +			"\"product\": \"product4\""	
					    +       "},"
					    +       "{"
					    +           "\"firstFactor\": 214,"
					    +           "\"secondFactor\": 21499,"
					    +			"\"product\": \"product7\""	
					    +       "},"
					    +       "{"
					    +           "\"firstFactor\": 219,"
					    +           "\"secondFactor\": 2,"
					    +			"\"product\": \"product9\""	
					    +       "}"
					    +   "]";
		
		String gsonString = multiplicationService.generateProductResult(jString);
		System.out.println(gsonString);
	}
}
