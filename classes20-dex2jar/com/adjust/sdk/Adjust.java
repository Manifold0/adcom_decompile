// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;

public class Adjust
{
    private static AdjustInstance defaultInstance;
    
    private Adjust() {
    }
    
    public static void addSessionCallbackParameter(final String s, final String s2) {
        getDefaultInstance().addSessionCallbackParameter(s, s2);
    }
    
    public static void addSessionPartnerParameter(final String s, final String s2) {
        getDefaultInstance().addSessionPartnerParameter(s, s2);
    }
    
    public static void appWillOpenUrl(final Uri uri) {
        getDefaultInstance().appWillOpenUrl(uri);
    }
    
    public static String getAdid() {
        return getDefaultInstance().getAdid();
    }
    
    public static String getAmazonAdId(final Context context) {
        return Util.getFireAdvertisingId(context.getContentResolver());
    }
    
    public static AdjustAttribution getAttribution() {
        return getDefaultInstance().getAttribution();
    }
    
    public static AdjustInstance getDefaultInstance() {
        synchronized (Adjust.class) {
            if (Adjust.defaultInstance == null) {
                Adjust.defaultInstance = new AdjustInstance();
            }
            return Adjust.defaultInstance;
        }
    }
    
    public static void getGoogleAdId(final Context context, final OnDeviceIdsRead onDeviceIdsRead) {
        Util.getGoogleAdId(context, onDeviceIdsRead);
    }
    
    public static boolean isEnabled() {
        return getDefaultInstance().isEnabled();
    }
    
    public static void onCreate(final AdjustConfig adjustConfig) {
        getDefaultInstance().onCreate(adjustConfig);
    }
    
    public static void onPause() {
        getDefaultInstance().onPause();
    }
    
    public static void onResume() {
        getDefaultInstance().onResume();
    }
    
    public static void removeSessionCallbackParameter(final String s) {
        getDefaultInstance().removeSessionCallbackParameter(s);
    }
    
    public static void removeSessionPartnerParameter(final String s) {
        getDefaultInstance().removeSessionPartnerParameter(s);
    }
    
    public static void resetSessionCallbackParameters() {
        getDefaultInstance().resetSessionCallbackParameters();
    }
    
    public static void resetSessionPartnerParameters() {
        getDefaultInstance().resetSessionPartnerParameters();
    }
    
    public static void sendFirstPackages() {
        getDefaultInstance().sendFirstPackages();
    }
    
    public static void setEnabled(final boolean enabled) {
        getDefaultInstance().setEnabled(enabled);
    }
    
    public static void setOfflineMode(final boolean offlineMode) {
        getDefaultInstance().setOfflineMode(offlineMode);
    }
    
    public static void setPushToken(final String pushToken) {
        getDefaultInstance().setPushToken(pushToken);
    }
    
    public static void setPushToken(final String s, final Context context) {
        getDefaultInstance().setPushToken(s, context);
    }
    
    public static void setReferrer(final String s, final Context context) {
        getDefaultInstance().sendReferrer(s, context);
    }
    
    public static void trackEvent(final AdjustEvent adjustEvent) {
        getDefaultInstance().trackEvent(adjustEvent);
    }
}
