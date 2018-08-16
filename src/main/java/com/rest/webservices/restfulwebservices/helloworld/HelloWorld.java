package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * Steps: 1. Create messages.properties for different languages 
	 * 2. Configure beans LocaleResolver and ResourceBundleMessageSource 
	 * 3. Autowire dependency
	 * 4. Write below implementation 
	 * 5. Test by setting various header params in Rest client
	 * 6. Note. We don't need to pass locale as header param in method There are other
	 * ways as well. See commented code for old reference
	 */
	@GetMapping(path = "/hello-world-internationaized")
	// public String
	// helloWorldInternationalized(@RequestHeader(/*name="Accept-Language",
	// required=false) Locale locale*/)
	public String helloWorldInternationalized() {
		// return messageSource.getMessage("good.morning.message", null, locale);
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}