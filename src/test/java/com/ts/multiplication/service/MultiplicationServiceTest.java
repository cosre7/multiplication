package com.ts.multiplication.service;

import java.math.BigInteger;
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
					    +           "\"firstFactor\": 214748364799,"
					    +           "\"secondFactor\": 214748364799,"
					    +			"\"product\": \"product4\""	
					    +       "}"
					    +   "]";	
		

		JSONArray jsonArray = new JSONArray(jString);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			BigInteger firstFactor = new BigInteger(jsonObject.get("firstFactor").toString());
			BigInteger secondFactor = new BigInteger(jsonObject.get("secondFactor").toString());
			
//			System.out.println(jsonObject.get("product"));
			productList.add(testcreateProductMap(firstFactor, secondFactor, (String) jsonObject.get("product")));
		}
		/* ������� ��Ʈ�ѷ� �κ� */
//		System.out.println(productList);
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		System.out.println(gsonString);
	}
	
	/* ������� service �κ� */
	private Map<String, Object> testcreateProductMap(BigInteger firstFactor, BigInteger secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, testCreateProduct(firstFactor, secondFactor));
		
		return productMap;
	}
	
	private BigInteger testCreateProduct(BigInteger firstFactor, BigInteger secondFactor) {
		BigInteger product = firstFactor.multiply(secondFactor);
		return product;
	}
}
