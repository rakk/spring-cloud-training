package com.gft.academy.selen.controller;

import com.gft.academy.selen.constant.TransferStatus;
import com.gft.academy.selen.domain.TransferRequest;
import com.gft.academy.selen.exception.BadRequestException;
import com.gft.academy.selen.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class SecuritiesController {

    private final Map<String, TransferStatus> cache = new ConcurrentHashMap<>();

    @RequestMapping(method = RequestMethod.POST, path = "/transfer")
    public TransferStatus transfer(@RequestBody  TransferRequest transferRequest) {
        if (transferRequest.getSecurityId().length() < 3) {
            throw new BadRequestException("Unknown security id: " + transferRequest.getSecurityId());
        }

        TransferStatus transferStatus = cache.get(transferRequest.getRequestId());
        if (transferStatus != null) {
            return transferStatus;
        }
        cache.put(transferRequest.getRequestId(), TransferStatus.IN_PROGRESS);

        // It takes some time to transfer securities
        try {
            TimeUnit.MILLISECONDS.sleep(transferRequest.getQuantity());
        } catch (InterruptedException e) {
        }

        if ("GFT".equalsIgnoreCase(transferRequest.getSecurityId())) {
            transferStatus = TransferStatus.REJECTED;
        } else {
            transferStatus = TransferStatus.COMPLETED;
        }
        cache.put(transferRequest.getRequestId(), transferStatus);
        return transferStatus;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/transfer/{requestId}")
    public TransferStatus getTransferStatus(@PathVariable String requestId) {
        TransferStatus transferStatus = cache.get(requestId);
        if (transferStatus == null) {
            throw new ResourceNotFoundException("No transfer status for request id: " + requestId);
        }
        return transferStatus;
    }

}
