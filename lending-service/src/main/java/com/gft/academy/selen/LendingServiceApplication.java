package com.gft.academy.selen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
//@EnableOAuth2Client
//@EnableResourceServer
public class LendingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingServiceApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public OAuth2FeignRequestInterceptor interceptor(OAuth2ProtectedResourceDetails details, OAuth2ClientContext context) {
//        return new OAuth2FeignRequestInterceptor(context, details);
//    }
//    @Bean
//    @LoadBalanced
//    public OAuth2RestTemplate restTemplate(OAuth2ProtectedResourceDetails details, OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(details, context);
//    }
}
