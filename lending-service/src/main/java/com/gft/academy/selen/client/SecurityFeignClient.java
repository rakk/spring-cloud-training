//package com.gft.academy.selen.client;
//
//import com.gft.academy.selen.domain.Security;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@FeignClient(name = "securities-service", fallback = SecuritiesFeignClientFallback.class)
//public interface SecurityFeignClient {
//
//    @RequestMapping(method = RequestMethod.GET, path = "/security")
//    List<Security> securities();
//
//}
