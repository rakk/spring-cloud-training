package com.gft.academy.selen.domain;


import com.gft.academy.selen.constant.LoanStatus;

import javax.persistence.*;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;
    private String securityId;
    private Integer quantity;
    private String client;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.NEW;

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
