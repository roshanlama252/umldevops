package com.leszko.calculator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@SpringBootApplication(scanBasePackages={
"com.leszko.calculator"})

@Service
public class Calculator {
        final static int umlNUMBER1 = 3;
	@Cacheable("sum")
	public int sum(int a, int b) {
		return a + b;
	}
}
