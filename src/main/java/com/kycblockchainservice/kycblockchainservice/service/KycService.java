package com.kycblockchainservice.kycblockchainservice.service;

import com.kycblockchainservice.kycblockchainservice.blockchain.model.AddKycRequestDto;
import com.kycblockchainservice.kycblockchainservice.blockchain.model.KycAccessRequestDto;
import com.kycblockchainservice.kycblockchainservice.blockchain.model.SmartContract;
import com.kycblockchainservice.kycblockchainservice.properties.TransactionProperties;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;""

import java.io.IOException;
import java.math.BigInteger;
import java.util.UUID;

public class KycService {

    private final String contractAddress;
    private final Web3j web3j;
    private final TransactionProperties config;

    public KycService(String contractAddress, Web3j web3j, TransactionProperties config) {
        this.contractAddress = contractAddress;
        this.web3j = web3j;
        this.config = config;
    }

    public BigInteger getBalance() throws IOException {
        return web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST).send().getBalance();
    }

    public TransactionReceipt addKyc(AddKycRequestDto addKycRequestDto , String ownerAddress) throws Exception {
        SmartContract contract = loadContract(ownerAddress);
        return contract.addKyc(addKycRequestDto.getCustomerAddress() ,
                getCertificate(addKycRequestDto.getCustomerAddress() + addKycRequestDto.getOtherDetails()) ,
                UUID.randomUUID().toString()).send();
    }

    public Tuple2<String, String> getKyc(String userAddress , String ownerAddress) throws Exception {
        SmartContract contract = loadContract(userAddress);
        return contract.getKyc(userAddress).send();
    }

    public void kycAccess(String bankAddress, String ownerAddress) throws Exception {
        SmartContract contract = loadContract(ownerAddress);
        contract.giveKycDataAccess(bankAddress);
    }

    private SmartContract loadContract(String accountAddress) {
        return SmartContract.load(contractAddress, web3j, txManager(accountAddress), config.gas());
    }

    private TransactionManager txManager(String accountAddress) {
        return new ClientTransactionManager(web3j, accountAddress);
    }

    private String getCertificate(String address){
        return "certificate" + address;
    }
}
