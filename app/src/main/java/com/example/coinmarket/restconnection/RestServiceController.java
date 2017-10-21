package com.example.coinmarket.restconnection;

import android.os.Looper;

import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.coinmarket.restconnection.response.CryptoCurrencyResponse;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RestServiceController {

    /**
     * Server API authentication
     * @return response: apiToken in String
     * {"Status":"success","ApiToken":"android_59a2d5be738730.31286605"}
     * */
    public void getCurrencyData(String currency) {
        try {
            RestService service = RestServiceSingleton.getInstance().getApiService();
            service.getCurrencyData().enqueue(callback);
        } catch (Exception ex) {
            System.out.println("Error has occured:"+ex.getMessage());
        }
    }

    Callback<List<CryptoCurrency>> callback = new Callback<List<CryptoCurrency>>() {
        @Override
        public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
            if(response.isSuccessful()) {
                System.out.print("test");
                Looper test = Looper.getMainLooper();
            } else {
                // TODO: Logging
            }
        }
        @Override
        public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {
            System.out.print("test");
        }
    };
}
