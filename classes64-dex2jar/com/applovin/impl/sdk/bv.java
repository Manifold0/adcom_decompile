// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.util.Map;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;

public class bv
{
    public static void a(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cg(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdClickListener appLovinAdClickListener, final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdClickListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cd(appLovinAdClickListener, appLovinAd, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bw(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final int n, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ca(appLovinAdRewardListener, appLovinAd, n, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cb(appLovinAdRewardListener, appLovinAd, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bx(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAd appLovinAd, final double n, final boolean b, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cf(appLovinAdVideoPlaybackListener, appLovinAd, n, b, appLovinSdk));
        }
    }
    
    public static void a(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ce(appLovinAdVideoPlaybackListener, appLovinAd, appLovinSdk));
        }
    }
    
    private static AppLovinAd b(AppLovinAd u) {
        final q q = (q)u;
        if (q.u() != null) {
            u = q.u();
        }
        return u;
    }
    
    public static void b(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ch(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }
    
    public static void b(final AppLovinAdDisplayListener appLovinAdDisplayListener, final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cc(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }
    
    public static void b(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new by(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }
    
    public static void c(final AppLovinAdViewEventListener appLovinAdViewEventListener, final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ci(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }
    
    public static void c(final AppLovinAdRewardListener appLovinAdRewardListener, final AppLovinAd appLovinAd, final Map<String, String> map, final AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bz(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }
}
