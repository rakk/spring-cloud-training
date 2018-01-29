package com.gft.academy.selen.ui;

import com.gft.academy.selen.ui.config.FeignOAuth2PassthroughRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableGlobalMethodSecurity
public class LendingUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingUIApplication.class, args);
    }

    @Bean
    public FeignOAuth2PassthroughRequestInterceptor feignOAuth2RequestInterceptor() {
        return new FeignOAuth2PassthroughRequestInterceptor();
    }
}
