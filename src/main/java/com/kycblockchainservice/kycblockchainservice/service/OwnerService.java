package com.kycblockchainservice.kycblockchainservice.service;

import com.kycblockchainservice.kycblockchainservice.blockchain.model.SmartContract;
import com.kycblockchainservice.kycblockchainservice.properties.TransactionProperties;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

import java.io.IOException;
import java.math.BigInteger;

public class OwnerService {

    private final String contractAddress;
    private final Web3j web3j;
    private final TransactionProperties config;

    public OwnerService(String contractAddress, Web3j web3j, TransactionProperties config) {
        this.contractAddress = contractAddress;
        this.web3j = web3j;
        this.config = config;
    }

    public BigInteger getBalance() throws IOException {
        return web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST).send().getBalance();
    }

    public String getData(String ownerAddress) throws Exception {
        SmartContract contract = loadContract(ownerAddress);
        return contract.getStoredData().send();
    }

    public void setData(String data ,String ownerAddress) throws Exception {
        SmartContract contract = loadContract(ownerAddress);
        contract.setStoredData(data).send();
    }

    private SmartContract loadContract(String accountAddress) {
        return SmartContract.load(contractAddress, web3j, txManager(accountAddress), config.gas());
    }

    private TransactionManager txManager(String accountAddress) {
        return new ClientTransactionManager(web3j, accountAddress);
    }
}
