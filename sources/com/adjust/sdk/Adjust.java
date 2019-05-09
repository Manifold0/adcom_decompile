package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;

public class Adjust {
    private static AdjustInstance defaultInstance;

    private Adjust() {
    }

    public static synchronized AdjustInstance getDefaultInstance() {
        AdjustInstance adjustInstance;
        synchronized (Adjust.class) {
            if (defaultInstance == null) {
                defaultInstance = new AdjustInstance();
            }
            adjustInstance = defaultInstance;
        }
        return adjustInstance;
    }

    public static void onCreate(AdjustConfig adjustConfig) {
        getDefaultInstance().onCreate(adjustConfig);
    }

    public static void trackEvent(AdjustEvent event) {
        getDefaultInstance().trackEvent(event);
    }

    public static void onResume() {
        getDefaultInstance().onResume();
    }

    public static void onPause() {
        getDefaultInstance().onPause();
    }

    public static void setEnabled(boolean enabled) {
        getDefaultInstance().setEnabled(enabled);
    }

    public static boolean isEnabled() {
        return getDefaultInstance().isEnabled();
    }

    public static void appWillOpenUrl(Uri url) {
        getDefaultInstance().appWillOpenUrl(url);
    }

    public static void setReferrer(String referrer, Context context) {
        getDefaultInstance().sendReferrer(referrer, context);
    }

    public static void setOfflineMode(boolean enabled) {
        getDefaultInstance().setOfflineMode(enabled);
    }

    public static void sendFirstPackages() {
        getDefaultInstance().sendFirstPackages();
    }

    public static void addSessionCallbackParameter(String key, String value) {
        getDefaultInstance().addSessionCallbackParameter(key, value);
    }

    public static void addSessionPartnerParameter(String key, String value) {
        getDefaultInstance().addSessionPartnerParameter(key, value);
    }

    public static void removeSessionCallbackParameter(String key) {
        getDefaultInstance().removeSessionCallbackParameter(key);
    }

    public static void removeSessionPartnerParameter(String key) {
        getDefaultInstance().removeSessionPartnerParameter(key);
    }

    public static void resetSessionCallbackParameters() {
        getDefaultInstance().resetSessionCallbackParameters();
    }

    public static void resetSessionPartnerParameters() {
        getDefaultInstance().resetSessionPartnerParameters();
    }

    public static void setPushToken(String token) {
        getDefaultInstance().setPushToken(token);
    }

    public static void setPushToken(String token, Context context) {
        getDefaultInstance().setPushToken(token, context);
    }

    public static void getGoogleAdId(Context context, OnDeviceIdsRead onDeviceIdRead) {
        Util.getGoogleAdId(context, onDeviceIdRead);
    }

    public static String getAmazonAdId(Context context) {
        return Util.getFireAdvertisingId(context.getContentResolver());
    }

    public static String getAdid() {
        return getDefaultInstance().getAdid();
    }

    public static AdjustAttribution getAttribution() {
        return getDefaultInstance().getAttribution();
    }
}
