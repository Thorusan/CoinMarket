package com.example.coinmarket.restconnection;

import android.app.Activity;

import com.example.coinmarket.restconnection.response.CryptoCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestServiceController {

    private final Activity activity;
    private RestDataCallback restDataCallback;

    public RestServiceController(Activity activity, RestDataCallback callback) {
         this.activity = activity;
         this.restDataCallback = callback;
     }

    /**
     * Server API authentication
     * @return response: apiToken in String
     * {"Status":"success","ApiToken":"android_59a2d5be738730.31286605"}
     * */
    public void getCurrencyDataAndSetList(String currency, int limit) {
        try {
            RestService service = RestServiceSingleton.getInstance().getApiService();
            service.getCurrencyData(currency, limit).enqueue(callback);
        } catch (Exception ex) {
            System.out.println("Error has occured:"+ex.getMessage());
        }
    }

    Callback<List<CryptoCurrency>> callback = new Callback<List<CryptoCurrency>>() {
        @Override
        public void onResponse(Call<List<CryptoCurrency>> call, final Response<List<CryptoCurrency>> response) {
            try {
                if(response.isSuccessful()) {
                    System.out.print("test");
                    //Looper test = Looper.getMainLooper(); // check if running un UI thread
                    restDataCallback.passCurrencyDataAndSetAdapter(response.body());
                    /*activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //TODO: update your UI
                            restDataCallback.passCurrencyDataAndSetAdapter(response);
                        }

                    });*/
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        @Override
        public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {
            System.out.print("test");
        }
    };


}
