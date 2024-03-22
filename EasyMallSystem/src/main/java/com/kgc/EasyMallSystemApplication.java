package com.kgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kgc.dao")
public class EasyMallSystemApplication {

    public static void main(String[] args) {
        System.out.println(1);
        SpringApplication.run(EasyMallSystemApplication.class, args);
    }

}
