package com.gft.academy.selen;

import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SecuritiesServiceApplication implements CommandLineRunner {

    @Autowired
    private SecurityRepository securityRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecuritiesServiceApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        securityRepository.save(new Security("GFT", 1000));
        securityRepository.save(new Security("IBM", 2000));
        securityRepository.save(new Security("MSFT", 5000));
    }
}

