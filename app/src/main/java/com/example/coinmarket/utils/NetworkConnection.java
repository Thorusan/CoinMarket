package com.example.coinmarket.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Method;

import timber.log.Timber;

/**
 * Util razred za sporočanje statusa povezave
 */
public class NetworkConnection {

    public static boolean isNetworkAvailable(Context context) {
        return (isWifiConnected(context) || isMobileDataConnectionEnabled(context));
    }

    /**
     * Check if Wifi connected
     *
     * @param context
     * @return true - wifi on, false - wifi off
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    /**
     * Check if mobile data connection is on
     *
     * @param context
     * @return true - MobileData on, MobileData - wifi off
     */
    public static boolean isMobileDataConnectionEnabled(Context context) {
        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API
            Timber.e(e, e.getMessage());
        }
        return mobileDataEnabled;
    }


}
