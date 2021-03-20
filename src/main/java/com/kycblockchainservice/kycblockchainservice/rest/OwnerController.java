package com.kycblockchainservice.kycblockchainservice.rest;

import com.kycblockchainservice.kycblockchainservice.blockchain.model.AddKycRequestDto;
import com.kycblockchainservice.kycblockchainservice.blockchain.model.KycAccessRequestDto;
import com.kycblockchainservice.kycblockchainservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

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


    @PostMapping("/owner/kyc/add")
    public TransactionReceipt addKyc(@RequestBody AddKycRequestDto addKycRequestDto) throws Exception {
        return ownerService.addKyc(addKycRequestDto ,ownerAddress);
    }

    @GetMapping("/owner/kyc/get")
    public Tuple2<String, String> getKyc(@RequestParam String userAddress) throws Exception {
        return ownerService.getKyc(userAddress ,ownerAddress);
    }

    @PostMapping("/owner/kyc/access")
    public void kycAccess(@RequestBody KycAccessRequestDto kycAccessRequestDto) throws Exception {
        ownerService.kycAccess(kycAccessRequestDto ,ownerAddress);
    }

}
