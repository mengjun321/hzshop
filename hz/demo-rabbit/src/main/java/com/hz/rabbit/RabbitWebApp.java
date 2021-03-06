package com.hz.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class RabbitWebApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitWebApp.class,args);
    }
}
