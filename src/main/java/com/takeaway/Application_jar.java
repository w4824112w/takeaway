package com.takeaway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan(basePackages = "com.takeaway.modular")
@ComponentScan(basePackages={"com.takeaway"})
@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.gkyt.ywgk")
public class Application_jar {

	public static void main(String[] args) {
		SpringApplication.run(Application_jar.class, args);
	}

}