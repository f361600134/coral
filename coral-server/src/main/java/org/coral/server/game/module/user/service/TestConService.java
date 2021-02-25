package org.coral.server.game.module.user.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.coral.api.dto.Tester;
import com.coral.api.service.ITestService;

@Component
public class TestConService {

	@Reference(version = "1.0.0")
	private ITestService testService;

	public void print() {
		String name = testService.test(new Tester("Jeremy"));
		System.out.println(name);
	}

}
