package com.cheng.mybatisgenerate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cheng.mybatisgenerate.dao")
public class MybatisGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGenerateApplication.class, args);
    }

}
