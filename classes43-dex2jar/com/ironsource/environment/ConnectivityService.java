// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import android.telephony.TelephonyManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;

public class ConnectivityService
{
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    
    public static String getConnectionType(final Context context) {
        final StringBuilder sb = new StringBuilder();
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            final String typeName = activeNetworkInfo.getTypeName();
            final int type = activeNetworkInfo.getType();
            if (type == 0) {
                sb.append("3g");
            }
            else if (type == 1) {
                sb.append("wifi");
            }
            else {
                sb.append(typeName);
            }
        }
        return sb.toString();
    }
    
    public static int getNetworkMCC(final Context context) {
        try {
            return context.getResources().getConfiguration().mcc;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static int getNetworkMNC(final Context context) {
        try {
            return context.getResources().getConfiguration().mnc;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static String getNetworkOperator(final Context context) {
        try {
            return ((TelephonyManager)context.getSystemService("phone")).getNetworkOperator();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public static int getPhoneType(final Context context) {
        try {
            return ((TelephonyManager)context.getSystemService("phone")).getPhoneType();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static String getSimOperator(final Context context) {
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getSimOperator();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    public static boolean isConnected(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    public static boolean isConnectedMobile(final Context context) {
        final boolean b = false;
        final NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(0);
        boolean b2 = b;
        if (networkInfo != null) {
            b2 = b;
            if (networkInfo.isConnected()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public static boolean isConnectedWifi(final Context context) {
        final NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnected();
    }
}
