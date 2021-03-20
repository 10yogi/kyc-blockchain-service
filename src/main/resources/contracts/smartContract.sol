pragma solidity ^0.4.25;
/**
 * @title Ballot
 * @dev Implements voting process along with vote delegation
 */
contract SmartContract {

struct KycData {
string certificate;
string docAccessToken;
bool isKycValid;
}

mapping (address => mapping(address => bool)) kycViewAccess;
mapping (address => bool) authorizedBanksToAddKyc;
mapping (address => bool) authorizedBanksToGetKyc;
mapping (address => KycData)  userKycDataDict;

// pass bank account address while deploying contracts
constructor(address [] memory addKycBankAddresses, address [] memory getKycBankAddresses){
for(uint i = 0; i < addKycBankAddresses.length; ++i){
authorizedBanksToAddKyc[ addKycBankAddresses[i]] = true;
}
for(uint j = 0; j < getKycBankAddresses.length; ++j){
authorizedBanksToGetKyc[getKycBankAddresses[j]] = true;
}
}

function addKyc(address user,  string memory certificate, string memory docAccessToken) public {
require ( authorizedBanksToAddKyc[msg.sender], "bank is not authorized to add kyc");
userKycDataDict[user].certificate = certificate;
userKycDataDict[user].docAccessToken = docAccessToken;
userKycDataDict[user].isKycValid = true;
kycViewAccess[user][msg.sender] = true;
}

function giveKycDataAccess(address bank) public {
require (userKycDataDict[msg.sender].isKycValid, "kyc doesn't exist for user");
kycViewAccess[msg.sender][bank] = true;
}

function getKyc(address user) public view returns (string memory certificate, string memory docAccessToken) {
require ( authorizedBanksToGetKyc[msg.sender], "bank is authorized to acces kyc data");
require( kycViewAccess[user][msg.sender], "required kyc view access from user");
require (userKycDataDict[msg.sender].isKycValid, "valid kyc doesn't exist for user");
return (userKycDataDict[user].certificate, userKycDataDict[user].docAccessToken);

}
}