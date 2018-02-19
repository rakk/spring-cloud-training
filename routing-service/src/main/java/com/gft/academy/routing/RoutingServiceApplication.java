package com.gft.academy.routing;

import com.gft.academy.routing.fallback.DefaultFallback;
import com.gft.academy.routing.filter.PreLogFilter;
import com.gft.academy.routing.filter.VersionRouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    PreLogFilter preLogFilter() {
        return new PreLogFilter();
    }

    @Bean
    VersionRouteFilter versionRouteFilter() {
        return new VersionRouteFilter();
    }

}


