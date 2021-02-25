package com.coral.test.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.coral.api.dto.Tester;
import com.coral.api.service.ITestService;

@Service(version = "1.0.0")
public class TestService implements ITestService{

	@Override
	public String test(Tester tester) {
		return "Hello world, I am ".concat(tester.getName()).concat("!");
	}

}
