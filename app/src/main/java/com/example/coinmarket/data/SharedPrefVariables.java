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
        if (currency == null) {
            editor.putString(ACCESS_CURRENCY, "USD");
        } else {
            editor.putString(ACCESS_CURRENCY, currency);
        }
        editor.apply();
    }

    public static String getCurrencyFromSharedPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("server", Context.MODE_PRIVATE);
        String currency = prefs.getString(ACCESS_CURRENCY, "USD");
        return currency;
    }

    private static final String ACCESS_CURRENCY = "currency";

}
