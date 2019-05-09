// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.i;

public class c
{
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final boolean h;
    
    private c(final int a, final String b, final String c, final String d, final String e, final String f, final boolean g, final boolean h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public static c a() {
        return new c(0, "interstitial", "interstitial", "/interstitial/get", "webview/%s/interstitial/get", "/interstitial/show", false, false);
    }
    
    public static c b() {
        return new c(1, "rewarded", "rewarded-video", "/reward/get", "webview/%s/reward/get", "/reward/show", true, false);
    }
    
    public static c c() {
        return new c(2, "inplay", null, "/inplay/get", "no webview endpoint", "/inplay/show", false, true);
    }
    
    public String a(final int n) {
        final String c = this.c;
        String s;
        if (n == 1) {
            s = "web";
        }
        else {
            s = "native";
        }
        return String.format("%s-%s", c, s);
    }
    
    public void a(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didClickInterstitial(s);
                }
                case 1: {
                    i.c.didClickRewardedVideo(s);
                }
            }
        }
    }
    
    public void a(final String s, final CBError.CBImpressionError cbImpressionError) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didFailToLoadInterstitial(s, cbImpressionError);
                }
                case 1: {
                    i.c.didFailToLoadRewardedVideo(s, cbImpressionError);
                }
                case 2: {
                    i.c.didFailToLoadInPlay(s, cbImpressionError);
                }
            }
        }
    }
    
    public void b(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didCloseInterstitial(s);
                }
                case 1: {
                    i.c.didCloseRewardedVideo(s);
                }
            }
        }
    }
    
    public void c(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didDismissInterstitial(s);
                }
                case 1: {
                    i.c.didDismissRewardedVideo(s);
                }
            }
        }
    }
    
    public void d(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didCacheInterstitial(s);
                }
                case 1: {
                    i.c.didCacheRewardedVideo(s);
                }
                case 2: {
                    i.c.didCacheInPlay(s);
                }
            }
        }
    }
    
    public void e(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    i.c.didDisplayInterstitial(s);
                }
                case 1: {
                    i.c.didDisplayRewardedVideo(s);
                }
            }
        }
    }
    
    public boolean f(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    return i.c.shouldDisplayInterstitial(s);
                }
                case 1: {
                    return i.c.shouldDisplayRewardedVideo(s);
                }
            }
        }
        return true;
    }
    
    public boolean g(final String s) {
        if (i.c != null) {
            switch (this.a) {
                case 0: {
                    return i.c.shouldRequestInterstitial(s);
                }
            }
        }
        return true;
    }
    
    public class a implements Runnable
    {
        private final int b;
        private final String c;
        private final CBError.CBImpressionError d;
        
        public a(final int b, final String c, final CBError.CBImpressionError d) {
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        @Override
        public void run() {
            switch (this.b) {
                default: {}
                case 0: {
                    com.chartboost.sdk.impl.c.this.d(this.c);
                }
                case 1: {
                    com.chartboost.sdk.impl.c.this.a(this.c);
                }
                case 2: {
                    com.chartboost.sdk.impl.c.this.b(this.c);
                }
                case 3: {
                    com.chartboost.sdk.impl.c.this.c(this.c);
                }
                case 4: {
                    com.chartboost.sdk.impl.c.this.a(this.c, this.d);
                }
                case 5: {
                    com.chartboost.sdk.impl.c.this.e(this.c);
                }
            }
        }
    }
}
