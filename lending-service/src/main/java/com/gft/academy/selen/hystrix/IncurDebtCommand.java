package com.gft.academy.selen.hystrix;

import com.gft.academy.selen.constant.LoanStatus;
import com.gft.academy.selen.domain.Loan;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class IncurDebtCommand extends HystrixCommand<Loan> {

    private RestTemplate restTemplate;
    private Loan loan;

    public IncurDebtCommand(RestTemplate restTemplate, Loan loan) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("incurDebt"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(10000)));
        this.restTemplate = restTemplate;
        this.loan = loan;
    }

    @Override
    protected Loan run() throws Exception {
        URI targetURI = restTemplate.postForLocation("http://securities-service/debt", loan);
        Loan transfer = restTemplate.getForObject(targetURI, Loan.class);
        return transfer;
    }

    @Override
    protected Loan getFallback() {
        loan.setStatus(LoanStatus.PENDING);
        return loan;
    }
}
