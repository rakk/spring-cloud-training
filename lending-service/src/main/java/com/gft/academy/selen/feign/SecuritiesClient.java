package com.gft.academy.selen.feign;

import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.hystrix.SecuritiesClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "securities-service", fallback = SecuritiesClientFallback.class)
public interface SecuritiesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/security")
    List<Security> getAvailableSecurities();
}


