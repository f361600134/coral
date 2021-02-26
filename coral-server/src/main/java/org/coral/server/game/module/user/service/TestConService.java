package org.coral.server.game.module.user.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.coral.api.dto.Tester;
import com.coral.api.service.ITestService;

@Service
public class TestConService {

	@Reference
	private ITestService testService;

	public void print() {
		String name = testService.test(new Tester("Jeremy"));
		System.out.println(name);
	}

}
