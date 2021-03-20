package com.kycblockchainservice.kycblockchainservice.rest;

import com.kycblockchainservice.kycblockchainservice.blockchain.model.AddKycRequestDto;
import com.kycblockchainservice.kycblockchainservice.blockchain.model.KycAccessRequestDto;
import com.kycblockchainservice.kycblockchainservice.service.KycService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

@RestController
public class KycController {

    @Value("${chain.contract.icici-address}")
    private String iciciAddress;

    @Value("${chain.contract.customer-address}")
    private String customerAddress;

    @Value("${chain.contract.indus-address}")
    private String indusAddress;

    @Value("${chain.contract.sbi-address}")
    private String sbiAddress;

    @Autowired
    private Web3j web3j;

    @Autowired
    private KycService kycService;

    @PostMapping("/icici/kyc/add")
    public TransactionReceipt addKyc(@RequestBody AddKycRequestDto addKycRequestDto) throws Exception {
        try {
            return kycService.addKyc(addKycRequestDto, iciciAddress);
        } catch (final Exception e) {
            throw new RuntimeException("forbidden failed to fetch kyc" + e.getMessage());
        }
    }

    @GetMapping("/indus/kyc/get")
    public Tuple2<String, String> getKyc(@RequestParam String customerAddress) throws Exception {
        try {
            return kycService.getKyc(customerAddress, indusAddress);
        } catch (final Exception e) {
            throw new RuntimeException("forbidden failed to fetch kyc" + e.getMessage());
        }
    }

    @PostMapping("/customer/kyc/access")
    public void kycAccess(@RequestBody KycAccessRequestDto kycAccessRequestDto) {
        try {
            switch (kycAccessRequestDto.getBankName()){
                case "INDUS":
                    kycService.kycAccess(indusAddress, customerAddress);
                case "ICICI":
                    kycService.kycAccess(iciciAddress, customerAddress);
                case "SBI":
                    kycService.kycAccess(sbiAddress, customerAddress);
            }
        } catch (final Exception e) {
            throw new RuntimeException("access approval failed" + e.getMessage());
        }
    }

}
