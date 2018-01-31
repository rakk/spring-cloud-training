//package com.gft.academy.selen.hystrix;
//
//
//import com.gft.academy.selen.constant.LoanStatus;
//import com.gft.academy.selen.domain.Loan;
//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import com.netflix.hystrix.HystrixCommandKey;
//import com.netflix.hystrix.HystrixCommandProperties;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//
//public class IncurDebtCommand extends HystrixCommand<LoanStatus> {
//
//    private RestTemplate restTemplate;
//    private Loan loan;
//
//    public IncurDebtCommand(RestTemplate restTemplate, Loan loan) {
//        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Group1"))
//                .andCommandKey(HystrixCommandKey.Factory.asKey("IncurCommand"))
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
//        this.restTemplate = restTemplate;
//        this.loan = loan;
//    }
//
//    @Override
//    protected LoanStatus run() throws Exception {
//        URI targetURI = restTemplate.postForLocation("http://securities-service/debt", loan);
//        Loan transfer = restTemplate.getForObject(targetURI, Loan.class);
//        return transfer.getStatus();
//    }
//
//    @Override
//    protected LoanStatus getFallback() {
//        return LoanStatus.PENDING;
//    }
//}
