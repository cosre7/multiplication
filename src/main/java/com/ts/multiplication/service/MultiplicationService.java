package com.ts.multiplication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MultiplicationService {

	private MultiplicationService() {}
	
	public Map<String, Object> createProductMap(int firstFactor, int secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, createProduct(firstFactor, secondFactor));
		
		return productMap;
	}

	private int createProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
