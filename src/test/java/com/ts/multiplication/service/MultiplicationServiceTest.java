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
		/* 컨트롤러 부분 */
		// 컨트롤러에서 json 데이터를 받고 받은 데이터를 string 형태로 바꿔준다.
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
		/* 여기까지 컨트롤러 부분 */
	}
	
	/* 여기부터 service 부분 */
	public List<Object> createJsonProductList(String jString) {
		// String으로 받은 jsonString을 json 형태로 만든다.
		JSONObject jObject = new JSONObject(jString);
		
		// json 데이터 안의 "posts"라는 key를 가진 배열을 jArray에 저장한다.
		JSONArray jArray = jObject.getJSONArray("posts");
		
		// 
		List<Object> list = new ArrayList<Object>();
		
		// jArray 배열에 있는 값을 꺼낸다.
		for (int i = 0; i < jArray.length(); i++) {
			// 배열에 있는 값을 obj에 저장한다.
			JSONObject obj = jArray.getJSONObject(i);
			// json 데이터 obj 중 row key를 가진 값을 row에 저장한다.
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
		
//		System.out.println("key 출력>>" + productMap.keySet());
//		System.out.println("value 출력>>" + productMap.values());
//		System.out.println("키밸류 출력>>" + productMap.toString());
		
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
