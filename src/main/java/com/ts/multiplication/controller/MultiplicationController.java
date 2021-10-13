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
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "multiplication";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getProduct.do", method = RequestMethod.POST)
	public String getProduct(@RequestBody String inputValue) {
		// @RequestBody : 클라이언트가 전송하는 Json 형태의 HTTP Body 내용을 Java Object로 변환시켜주는 역할
		// 				  - body에 있는 데이터를 가져온다.
		System.out.println(inputValue);
		
		// json 배열형태의 문자열 inputValue를 jsonArray에 담는다. 
		JSONArray jsonArray = new JSONArray(inputValue);
		List<Map<String, Object>> productList = new ArrayList<Map<String, Object>>();
	
			
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				System.out.println(jsonObject); 
				/*{"product":"product1","firstFactor":"2","secondFactor":"5"}
				{"product":"product7","firstFactor":"6","secondFactor":"8"}*/
				productList.add(multiplicationService.createProductMap(jsonObject.getInt("firstFactor"), jsonObject.getInt("secondFactor"), jsonObject.getString("product")));
				} catch(NumberFormatException e) { 
					// 입력값이 int 범위를 넘는 경우 발생하는 NumberFormatException 에러 제어 
					System.out.println("초과");
					continue;
				}
			}
			
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(productList);
		System.out.println(gsonString);
		/* [{"product1":3},{"product7":35}]
		 * */
		return gsonString;
	}
	
}
