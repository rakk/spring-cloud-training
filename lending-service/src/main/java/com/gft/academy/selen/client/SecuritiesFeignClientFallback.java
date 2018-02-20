package com.gft.academy.selen.client;

import com.gft.academy.selen.domain.Security;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SecuritiesFeignClientFallback implements SecurityFeignClient {

    @Override
    public ResponseEntity<List<Security>> securities() {
        return ResponseEntity.ok(Arrays.asList(new Security("IBM", 6000)));
    }
}
