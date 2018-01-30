package com.gft.academy.selen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    @Primary
    public FacebookRemoteTokenServices facebookTokenServices() {
        return new FacebookRemoteTokenServices();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(facebookTokenServices());
    }

}
