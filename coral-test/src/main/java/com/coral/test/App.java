package com.coral.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.coral.api.dto.Tester;
import com.coral.test.service.TestService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	public static void main(String[] args) {
		 ConfigurableApplicationContext run = SpringApplication.run(App.class,args);
		 TestService service = run.getBean(TestService.class);
		 System.out.println(service.test(new Tester("aa")));
    }
}
