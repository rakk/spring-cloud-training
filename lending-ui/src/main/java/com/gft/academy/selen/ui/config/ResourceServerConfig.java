package com.gft.academy.selen.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Bean
//    public FeignOAuth2PassthroughRequestInterceptor feignOAuth2RequestInterceptor() {
//        return new FeignOAuth2PassthroughRequestInterceptor();
//    }
}
