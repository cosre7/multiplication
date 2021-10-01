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
	public void testCreateProductHashMap() {
		/* ��Ʈ�ѷ� �κ� */
		// ��Ʈ�ѷ����� json �����͸� �ް� ���� �����͸� string ���·� �ٲ��ش�.
		String jString = 
			"{"
				    +   "\"posts\": ["
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
		
		List<Object> list = createJsonProductList(jString);
		System.out.println(list);
		/* ������� ��Ʈ�ѷ� �κ� */
	}
	
	/* ������� service �κ� */
	public List<Object> createJsonProductList(String jString) {
		// String���� ���� jsonString�� json ���·� �����.
		JSONObject jObject = new JSONObject(jString);
		
		// json ������ ���� "posts"��� key�� ���� �迭�� jArray�� �����Ѵ�.
		JSONArray jArray = jObject.getJSONArray("posts");
		
		// 
		List<Object> list = new ArrayList<Object>();
		
		// jArray �迭�� �ִ� ���� ������.
		for (int i = 0; i < jArray.length(); i++) {
			// �迭�� �ִ� ���� obj�� �����Ѵ�.
			JSONObject obj = jArray.getJSONObject(i);
			// json ������ obj �� row key�� ���� ���� row�� �����Ѵ�.
			String row = obj.getString("row");
			int firstFactor = obj.getInt("firstFactor");
			int secondFactor = obj.getInt("secondFactor");
			System.out.println("row : " + row);
			System.out.println("firstFactor : " + firstFactor);
			System.out.println("secondFactor : " + secondFactor);
			System.out.println();
			
			list.add(createJsonProduct(row, firstFactor, secondFactor));
		}
		return list;
		
	}
	
	private JsonObject createJsonProduct(String row, int firstFactor, int secondFactor) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(row, testCreateProduct(firstFactor, secondFactor));
		
//		System.out.println("key ���>>" + productMap.keySet());
//		System.out.println("value ���>>" + productMap.values());
//		System.out.println("Ű��� ���>>" + productMap.toString());
		
		Gson gson = new Gson();
		JsonObject json = gson.toJsonTree(productMap).getAsJsonObject();
//		System.out.println(json);
//		System.out.println(productMap);
		return json;
	}
	
	private int testCreateProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
