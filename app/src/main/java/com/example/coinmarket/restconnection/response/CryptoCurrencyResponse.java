package com.example.coinmarket.restconnection.response;


import java.util.List;

public class CryptoCurrencyResponse {
    public List<CryptoCurrency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CryptoCurrency> currencyList) {
        this.currencyList = currencyList;
    }

    private List<CryptoCurrency> currencyList;
}
