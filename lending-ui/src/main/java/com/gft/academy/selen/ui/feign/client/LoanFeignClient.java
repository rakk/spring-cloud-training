package com.gft.academy.selen.ui.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "lending-service", decode404 = true)
public interface LoanFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/loan")
    String readAllLoans();
}
