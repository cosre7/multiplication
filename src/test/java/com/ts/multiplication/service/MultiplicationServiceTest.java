package com.ts.multiplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;


public class MultiplicationServiceTest {
	
	@Test
	public void testService() {
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
					    +           "\"firstFactor\": 21474836479449,"
					    +           "\"secondFactor\": 33333333333ㅇ33333333333,"
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
		

			
			
		/* 컨트롤러 부분 */
		String gsonString = testcreateProduct(jString);
		System.out.println(gsonString);
		/* 여기까지 컨트롤러 부분 */
	}
	public String testcreateProduct(String jString) {
		List<Map<String, Object>> productList = testcreateProductList(jString);
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		return gsonString;
	}
	
	private List<Map<String, Object>> testcreateProductList(String jString) {
		JSONArray jsonArray = new JSONArray(jString);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			try {
				
//		System.out.println(jsonObject.get("product"));
				productList.add(testcreateProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), (String) jsonObject.get("product")));
			} catch(Exception e) {
				System.out.println("초과");
				productList.add(testExceptionProduct(jsonObject.getString("product")));
			}
		}
		
		return productList;
	}
	
	/* 여기부터 service 부분 */
	private Map<String, Object> testcreateProductMap(int firstFactor, int secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, testCreateProduct(firstFactor, secondFactor));
		
		return productMap;
	}
	
	private Map<String, Object> testExceptionProduct(String product) {
		Map<String, Object> exceptionProduct = new HashMap<String, Object>();
		exceptionProduct.put(product, "범위를 초과하였습니다.");
		return exceptionProduct;
	}
	
	private int testCreateProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
