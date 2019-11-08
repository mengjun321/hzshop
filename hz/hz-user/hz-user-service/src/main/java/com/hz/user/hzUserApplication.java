package com.hz.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hz.user.mapper")
public class hzUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(hzUserApplication.class, args);
    }
}
