package com.ts.multiplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class MultiplicationService {

	private MultiplicationService() {}
	
	public List<Object> createJsonProductList(String inputValue) {
		JSONObject jsonObject = new JSONObject(inputValue);
		
		JSONArray jsonArray = jsonObject.getJSONArray("inputValue");
		
		List<Object> jsonProductList = new ArrayList<Object>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String row = obj.getString("row");
			int firstFactor = obj.getInt("firstFactor");
			int secondFactor = obj.getInt("secondFactor");
			
			jsonProductList.add(createJsonProduct(row, firstFactor, secondFactor));
		}
		return jsonProductList;
	}

	private JsonObject createJsonProduct(String row, int firstFactor, int secondFactor) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(row, createProduct(firstFactor, secondFactor));
		
		Gson gson = new Gson();
		JsonObject json = gson.toJsonTree(productMap).getAsJsonObject();
		return json;
	}

	private int createProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
