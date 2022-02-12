package com.rbacpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MainServer {

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }
}