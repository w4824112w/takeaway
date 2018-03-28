package com.takeaway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gkyt.ywgk.modular")
public class YwgkApplication_jar {

	public static void main(String[] args) {
		SpringApplication.run(YwgkApplication_jar.class, args);
	}

}