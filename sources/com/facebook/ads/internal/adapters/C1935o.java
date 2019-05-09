package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;

/* renamed from: com.facebook.ads.internal.adapters.o */
public class C1935o extends BroadcastReceiver {
    /* renamed from: a */
    private String f4211a;
    /* renamed from: b */
    private Context f4212b;
    /* renamed from: c */
    private InterstitialAdapterListener f4213c;
    /* renamed from: d */
    private C1918g f4214d;

    public C1935o(Context context, String str, C1918g c1918g, InterstitialAdapterListener interstitialAdapterListener) {
        this.f4212b = context;
        this.f4211a = str;
        this.f4213c = interstitialAdapterListener;
        this.f4214d = c1918g;
    }

    /* renamed from: a */
    public void m4566a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + this.f4211a);
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.f4211a);
        intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + this.f4211a);
        intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + this.f4211a);
        intentFilter.addAction("com.facebook.ads.interstitial.error:" + this.f4211a);
        intentFilter.addAction("com.facebook.ads.interstitial.activity_destroyed:" + this.f4211a);
        LocalBroadcastManager.getInstance(this.f4212b).registerReceiver(this, intentFilter);
    }

    /* renamed from: b */
    public void m4567b() {
        try {
            LocalBroadcastManager.getInstance(this.f4212b).unregisterReceiver(this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if (this.f4213c != null && obj != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(obj)) {
                this.f4213c.onInterstitialAdClicked(this.f4214d, null, true);
            } else if ("com.facebook.ads.interstitial.dismissed".equals(obj)) {
                this.f4213c.onInterstitialAdDismissed(this.f4214d);
            } else if ("com.facebook.ads.interstitial.displayed".equals(obj)) {
                this.f4213c.onInterstitialAdDisplayed(this.f4214d);
            } else if ("com.facebook.ads.interstitial.impression.logged".equals(obj)) {
                this.f4213c.onInterstitialLoggingImpression(this.f4214d);
            } else if ("com.facebook.ads.interstitial.error".equals(obj)) {
                this.f4213c.onInterstitialError(this.f4214d, AdError.INTERNAL_ERROR);
            } else if ("com.facebook.ads.interstitial.activity_destroyed".equals(obj)) {
                this.f4213c.onInterstitialActivityDestroyed();
            }
        }
    }
}
