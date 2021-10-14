package com.ts.multiplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class MultiplicationService {

	private MultiplicationService() {}
	
	public String generateProductResult(String inputValue) {
		List<Map<String, Object>> product = createProductList(inputValue);
		Gson gson = new Gson();
		String gsonString = gson.toJson(product);
		return gsonString;
	}
	
	private List<Map<String, Object>> createProductList(String inputValue) {
		JSONArray jsonArray = new JSONArray(inputValue);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			try {
				productList.add(createProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), jsonObject.getString("product")));
				
			} catch(Exception e) {
				productList.add(exceptionProductMap(jsonObject.getString("product")));
			}
		}
		
		return productList;
	}
	
	private Map<String, Object> exceptionProductMap(String product) {
		Map<String, Object> exceptionProductMap = new HashMap<String, Object>();
		exceptionProductMap.put(product, "계산 가능 범위 초과");
		return exceptionProductMap;
	}
	
	// product1=10 형태로 맵에 저장한다.
	private Map<String, Object> createProductMap(int firstFactor, int secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		if (generateProduct(firstFactor, secondFactor) == null) {
			productMap.put(product, "계산 가능 범위 초과");
		} else {
			productMap.put(product, generateProduct(firstFactor, secondFactor));
		}
		return productMap;
	}

	// firstFactor와 secondFactor 를 인수로 받아서 두 수의 곱의 결과(product)를 리턴한다.
	// 2, 5 -> 10
	private Object generateProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		if (firstFactor == 0 || secondFactor == 0) {
			return 0;
		} else {
			// 두 수의 부호가 같은 경우 
			if (firstFactor > 0 && secondFactor > 0 ||
					firstFactor < 0 && secondFactor < 0) {
				// 곱이 int의 최대값보다 클 경우 
				if(Math.abs(firstFactor) > (Integer.MAX_VALUE / Math.abs(secondFactor))) {
					return null;
				} else {
					return product;
				}
			} else {
				// 곱이 int 최소값보다 작을 경우 
				if(-Math.abs(firstFactor) < (Integer.MIN_VALUE / Math.abs(secondFactor))) {
					return null;
				} else {
					return product;
				}
			}
		}
	}
}
