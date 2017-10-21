package com.example.coinmarket.restconnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceSingleton {

    public static RestServiceSingleton getInstance() {
        if (instance == null) {
            instance = new RestServiceSingleton();
        }

        return instance;
    }

    // Build retrofit once when creating a single instance
    private RestServiceSingleton() {
        // Implement a method to build your retrofit
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Build your services once
        this.apiService = retrofit.create(RestService.class);

    }

    public RestService getApiService() {
        return this.apiService;
    }

    private static final String BASE_URL = "https://api.coinmarketcap.com";
    private static RestServiceSingleton instance = null;

    // Keep your services here, build them in buildRetrofit method later
    private RestService apiService;
}
