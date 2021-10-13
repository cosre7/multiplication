package com.ts.multiplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
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
					    +           "\"firstFactor\": -99999999999999999999999999999999,"
					    +           "\"secondFactor\": 999999999999999999999999999999,"
					    +			"\"product\": \"product4\""	
					    +       "}"
					    +   "]";	
		
		JSONArray jsonArray = new JSONArray(jString);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
//			System.out.println(jsonObject.get("product"));
				productList.add(multiplicationService.createProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), (String) jsonObject.get("product")));
				} catch(Exception e) {
					System.out.println("초과");
					
				}
			}
			
		/* 여기까지 컨트롤러 부분 */
//		System.out.println(productList);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		System.out.println(gsonString);
	}
}
