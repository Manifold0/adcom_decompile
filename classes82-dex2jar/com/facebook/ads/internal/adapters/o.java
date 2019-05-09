// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;

public class o extends BroadcastReceiver
{
    private String a;
    private Context b;
    private InterstitialAdapterListener c;
    private g d;
    
    public o(final Context b, final String a, final g d, final InterstitialAdapterListener c) {
        this.b = b;
        this.a = a;
        this.c = c;
        this.d = d;
    }
    
    public void a() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.error:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.activity_destroyed:" + this.a);
        LocalBroadcastManager.getInstance(this.b).registerReceiver((BroadcastReceiver)this, intentFilter);
    }
    
    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.b).unregisterReceiver((BroadcastReceiver)this);
        }
        catch (Exception ex) {}
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String s = intent.getAction().split(":")[0];
        if (this.c != null && s != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(s)) {
                this.c.onInterstitialAdClicked(this.d, null, true);
                return;
            }
            if ("com.facebook.ads.interstitial.dismissed".equals(s)) {
                this.c.onInterstitialAdDismissed(this.d);
                return;
            }
            if ("com.facebook.ads.interstitial.displayed".equals(s)) {
                this.c.onInterstitialAdDisplayed(this.d);
                return;
            }
            if ("com.facebook.ads.interstitial.impression.logged".equals(s)) {
                this.c.onInterstitialLoggingImpression(this.d);
                return;
            }
            if ("com.facebook.ads.interstitial.error".equals(s)) {
                this.c.onInterstitialError(this.d, AdError.INTERNAL_ERROR);
                return;
            }
            if ("com.facebook.ads.interstitial.activity_destroyed".equals(s)) {
                this.c.onInterstitialActivityDestroyed();
            }
        }
    }
}
