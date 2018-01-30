package com.gft.academy.selen.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableGlobalMethodSecurity
public class LendingUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingUIApplication.class, args);
    }

}
