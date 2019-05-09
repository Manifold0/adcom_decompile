package com.applovin.sdk;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.gd;

public class AppLovinSdkSettings {
    /* renamed from: a */
    private boolean f1940a;
    /* renamed from: b */
    private boolean f1941b;
    /* renamed from: c */
    private long f1942c;
    /* renamed from: d */
    private String f1943d;
    /* renamed from: e */
    private String f1944e;
    /* renamed from: f */
    private boolean f1945f;

    public AppLovinSdkSettings() {
        this(null);
    }

    protected AppLovinSdkSettings(Context context) {
        this.f1941b = gd.m2954c(context);
        this.f1940a = gd.m2951b(context);
        this.f1942c = -1;
        this.f1943d = AppLovinAdSize.INTERSTITIAL.getLabel() + "," + AppLovinAdSize.BANNER.getLabel() + "," + AppLovinAdSize.MREC.getLabel();
        this.f1944e = AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.REGULAR.getLabel() + "," + AppLovinAdType.NATIVE.getLabel();
    }

    @Deprecated
    public String getAutoPreloadSizes() {
        return this.f1943d;
    }

    @Deprecated
    public String getAutoPreloadTypes() {
        return this.f1944e;
    }

    public long getBannerAdRefreshSeconds() {
        return this.f1942c;
    }

    public boolean isMuted() {
        return this.f1945f;
    }

    public boolean isTestAdsEnabled() {
        return this.f1940a;
    }

    public boolean isVerboseLoggingEnabled() {
        return this.f1941b;
    }

    @Deprecated
    public void setAutoPreloadSizes(String str) {
        this.f1943d = str;
    }

    @Deprecated
    public void setAutoPreloadTypes(String str) {
        this.f1944e = str;
    }

    public void setBannerAdRefreshSeconds(long j) {
        this.f1942c = j;
    }

    public void setMuted(boolean z) {
        this.f1945f = z;
    }

    public void setTestAdsEnabled(boolean z) {
        this.f1940a = z;
    }

    public void setVerboseLogging(boolean z) {
        if (gd.m2944a()) {
            Log.e(AppLovinLogger.SDK_TAG, "[AppLovinSdkSettings] Ignoring setting of verbose logging - it is configured from Android manifest already or AppLovinSdkSettings was initialized without a context.");
        } else {
            this.f1941b = z;
        }
    }
}
