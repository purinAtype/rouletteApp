package com.example.rouletteApp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.rouletteApp.mapper")
public class RouletteAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouletteAppApplication.class, args);
    }
}
