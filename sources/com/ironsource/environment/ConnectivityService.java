package com.ironsource.environment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;

public class ConnectivityService {
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_WIFI = "wifi";

    public static String getConnectionType(Context context) {
        StringBuilder connectionType = new StringBuilder();
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            String typeName = activeNetwork.getTypeName();
            int typeId = activeNetwork.getType();
            if (typeId == 0) {
                connectionType.append(NETWORK_TYPE_3G);
            } else if (typeId == 1) {
                connectionType.append("wifi");
            } else {
                connectionType.append(typeName);
            }
        }
        return connectionType.toString();
    }

    public static boolean isConnectedWifi(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isConnectedMobile(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static String getNetworkOperator(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperator();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static int getNetworkMCC(Context context) {
        try {
            return context.getResources().getConfiguration().mcc;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static int getNetworkMNC(Context context) {
        try {
            return context.getResources().getConfiguration().mnc;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static String getSimOperator(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            if (tm != null) {
                return tm.getSimOperator();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static int getPhoneType(Context context) {
        int result = -1;
        try {
            result = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getPhoneType();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
