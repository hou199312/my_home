package com.houzizhen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// SpringBootApplication 包括了Configuration、EnableAutoConfiguration、ComponentScan三个注解。
@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
