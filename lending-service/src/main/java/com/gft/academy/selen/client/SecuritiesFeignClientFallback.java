package com.gft.academy.selen.client;

import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.client.SecuritiesFeignClient;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecuritiesFeignClientFallback implements SecuritiesFeignClient {

    @Override
    public List<Security> getAvailableSecurities() {
        return Lists.newArrayList();
    }
}
