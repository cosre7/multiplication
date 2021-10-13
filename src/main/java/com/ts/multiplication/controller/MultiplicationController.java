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
		// @RequestBody : Ŭ���̾�Ʈ�� �����ϴ� Json ������ HTTP Body ������ Java Object�� ��ȯ�����ִ� ����
		// 				  - body�� �ִ� �����͸� �����´�.
		System.out.println(inputValue);
		
		// json �迭������ ���ڿ� inputValue�� jsonArray�� ��´�. 
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
					// �Է°��� int ������ �Ѵ� ��� �߻��ϴ� NumberFormatException ���� ���� 
					System.out.println("�ʰ�");
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
