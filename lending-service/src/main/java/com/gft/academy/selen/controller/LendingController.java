package com.gft.academy.selen.controller;

import com.gft.academy.selen.domain.Loan;
import com.gft.academy.selen.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @RequestMapping(method = RequestMethod.POST, path = "/loan")
    public Loan lend(String securityId, Integer quantity) {
        Loan request = lendingService.createLoanRequest(securityId, quantity);
        lendingService.processLoanRequest(request);
        return request;
    }
}
