package com.kycblockchainservice.kycblockchainservice.blockchain.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class SmartContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610890380380610890833981016040528051602082015190820191016000805b835182101561008e576001806000868581518110151561005057fe5b602090810291909101810151600160a060020a03168252810191909152604001600020805460ff191691151591909117905560019190910190610034565b5060005b82518110156100e95760016002600085848151811015156100af57fe5b602090810291909101810151600160a060020a03168252810191909152604001600020805460ff1916911515919091179055600101610092565b50505050610794806100fc6000396000f3006080604052600436106100565763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630fade719811461005b57806364c517ad14610102578063caf8340a14610123575b600080fd5b34801561006757600080fd5b5060408051602060046024803582810135601f8101859004850286018501909652858552610100958335600160a060020a031695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506102229650505050505050565b005b34801561010e57600080fd5b50610100600160a060020a0360043516610353565b34801561012f57600080fd5b50610144600160a060020a03600435166103ee565b604051808060200180602001838103835285818151815260200191508051906020019080838360005b8381101561018557818101518382015260200161016d565b50505050905090810190601f1680156101b25780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156101e55781810151838201526020016101cd565b50505050905090810190601f1680156102125780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b3360009081526001602052604090205460ff1615156102b1576040805160e560020a62461bcd02815260206004820152602160248201527f62616e6b206973206e6f7420617574686f72697a656420746f20616464206b7960448201527f6300000000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b600160a060020a038316600090815260036020908152604090912083516102da928501906106cd565b50600160a060020a0383166000908152600360209081526040909120825161030a926001909201918401906106cd565b505050600160a060020a031660009081526003602090815260408083206002018054600160ff199182168117909255848452828520338652909352922080549091169091179055565b3360009081526003602052604090206002015460ff1615156103bf576040805160e560020a62461bcd02815260206004820152601a60248201527f6b796320646f65736e277420657869737420666f722075736572000000000000604482015290519081900360640190fd5b33600090815260208181526040808320600160a060020a0394909416835292905220805460ff19166001179055565b33600090815260026020526040902054606090819060ff161515610481576040805160e560020a62461bcd028152602060048201526024808201527f62616e6b20697320617574686f72697a656420746f206163636573206b79632060448201527f6461746100000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b600160a060020a03831660009081526020818152604080832033845290915290205460ff161515610522576040805160e560020a62461bcd02815260206004820152602260248201527f7265717569726564206b79632076696577206163636573732066726f6d20757360448201527f6572000000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b3360009081526003602052604090206002015460ff16151561058e576040805160e560020a62461bcd02815260206004820181905260248201527f76616c6964206b796320646f65736e277420657869737420666f722075736572604482015290519081900360640190fd5b600160a060020a038316600090815260036020908152604091829020805483516002600180841615610100026000190190931604601f810185900485028201850190955284815291939084019284919083018282801561062f5780601f106106045761010080835404028352916020019161062f565b820191906000526020600020905b81548152906001019060200180831161061257829003601f168201915b5050845460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959750869450925084019050828280156106bd5780601f10610692576101008083540402835291602001916106bd565b820191906000526020600020905b8154815290600101906020018083116106a057829003601f168201915b5050505050905091509150915091565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061070e57805160ff191683800117855561073b565b8280016001018555821561073b579182015b8281111561073b578251825591602001919060010190610720565b5061074792915061074b565b5090565b61076591905b808211156107475760008155600101610751565b905600a165627a7a72305820e0fd2e88dbf28c4a3da543943c1bda9fe72288b46796ca8f1636d58103d93bdf0029";

    public static final String FUNC_ADDKYC = "addKyc";

    public static final String FUNC_GIVEKYCDATAACCESS = "giveKycDataAccess";

    public static final String FUNC_GETKYC = "getKyc";

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> addKyc(String user, String certificate, String docAccessToken) {
        final Function function = new Function(
                FUNC_ADDKYC, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user), 
                new org.web3j.abi.datatypes.Utf8String(certificate), 
                new org.web3j.abi.datatypes.Utf8String(docAccessToken)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> giveKycDataAccess(String bank) {
        final Function function = new Function(
                FUNC_GIVEKYCDATAACCESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(bank)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<String, String>> getKyc(String user) {
        final Function function = new Function(FUNC_GETKYC, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<String, String>>(
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, List<String> addKycBankAddresses, List<String> getKycBankAddresses) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(addKycBankAddresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(getKycBankAddresses, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(SmartContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SmartContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, List<String> addKycBankAddresses, List<String> getKycBankAddresses) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(addKycBankAddresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(getKycBankAddresses, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<String> addKycBankAddresses, List<String> getKycBankAddresses) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(addKycBankAddresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(getKycBankAddresses, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(SmartContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SmartContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<String> addKycBankAddresses, List<String> getKycBankAddresses) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(addKycBankAddresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(getKycBankAddresses, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(SmartContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SmartContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SmartContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
