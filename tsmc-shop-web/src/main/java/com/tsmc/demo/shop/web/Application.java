package com.tsmc.demo.shop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jkos.demo.shop")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
