package com.gft.academy.selen.service;

import com.gft.academy.selen.client.SecuritiesClient;
import com.gft.academy.selen.domain.Loan;
import com.gft.academy.selen.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
@ManagedResource
public class LoanService {

    private final LoanRepository loanRepository;

    private final SecuritiesClient securitiesClient;

    @Autowired
    public LoanService(LoanRepository loanRepository, SecuritiesClient securitiesClient) {
        this.loanRepository = loanRepository;
        this.securitiesClient = securitiesClient;
    }

    @ManagedOperation
    public Loan takeOutLoan(String securityId, Integer quantity) {
        Loan loan = new Loan();
        loan.setSecurityId(securityId);
        loan.setQuantity(quantity);
        loan = loanRepository.save(loan);

        loan.setStatus(securitiesClient.incurDebt(loan));
        return loan;
    }

    public Loan returnLoan(Loan loan) {
        loan.setStatus(securitiesClient.returnDebt(loan));
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
