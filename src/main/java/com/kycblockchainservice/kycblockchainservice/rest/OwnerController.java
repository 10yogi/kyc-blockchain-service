package com.kycblockchainservice.kycblockchainservice.rest;

import com.kycblockchainservice.kycblockchainservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;

@RestController
public class OwnerController {

    @Value("${chain.contract.owner-address}")
    private String ownerAddress;

    @Autowired
    private Web3j web3j;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owner")
    public String getAddress() {
        return ownerAddress;
    }


    @GetMapping("/owner/getData")
    public String getData() throws Exception {
        return ownerService.getData(ownerAddress);
    }

    @GetMapping("/owner/setData")
    public void setData(@RequestParam String data) throws Exception {
        ownerService.setData(data ,ownerAddress);
    }

}
