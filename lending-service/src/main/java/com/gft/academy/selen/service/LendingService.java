package com.gft.academy.selen.service;

import com.gft.academy.selen.constant.LoanRequestStatus;
import com.gft.academy.selen.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LendingService {

    private final Map<String, Loan> cache = new ConcurrentHashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public Loan createLoanRequest(String securityId, Integer quantity) {
        Loan request = new Loan(securityId, quantity, null);
        cache.put(request.getRequestId(), request);
        return request;
    }

    public Loan getByRequestId(String requestId) {
        return cache.get(requestId);
    }

    public void processLoanRequest(Loan request) {
        LoanRequestStatus status = restTemplate.postForObject("http://localhost:9001/transfer", request, LoanRequestStatus.class);
        request.setStatus(status);
    }
}
