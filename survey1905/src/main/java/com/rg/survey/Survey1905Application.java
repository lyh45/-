package com.rg.survey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.rg.survey.mapper"})
public class Survey1905Application {

    public static void main(String[] args) {
        SpringApplication.run(Survey1905Application.class, args);
    }

}
