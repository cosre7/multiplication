package com.ts.multiplication.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MultiplicationService {

	private MultiplicationService() {}
	
	public Map<String, Object> createProductMap(BigInteger firstFactor, BigInteger secondFactor, String product) {
		Map<String, Object> productMap = new HashMap<String, Object>();
		productMap.put(product, createProduct(firstFactor, secondFactor));
		
		return productMap;
	}

	private BigInteger createProduct(BigInteger firstFactor, BigInteger secondFactor) {
		BigInteger product = firstFactor.multiply(secondFactor);
		return product;
	}
}
