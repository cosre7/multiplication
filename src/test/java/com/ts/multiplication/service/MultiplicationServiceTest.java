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
		/* ��Ʈ�ѷ� �κ� */
		// ��Ʈ�ѷ����� json �����͸� �ް� ���� �����͸� string ���·� �ٲ��ش�.
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
					    +           "\"secondFactor\": 33333333333��33333333333,"
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
		

		JSONArray jsonArray = new JSONArray(jString);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
//		System.out.println(jsonObject.get("product"));
			productList.add(testcreateProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), (String) jsonObject.get("product")));
			} catch(Exception e) {
				System.out.println("�ʰ�");
				
			}
		}
			
			
		/* ������� ��Ʈ�ѷ� �κ� */
//		System.out.println(productList);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		System.out.println(gsonString);
	}
	
	/* ������� service �κ� */
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
