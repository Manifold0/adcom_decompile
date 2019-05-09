// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import com.applovin.nativeAds.AppLovinNativeAdService;
import android.util.Log;
import com.applovin.impl.sdk.AppLovinInternalSdkSettings;
import android.content.Context;
import com.applovin.impl.sdk.AppLovinSdkImpl;

public abstract class AppLovinSdk
{
    public static final String URI_HOST = "com.applovin.sdk";
    public static final String URI_SCHEME = "applovin";
    public static final String VERSION = "8.0.1";
    public static final int VERSION_CODE = 801;
    protected static AppLovinSdkImpl[] sdkInstances;
    protected static final Object sdkInstancesLock;
    
    static {
        AppLovinSdk.sdkInstances = new AppLovinSdkImpl[0];
        sdkInstancesLock = new Object();
    }
    
    public static AppLovinSdk getInstance(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return getInstance(AppLovinSdkUtils.retrieveSdkKey(context), new AppLovinInternalSdkSettings(context), context);
    }
    
    public static AppLovinSdk getInstance(final AppLovinSdkSettings appLovinSdkSettings, final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return getInstance(AppLovinSdkUtils.retrieveSdkKey(context), appLovinSdkSettings, context);
    }
    
    public static AppLovinSdk getInstance(final String s, final AppLovinSdkSettings appLovinSdkSettings, final Context context) {
        if (appLovinSdkSettings == null) {
            throw new IllegalArgumentException("No userSettings specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        while (true) {
            while (true) {
                int n;
                synchronized (AppLovinSdk.sdkInstancesLock) {
                    if (AppLovinSdk.sdkInstances.length == 1 && AppLovinSdk.sdkInstances[0].getSdkKey().equals(s)) {
                        return AppLovinSdk.sdkInstances[0];
                    }
                    final AppLovinSdkImpl[] sdkInstances = AppLovinSdk.sdkInstances;
                    final int length = sdkInstances.length;
                    n = 0;
                    if (n >= length) {
                        break;
                    }
                    final AppLovinSdkImpl appLovinSdkImpl = sdkInstances[n];
                    if (appLovinSdkImpl.getSdkKey().equals(s)) {
                        return appLovinSdkImpl;
                    }
                }
                ++n;
                continue;
            }
        }
        try {
            final AppLovinSdkImpl appLovinSdkImpl2 = new AppLovinSdkImpl();
            appLovinSdkImpl2.initialize(s, appLovinSdkSettings, context);
            appLovinSdkImpl2.setInitializedInMainActivity(appLovinSdkImpl2.checkCorrectInitialization(context));
            final AppLovinSdkImpl[] sdkInstances2 = new AppLovinSdkImpl[AppLovinSdk.sdkInstances.length + 1];
            System.arraycopy(AppLovinSdk.sdkInstances, 0, sdkInstances2, 0, AppLovinSdk.sdkInstances.length);
            sdkInstances2[AppLovinSdk.sdkInstances.length] = appLovinSdkImpl2;
            AppLovinSdk.sdkInstances = sdkInstances2;
            // monitorexit(o)
            return appLovinSdkImpl2;
        }
        catch (Throwable t) {
            Log.e("AppLovinSdk", "Failed to build AppLovin SDK. Try cleaning application data and starting the application again.", t);
            throw new RuntimeException("Unable to build AppLovin SDK");
        }
    }
    
    public static void initializeSdk(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        final AppLovinSdk instance = getInstance(context);
        if (instance != null) {
            instance.initializeSdk();
            return;
        }
        Log.e("AppLovinSdk", "Unable to initialize AppLovin SDK: SDK object not created");
    }
    
    public abstract AppLovinAdService getAdService();
    
    public abstract Context getApplicationContext();
    
    public abstract AppLovinEventService getEventService();
    
    public abstract AppLovinLogger getLogger();
    
    public abstract String getMediationProvider();
    
    public abstract AppLovinMediationService getMediationService();
    
    public abstract AppLovinNativeAdService getNativeAdService();
    
    public abstract AppLovinPostbackService getPostbackService();
    
    public abstract String getSdkKey();
    
    public abstract AppLovinSdkSettings getSettings();
    
    public abstract String getUserIdentifier();
    
    public abstract boolean hasCriticalErrors();
    
    public abstract void initialize(final String p0, final AppLovinSdkSettings p1, final Context p2);
    
    public abstract void initializeSdk();
    
    public abstract boolean isEnabled();
    
    public abstract void setMediationProvider(final String p0);
    
    public abstract void setPluginVersion(final String p0);
    
    public abstract void setUserIdentifier(final String p0);
}
