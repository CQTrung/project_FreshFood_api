package com.example.freshfoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.example.freshfoodapi.mapper")
public class FreshFoodApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreshFoodApiApplication.class, args);
    }

}
