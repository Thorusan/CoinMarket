package com.example.coinmarket.restconnection;


import com.example.coinmarket.restconnection.response.CryptoCurrency;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    /**
     * Important Server Urls
     */
    String dataURl = "https://api.coinmarketcap.com/v1/ticker/";

    @GET("/v1/ticker/")
    retrofit2.Call<List<CryptoCurrency>> getCurrencyData
    (@Query("convert") String convert,
     @Query("limit") int limit);


}
