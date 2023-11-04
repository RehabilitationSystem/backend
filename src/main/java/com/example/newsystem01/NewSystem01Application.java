package com.example.newsystem01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
@MapperScan("com.example.newsystem01.dao")
public class NewSystem01Application {

    public static void main(String[] args) {
        SpringApplication.run(NewSystem01Application.class, args);
    }

}
