package com.gft.academy.routing;

import com.gft.academy.routing.fallback.DefaultFallback;
import com.gft.academy.routing.fallback.WebFallback;
import com.gft.academy.routing.filter.PreLogFilter;
import com.gft.academy.routing.filter.VersionRouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class RoutingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutingServiceApplication.class, args);
    }

    @Bean
    DefaultFallback defaultFallback() {
        return new DefaultFallback();
    }

    @Bean
    WebFallback webFallback() {
        return new WebFallback();
    }

    @Bean
    PreLogFilter preLoginFilter() {
        return new PreLogFilter();
    }

    @Bean
    VersionRouteFilter versionRouteFilter() {
        return new VersionRouteFilter();
    }

}


