package com.example.coinmarket.restconnection;


import com.example.coinmarket.restconnection.response.CryptoCurrency;

import java.util.List;

import retrofit2.http.GET;

public interface RestService {

    /*@GET()
    retrofit2.Call<CryptoCurrencyResponse> getCurrencyData(@Field("convert") String convert,
                                                       @Field("limit") int limit);*/
    @GET("/v1/ticker/?convert=EUR&limit=100")
    retrofit2.Call<List<CryptoCurrency>> getCurrencyData();


    /** Important Server Urls */
    String dataURl = "https://api.coinmarketcap.com/v1/ticker/";


}
