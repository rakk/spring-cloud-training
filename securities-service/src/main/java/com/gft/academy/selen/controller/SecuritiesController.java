package com.gft.academy.selen.controller;

import com.gft.academy.selen.constant.DebtStatus;
import com.gft.academy.selen.domain.Debt;
import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.repository.DebtRepository;
import com.gft.academy.selen.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SecuritiesController {

    private final DebtRepository debtRepository;

    private final SecurityRepository securityRepository;

    @Autowired
    public SecuritiesController(DebtRepository debtRepository, SecurityRepository securityRepository) {
        this.debtRepository = debtRepository;
        this.securityRepository = securityRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/debt")
    public ResponseEntity<Void> incurDebt(@RequestBody Debt debt) {
        if (debt == null || debt.getId() == null || debt.getQuantity() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Security> optionalSecurity = securityRepository.findById(debt.getSecurityId());
        if (!optionalSecurity.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Debt> optionalDebt = debtRepository.findById(debt.getId());

        if (!optionalDebt.isPresent()) {
            if (optionalSecurity.get().getAvailableQuantity() >= debt.getQuantity()) {
                debt.setStatus(DebtStatus.ACTIVE);
                debt = debtRepository.save(debt);
                Security security = optionalSecurity.get();
                security.setAvailableQuantity(security.getAvailableQuantity() - debt.getQuantity());
                securityRepository.save(security);
            } else {
                debt.setStatus(DebtStatus.REJECTED);
                debt = debtRepository.save(debt);
            }
        }
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/debt/{id}")
                .buildAndExpand(debt.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/debt/{id}")
    public ResponseEntity<Debt> returnDebt(@PathVariable Long id) {
        Optional<Debt> optionalDebt = debtRepository.findById(id);
        if (optionalDebt.isPresent()) {
            Debt debt = optionalDebt.get();
            debt.setStatus(DebtStatus.RETURNED);
            debtRepository.save(debt);

            Security security = securityRepository.findById(debt.getSecurityId()).get();
            security.setAvailableQuantity(security.getAvailableQuantity() + debt.getQuantity());
            securityRepository.save(security);
            return ResponseEntity.ok(debt);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/debt/{id}")
    public ResponseEntity<Debt> debtById(@PathVariable Long id) {
        Optional<Debt> optionalDebt = debtRepository.findById(id);
        if (optionalDebt.isPresent()) {
            return ResponseEntity.ok(optionalDebt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/security")
    public ResponseEntity<List<Security>> securities() {
        return ResponseEntity.ok(securityRepository.findAll().collect(Collectors.toList()));
    }
}
