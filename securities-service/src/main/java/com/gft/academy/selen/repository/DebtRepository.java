package com.gft.academy.selen.repository;

import com.gft.academy.selen.domain.Debt;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DebtRepository extends Repository<Debt, Long> {

    Optional<Debt> findById(Long id);

    <S extends Debt> S save(S Debt);

    Stream<Debt> findBySecurityId(String securityId);

}
