package com.gft.oauth.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:8081", name = "service")
public interface ServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    String index();
}
