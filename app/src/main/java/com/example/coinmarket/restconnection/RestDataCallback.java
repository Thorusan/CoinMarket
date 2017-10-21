package com.example.coinmarket.restconnection;

import com.example.coinmarket.restconnection.response.CryptoCurrency;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Thorus on 21. 10. 2017.
 */

public interface RestDataCallback {
   void passCurrencyDataAndSetAdapter(List<CryptoCurrency> data);
}
