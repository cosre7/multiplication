package com.ts.multiplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ts.multiplication.service.MultiplicationService;

@Controller
public class MultiplicationController {

	@Autowired
	MultiplicationService multiplicationService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "multiplication";
	}
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getProduct(@RequestBody String inputValue) {
		JSONArray jsonArray = new JSONArray(inputValue);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
	
		try {
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				productList.add(multiplicationService.createProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), jsonObject.getString("product")));
			}
			
		} catch(NumberFormatException e) {
			e.printStackTrace();
			
		}
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		return gsonString;
	}
	
}
