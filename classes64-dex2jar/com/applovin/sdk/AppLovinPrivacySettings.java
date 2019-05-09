// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.ac;
import android.content.Context;

public class AppLovinPrivacySettings
{
    public static boolean hasUserConsent(final Context context) {
        final Boolean a = ac.a(context);
        return a != null && a;
    }
    
    public static boolean isAgeRestrictedUser(final Context context) {
        final Boolean b = ac.b(context);
        return b != null && b;
    }
    
    public static void setHasUserConsent(final boolean b, final Context context) {
        if (ac.a(b, context)) {
            AppLovinSdkImpl.reinitializeAll();
        }
    }
    
    public static void setIsAgeRestrictedUser(final boolean b, final Context context) {
        if (ac.b(b, context)) {
            AppLovinSdkImpl.reinitializeAll();
        }
    }
}
