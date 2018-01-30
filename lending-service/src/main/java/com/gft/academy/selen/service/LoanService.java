package com.gft.academy.selen.service;

import com.gft.academy.selen.constant.LoanStatus;
import com.gft.academy.selen.domain.Loan;
import com.gft.academy.selen.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
@ManagedResource
public class LoanService {

    private final LoanRepository loanRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public LoanService(LoanRepository loanRepository, RestTemplate restTemplate) {
        this.loanRepository = loanRepository;
        this.restTemplate = restTemplate;
    }

    @ManagedOperation
    public Loan takeOutLoan(String securityId, Integer quantity) {
        Loan loan = new Loan();
        loan.setSecurityId(securityId);
        loan.setQuantity(quantity);

        loan = loanRepository.save(loan);

        URI targetURI = restTemplate.postForLocation("http://securities-service/debt", loan);
        Loan transfer = restTemplate.getForObject(targetURI, Loan.class);
        loan.setStatus(transfer.getStatus());
        return loan;
    }

    public Loan returnLoan(Loan loan) {
        restTemplate.put("http://securities-service/debt/" + loan.getId(), loan);
        loan.setStatus(LoanStatus.RETURNED);
        loan = loanRepository.save(loan);
        return loan;
    }

    public Optional<Loan> findOne(Long id) {
        return loanRepository.findById(id);
    }

    public Stream<Loan> findAll() {
        return loanRepository.findAll();
    }
}
