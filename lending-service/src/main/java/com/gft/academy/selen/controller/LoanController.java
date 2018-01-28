package com.gft.academy.selen.controller;

import com.gft.academy.selen.domain.Loan;
import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.feign.SecuritiesClient;
import com.gft.academy.selen.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/loan")
public class LoanController {

    private final LoanService loanService;
    private final SecuritiesClient securitiesClient;

    @Autowired
    public LoanController(LoanService loanService, SecuritiesClient securitiesClient) {
        this.loanService = loanService;
        this.securitiesClient = securitiesClient;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> takeOutLoan(@RequestParam String securityId, @RequestParam Integer quantity) {
        Set<String> availableSecurityIds = securitiesClient.getAvailableSecurities().stream().map(Security::getId).collect(Collectors.toSet());
        if (!availableSecurityIds.contains(securityId)) {
            return ResponseEntity.badRequest().build();
        }
        Loan loan = loanService.takeOutLoan(securityId, quantity);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/loan/{id}")
                .buildAndExpand(loan.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id) {
        Optional<Loan> optionalLoan = loanService.findOne(id);
        if (optionalLoan.isPresent()) {
            Loan loan = loanService.returnLoan(optionalLoan.get());
            return ResponseEntity.ok(loan);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Loan>> readAllLoans() {
        List<Loan> loans = loanService.findAll().collect(Collectors.toList());
        if (!loans.isEmpty()) {
            return ResponseEntity.ok(loans);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Loan> findLoan(@PathVariable Long id) {
        Optional<Loan> optionalLoan = loanService.findOne(id);
        if (optionalLoan.isPresent()) {
            return ResponseEntity.ok(optionalLoan.get());
        }
        return ResponseEntity.notFound().build();
    }

}
