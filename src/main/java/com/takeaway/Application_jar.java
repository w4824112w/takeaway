package com.takeaway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.takeaway.modular")
public class Application_jar {

	public static void main(String[] args) {
		SpringApplication.run(Application_jar.class, args);
	}

}