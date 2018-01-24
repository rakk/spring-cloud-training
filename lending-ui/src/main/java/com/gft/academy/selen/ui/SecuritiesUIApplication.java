package com.gft.academy.selen.ui;

import com.gft.academy.selen.ui.feign.OAuthFeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

@EnableDiscoveryClient
@EnableOAuth2Sso
@EnableFeignClients
@SpringBootApplication
public class SecuritiesUIApplication {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        return new OAuthFeignRequestInterceptor(oauth2ClientContext);
    }

    public static void main(String[] args) {
        SpringApplication.run(SecuritiesUIApplication.class, args);
    }
}
