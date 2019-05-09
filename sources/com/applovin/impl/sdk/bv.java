package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class bv {
    /* renamed from: a */
    public static void m2399a(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cg(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2400a(AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdClickListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cd(appLovinAdClickListener, appLovinAd, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2401a(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bw(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2402a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, int i, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ca(appLovinAdRewardListener, appLovinAd, i, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2403a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cb(appLovinAdRewardListener, appLovinAd, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2404a(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bx(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2405a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, double d, boolean z, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cf(appLovinAdVideoPlaybackListener, appLovinAd, d, z, appLovinSdk));
        }
    }

    /* renamed from: a */
    public static void m2406a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdVideoPlaybackListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ce(appLovinAdVideoPlaybackListener, appLovinAd, appLovinSdk));
        }
    }

    /* renamed from: b */
    private static AppLovinAd m2407b(AppLovinAd appLovinAd) {
        C1227q c1227q = (C1227q) appLovinAd;
        return c1227q.m1785u() != null ? c1227q.m1785u() : appLovinAd;
    }

    /* renamed from: b */
    public static void m2408b(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ch(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    /* renamed from: b */
    public static void m2409b(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdDisplayListener != null) {
            AppLovinSdkUtils.runOnUiThread(new cc(appLovinAdDisplayListener, appLovinAd, appLovinSdk));
        }
    }

    /* renamed from: b */
    public static void m2410b(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new by(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }

    /* renamed from: c */
    public static void m2411c(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdViewEventListener != null) {
            AppLovinSdkUtils.runOnUiThread(new ci(appLovinAdViewEventListener, appLovinAd, appLovinAdView, appLovinSdk));
        }
    }

    /* renamed from: c */
    public static void m2412c(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map<String, String> map, AppLovinSdk appLovinSdk) {
        if (appLovinAd != null && appLovinAdRewardListener != null) {
            AppLovinSdkUtils.runOnUiThread(new bz(appLovinAdRewardListener, appLovinAd, map, appLovinSdk));
        }
    }
}
