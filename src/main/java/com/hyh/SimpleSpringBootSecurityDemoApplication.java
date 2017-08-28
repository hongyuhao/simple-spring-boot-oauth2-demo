package com.hyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
// 定义mybatisMapper代码扫描，也就是平时所说的dao层
@MapperScan("com.hyh.api.dao")
public class SimpleSpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringBootSecurityDemoApplication.class, args);
	}
}
