package com.ts.multiplication;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ts.multiplication.service.MultiplicationServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:servlet-context.xml")
public class ModuleTests {

	/*
	 * @Autowired private MultiplicationServiceTest multiplicationService;
	 * 
	 * public void testFunction() {
	 * multiplicationService.testCreateProductHashMap(); }
	 */
}
