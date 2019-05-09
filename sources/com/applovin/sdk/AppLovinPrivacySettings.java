package com.applovin.sdk;

import android.content.Context;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.ac;

public class AppLovinPrivacySettings {
    public static boolean hasUserConsent(Context context) {
        Boolean a = ac.m2209a(context);
        return a != null ? a.booleanValue() : false;
    }

    public static boolean isAgeRestrictedUser(Context context) {
        Boolean b = ac.m2213b(context);
        return b != null ? b.booleanValue() : false;
    }

    public static void setHasUserConsent(boolean z, Context context) {
        if (ac.m2212a(z, context)) {
            AppLovinSdkImpl.reinitializeAll();
        }
    }

    public static void setIsAgeRestrictedUser(boolean z, Context context) {
        if (ac.m2214b(z, context)) {
            AppLovinSdkImpl.reinitializeAll();
        }
    }
}
