package com.kycblockchainservice.kycblockchainservice.config;

import com.kycblockchainservice.kycblockchainservice.blockchain.model.SmartContract;
import com.kycblockchainservice.kycblockchainservice.properties.TransactionProperties;
import com.kycblockchainservice.kycblockchainservice.service.OwnerService;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

@Configuration
public class SmartContractConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SmartContractConfiguration.class);

    @Value("${chain.contract.owner-address}")
    private String ownerAddress;

    @Value("${web3j.client-address}")
    private String clientAddress;

    @Autowired
    private TransactionProperties config;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(clientAddress, new OkHttpClient.Builder().build()));
    }

    @Bean
    public OwnerService contract(Web3j web3j, @Value("${chain.contract.address:}") String contractAddress)
            throws Exception {
        if (StringUtils.isEmpty(contractAddress)) {
            SmartContract contract = deployContract(web3j);
            return initOwnerService(contract.getContractAddress(), web3j);
        }

        return initOwnerService(contractAddress, web3j);
    }

    private OwnerService initOwnerService(String contractAddress, Web3j web3j) {
        return new OwnerService(contractAddress, web3j, config);
    }

    private SmartContract deployContract(Web3j web3j) throws Exception {
        LOG.info("About to deploy new contract...");
        SmartContract contract = SmartContract.deploy(web3j, txManager(web3j), config.gas()).send();
        LOG.info("Deployed new contract with address '{}'", contract.getContractAddress());
        return contract;
    }

    private TransactionManager txManager(Web3j web3j) {
        return new ClientTransactionManager(web3j, ownerAddress);
    }
}
