package com.gft.academy.selen.domain;


import com.gft.academy.selen.constant.LoanRequestStatus;

import java.util.UUID;

public class Loan {

    private final String requestId = UUID.randomUUID().toString();
    private final String securityId;
    private final Integer quantity;
    private final String clientId;
    private LoanRequestStatus status = LoanRequestStatus.NEW;

    public Loan(String securityId, Integer quantity, String clientId) {
        this.securityId = securityId;
        this.quantity = quantity;
        this.clientId = clientId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getSecurityId() {
        return securityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getClientId() {
        return clientId;
    }

    public LoanRequestStatus getStatus() {
        return status;
    }

    public void setStatus(LoanRequestStatus status) {
        this.status = status;
    }
}
