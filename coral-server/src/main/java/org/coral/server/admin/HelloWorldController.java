package org.coral.server.admin;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("=====================");
		return "Hello World";
	}

	@RequestMapping(value = "/param2")
	public String param2(@RequestParam Map<String, String> parameter) {
		System.out.println(parameter); 
		return parameter.toString();
	}
	
}
