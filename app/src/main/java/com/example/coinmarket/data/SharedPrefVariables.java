package com.example.coinmarket.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Thorus on 14. 10. 2017.
 */

public class SharedPrefVariables {

    public static void storeCurrencyToSharedPreferences(Context context, String currency) {
        SharedPreferences sharedPref = context.getSharedPreferences("server", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ACCESS_CURRENCY, currency);
        editor.commit();
    }

    public static String getCurrencyFromSharedPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("server", Context.MODE_PRIVATE);
        String apiToken = prefs.getString(ACCESS_CURRENCY, null);
        return apiToken;
    }

    private static final String ACCESS_CURRENCY = "currency";

}
