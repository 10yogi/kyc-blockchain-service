package com.kycblockchainservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
@ConfigurationProperties(prefix = "chain.contract")
@Getter
@Setter
public class TransactionProperties {
    private BigInteger gasPrice;
    private BigInteger gasLimit;

    public ContractGasProvider gas() {
        return new StaticGasProvider(gasPrice, gasLimit);
    }
}
