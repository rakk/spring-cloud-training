package com.gft.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    private ServiceFeignClient client;

    @RequestMapping(method = RequestMethod.GET, value = "/request")
    public String index() {
        return client.index();
    }

}
