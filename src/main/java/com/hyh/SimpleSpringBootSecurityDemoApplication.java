package com.hyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SimpleSpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootSecurityDemoApplication.class, args);
	}
}
