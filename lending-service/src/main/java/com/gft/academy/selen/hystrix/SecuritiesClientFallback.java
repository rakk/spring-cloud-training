package com.gft.academy.selen.hystrix;

import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.feign.SecuritiesClient;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecuritiesClientFallback implements SecuritiesClient{

    @Override
    public List<Security> getAvailableSecurities() {
        return Lists.newArrayList();
    }
}
