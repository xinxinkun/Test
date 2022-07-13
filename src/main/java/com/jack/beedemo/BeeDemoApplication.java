package com.jack.beedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jack.beedemo.mapper")
public class BeeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeDemoApplication.class, args);
    }

}
