package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world!";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world from Bean!");
	}
	
	@GetMapping(path = "/hello/path-variable/{message}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable("message") String message) {
		return new HelloWorldBean("Hello " + message);
	}

}