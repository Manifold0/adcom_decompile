// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import android.util.Log;
import com.applovin.impl.sdk.gd;
import android.content.Context;

public class AppLovinSdkSettings
{
    private boolean a;
    private boolean b;
    private long c;
    private String d;
    private String e;
    private boolean f;
    
    public AppLovinSdkSettings() {
        this(null);
    }
    
    protected AppLovinSdkSettings(final Context context) {
        this.b = gd.c(context);
        this.a = gd.b(context);
        this.c = -1L;
        this.d = AppLovinAdSize.INTERSTITIAL.getLabel() + "," + AppLovinAdSize.BANNER.getLabel() + "," + AppLovinAdSize.MREC.getLabel();
        this.e = AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.NATIVE.getLabel();
    }
    
    @Deprecated
    public String getAutoPreloadSizes() {
        return this.d;
    }
    
    @Deprecated
    public String getAutoPreloadTypes() {
        return this.e;
    }
    
    public long getBannerAdRefreshSeconds() {
        return this.c;
    }
    
    public boolean isMuted() {
        return this.f;
    }
    
    public boolean isTestAdsEnabled() {
        return this.a;
    }
    
    public boolean isVerboseLoggingEnabled() {
        return this.b;
    }
    
    @Deprecated
    public void setAutoPreloadSizes(final String d) {
        this.d = d;
    }
    
    @Deprecated
    public void setAutoPreloadTypes(final String e) {
        this.e = e;
    }
    
    public void setBannerAdRefreshSeconds(final long c) {
        this.c = c;
    }
    
    public void setMuted(final boolean f) {
        this.f = f;
    }
    
    public void setTestAdsEnabled(final boolean a) {
        this.a = a;
    }
    
    public void setVerboseLogging(final boolean b) {
        if (gd.a()) {
            Log.e("AppLovinSdk", "[AppLovinSdkSettings] Ignoring setting of verbose logging - it is configured from Android manifest already or AppLovinSdkSettings was initialized without a context.");
            return;
        }
        this.b = b;
    }
}
