pragma solidity ^0.4.25;

contract smartContract {
    string storedData;

    function setStoredData(string x) public {
        storedData = x;
    }

    function getStoredData() public view returns (string) {
        return storedData;
    }
}
