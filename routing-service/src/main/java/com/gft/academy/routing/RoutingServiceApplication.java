package com.gft.academy.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.gft.academy.routing.fallback.DefaultFallback;

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
}
