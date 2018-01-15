package com.gft.academy.selen.repository;


import com.gft.academy.selen.domain.Debt;
import com.gft.academy.selen.domain.Security;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface SecurityRepository extends Repository<Security, String> {

    Optional<Security> findById(String id);

    <S extends Security> S save(S Security);

    Stream<Security> findAll();
}
