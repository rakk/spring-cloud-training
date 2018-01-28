package com.gft.academy.selen.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.tokenInfoUri}")
    private String tokenInfoEndpointUrl;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(facebookTokenInfoTokenServices());
    }

    @Bean
    @Primary
    public ResourceServerTokenServices facebookTokenInfoTokenServices() {
        return new FacebookRemoteTokenInfoTokenServices(tokenInfoEndpointUrl, clientId, clientSecret);
    }

}
