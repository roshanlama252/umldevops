package com.leszko.calculator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
/**
 * 
*@Service
*/
public class Calculator {
        final static int umlNUMBER1 = A;
	@Cacheable("sum")
	public int sum(int a, int b) {
		return a + b;
	}
}
