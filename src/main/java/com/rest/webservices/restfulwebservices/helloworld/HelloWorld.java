package com.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@Autowired
	MessageSource messageSource;

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
	
	/*
	 * Steps:
	 * 1. Create messages.properties for different languages
	 * 2. Configure beans LocaleResolver and ResourceBundleMessageSource
	 * 3. Autowire dependency
	 * 4. Write below method	 * 
	 */
	@GetMapping(path = "/hello-world-internationaized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

}