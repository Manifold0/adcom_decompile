// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import android.util.Log;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.sdk.gc;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import com.applovin.impl.sdk.ax;

public class AppLovinIncentivizedInterstitial
{
    private final ax a;
    
    public AppLovinIncentivizedInterstitial(final Context context) {
        this(AppLovinSdk.getInstance(context));
    }
    
    public AppLovinIncentivizedInterstitial(final AppLovinSdk appLovinSdk) {
        this(null, appLovinSdk);
    }
    
    public AppLovinIncentivizedInterstitial(final String s, final AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = this.createIncentivizedAdController(s, appLovinSdk);
    }
    
    public static AppLovinIncentivizedInterstitial create(final Context context) {
        return create(AppLovinSdk.getInstance(context));
    }
    
    public static AppLovinIncentivizedInterstitial create(final AppLovinSdk appLovinSdk) {
        return create(null, appLovinSdk);
    }
    
    public static AppLovinIncentivizedInterstitial create(final String s, final AppLovinSdk appLovinSdk) {
        return new AppLovinIncentivizedInterstitial(s, appLovinSdk);
    }
    
    protected ax createIncentivizedAdController(final String s, final AppLovinSdk appLovinSdk) {
        return new ax(s, appLovinSdk);
    }
    
    public void dismiss() {
        this.a.d();
    }
    
    public String getUserIdentifier() {
        return gc.a();
    }
    
    public String getZoneId() {
        return this.a.c();
    }
    
    public boolean isAdReadyToDisplay() {
        return this.a.a();
    }
    
    public void preload(final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener == null) {
            Log.i("AppLovinIncentivizedInterstitial", "AppLovinAdLoadListener was null when preloading incentivized interstitials; using a listener is highly recommended.");
        }
        this.a.a(appLovinAdLoadListener);
    }
    
    public void setUserIdentifier(final String s) {
        gc.a(s);
    }
    
    public void show(final Context context) {
        this.show(context, null, null);
    }
    
    public void show(final Context context, final AppLovinAdRewardListener appLovinAdRewardListener) {
        this.show(context, appLovinAdRewardListener, null);
    }
    
    public void show(final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.show(context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, null);
    }
    
    public void show(final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.show(context, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, null);
    }
    
    public void show(final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        this.show(context, null, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }
    
    @Deprecated
    public void show(final Context context, final String s, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        this.a.a(null, context, s, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }
    
    public void show(final AppLovinAd appLovinAd, final Context context, final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAdClickListener appLovinAdClickListener) {
        this.a.a(appLovinAd, context, null, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }
}
