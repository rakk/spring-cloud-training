package com.cloud.training.tracingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TracingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingServiceApplication.class, args);
	}
}
