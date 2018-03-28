package com.takeaway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "com.gkyt.ywgk.modular")
public class YwgkApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YwgkApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YwgkApplication.class);
    }
}