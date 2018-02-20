package com.gft.academy.selen.client;

import com.gft.academy.selen.constant.LoanStatus;
import com.gft.academy.selen.domain.Loan;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class SecuritiesClient {

    private final RestTemplate restTemplate;

    @Autowired
    public SecuritiesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "pending")
    public LoanStatus incurDebt(Loan loan) {
        URI targetURI = restTemplate.postForLocation("http://securities-service/debt", loan);
        Loan transfer = restTemplate.getForObject(targetURI, Loan.class);
        return  transfer.getStatus();
    }

    public LoanStatus pending(Loan loan) {
        return LoanStatus.PENDING;
    }

    @HystrixCommand(fallbackMethod = "pendingReturn")
    public LoanStatus returnDebt(Loan loan) {
        ResponseEntity<Loan> returned = restTemplate.exchange ("http://securities-service/debt/" + loan.getId(), HttpMethod.PUT, new HttpEntity<>(loan), Loan.class);
        return returned.getBody().getStatus();
    }

    public LoanStatus pendingReturn(Loan loan) {
        return LoanStatus.PENDING_RETURN;
    }
}
