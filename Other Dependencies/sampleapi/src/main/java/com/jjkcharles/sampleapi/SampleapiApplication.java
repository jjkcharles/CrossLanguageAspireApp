package com.jjkcharles.sampleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan({"com.jjkcharles.controllers", "com.jjkcharles.Common"})
public class SampleapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleapiApplication.class, args);
	}

	@RequestMapping
	public String home() {
		return "Hello World";
	}
}
