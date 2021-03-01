package com.coral.test.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.coral.api.dto.Tester;
import com.coral.api.service.ITestService;

@Service
@Component
public class TestService implements ITestService{

	@Override
	public String test(Tester tester) {
		return "Hello world, I am ".concat(tester.getName()).concat("!");
	}

}
