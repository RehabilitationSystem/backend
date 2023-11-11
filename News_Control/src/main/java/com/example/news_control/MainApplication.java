package com.example.news_control;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.properties")
@MapperScan("com.example.newsystem01.dao")
@SpringBootApplication(scanBasePackages = {"com.example.news_control","com.example.newsystem01","com.example.login_authetic"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}





