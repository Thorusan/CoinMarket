package com.example.coinmarket.restconnection;

import com.example.coinmarket.restconnection.response.CryptoCurrency;

import java.util.List;


public interface RestDataCallback {
    void passCurrencyDataAndSetAdapter(List<CryptoCurrency> data);
}
