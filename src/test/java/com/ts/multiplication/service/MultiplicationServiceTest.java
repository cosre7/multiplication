package com.ts.multiplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class MultiplicationServiceTest {
	
	@Test
	public void testService() {
		/* 컨트롤러 부분 */
		// 컨트롤러에서 json 데이터를 받고 받은 데이터를 string 형태로 바꿔준다.
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
					    +           "\"firstFactor\": 5,"
					    +           "\"secondFactor\": 6,"
					    +			"\"product\": \"product4\""	
					    +       "}"
					    +   "]";	
		

		JSONArray jsonArray = new JSONArray(jString);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
//			System.out.println(jsonObject.get("product"));
			productList.add(testcreateProductMap((Integer) jsonObject.get("firstFactor"), (Integer) jsonObject.get("secondFactor"), (String) jsonObject.get("product")));
		}
		/* 여기까지 컨트롤러 부분 */
//		System.out.println(productList);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
//		System.out.println(gsonString);
	}
	
	/* 여기부터 service 부분 */
	private Map<String, Object> testcreateProductMap(int firstFactor, int secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, testCreateProduct(firstFactor, secondFactor));
		
		return productMap;
	}
	
	private int testCreateProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
