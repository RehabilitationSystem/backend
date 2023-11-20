package com.example.news_control;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
@PropertySource("classpath:/application.properties")
@MapperScan({"com.example.login_authetic.dao","com.example.news_control"})
@SpringBootApplication(scanBasePackages = {"com.example.news_control","com.example.login_authetic","com.example.comment_control","com.example.logger_security","com.example.commons"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}





