package com.example.springbootshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootshardingsphere.mapper")
public class SpringbootShardingSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingSphereApplication.class, args);
    }

}
