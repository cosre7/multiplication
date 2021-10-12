package com.ts.multiplication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MultiplicationService {

	private MultiplicationService() {}
	
	// product1=10 형태로 맵에 저장한다.
	public Map<String, Object> createProductMap(int firstFactor, int secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, createProduct(firstFactor, secondFactor));
		return productMap;
	}

	// firstFactor와 secondFactor 를 인수로 받아서 두 수의 곱의 결과(product)를 리턴한다.
	// 2, 5 -> 10
	private int createProduct(int firstFactor, int secondFactor) {
		int product = firstFactor * secondFactor;
		return product;
	}
}
