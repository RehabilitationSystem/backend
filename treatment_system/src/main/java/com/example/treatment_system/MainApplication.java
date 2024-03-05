package com.example.treatment_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;



@PropertySource("classpath:/application.properties")
@MapperScan({"com.example.reservation_control.dao","com.example.treatment_system.dao","com.example.authetic_system.dao","com.example.logger_security.dao","com.example.comment_control.dao"})
@SpringBootApplication(scanBasePackages = {"com.example.treatment_system","com.example.authetic_system","com.example.logger_security","com.example.commons","com.example.reservation_control"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}





