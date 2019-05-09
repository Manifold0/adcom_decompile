// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import com.applovin.sdk.AppLovinAd;
import android.os.Looper;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import java.lang.ref.WeakReference;
import android.os.Handler;

public class h
{
    private static final Handler a;
    private WeakReference<AppLovinAdDisplayListener> b;
    private WeakReference<AppLovinAdClickListener> c;
    private WeakReference<AppLovinAdRewardListener> d;
    private AppLovinAdDisplayListener e;
    private AppLovinAdClickListener f;
    
    static {
        a = new Handler(Looper.getMainLooper());
    }
    
    public h() {
        this.b = new WeakReference<AppLovinAdDisplayListener>(null);
        this.c = new WeakReference<AppLovinAdClickListener>(null);
        this.d = new WeakReference<AppLovinAdRewardListener>(null);
    }
    
    void a(final AppLovinAd appLovinAd) {
        final AppLovinAdDisplayListener appLovinAdDisplayListener = this.b.get();
        if (appLovinAdDisplayListener != null) {
            h.a.post((Runnable)new i(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.e != null) {
            this.e.adDisplayed(appLovinAd);
        }
    }
    
    public void a(final AppLovinAdClickListener appLovinAdClickListener) {
        this.c = new WeakReference<AppLovinAdClickListener>(appLovinAdClickListener);
    }
    
    public void a(final AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.b = new WeakReference<AppLovinAdDisplayListener>(appLovinAdDisplayListener);
    }
    
    public void a(final AppLovinAdRewardListener appLovinAdRewardListener) {
        this.d = new WeakReference<AppLovinAdRewardListener>(appLovinAdRewardListener);
    }
    
    void a(final Map<String, String> map, final ck ck) {
        final AppLovinAdRewardListener appLovinAdRewardListener = this.d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardVerified(ck, map);
        }
    }
    
    void b(final AppLovinAd appLovinAd) {
        final AppLovinAdDisplayListener appLovinAdDisplayListener = this.b.get();
        if (appLovinAdDisplayListener != null) {
            h.a.post((Runnable)new j(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.e != null) {
            this.e.adHidden(appLovinAd);
        }
    }
    
    void b(final AppLovinAdClickListener f) {
        this.f = f;
    }
    
    void b(final AppLovinAdDisplayListener e) {
        this.e = e;
    }
    
    void b(final Map<String, String> map, final ck ck) {
        final AppLovinAdRewardListener appLovinAdRewardListener = this.d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardRejected(ck, map);
        }
    }
    
    void c(final AppLovinAd appLovinAd) {
        final AppLovinAdClickListener appLovinAdClickListener = this.c.get();
        if (appLovinAdClickListener != null) {
            h.a.post((Runnable)new k(this, appLovinAdClickListener, appLovinAd));
        }
        if (this.f != null) {
            this.f.adClicked(appLovinAd);
        }
    }
}
