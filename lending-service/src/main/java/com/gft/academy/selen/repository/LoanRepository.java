package com.gft.academy.selen.repository;

import com.gft.academy.selen.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface LoanRepository extends Repository<Loan, Long> {

    Optional<Loan> findById(Long id);

    <S extends Loan> S save(S Loan);

    Stream<Loan> findAll();
}
