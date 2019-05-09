// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;

public class el extends ej
{
    private final m a;
    private boolean b;
    
    public el(final m a, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheAppLovinAd", a, appLovinAdLoadListener, appLovinSdkImpl);
        this.a = a;
    }
    
    private void d() {
        this.e.d(this.c, "Caching HTML resources...");
        this.a.a(this.b(this.a.a(), this.a.O()));
        this.e.d(this.c, "Finish caching non-video resources for ad #" + this.a.getAdIdNumber());
        this.e.d(this.c, "Ad updated with cachedHTML = " + this.a.a());
    }
    
    private void e() {
        final Uri a = this.a(this.a.e());
        if (a != null) {
            this.a.c();
            this.a.a(a);
        }
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    @Override
    public void run() {
        if (this.a.b()) {
            this.e.d(this.c, "Begin caching for streaming ad #" + this.a.getAdIdNumber() + "...");
            this.b();
            if (this.b) {
                this.e.d(this.c, "Calling back ad load immediately");
                this.c();
            }
            this.d();
            if (!this.b) {
                this.e.d(this.c, "Calling back ad load AFTER caching endcard");
                this.c();
            }
            this.e();
        }
        else {
            this.e.d(this.c, "Begin processing for non-streaming ad #" + this.a.getAdIdNumber() + "...");
            this.b();
            this.d();
            this.e();
            this.e.d(this.c, "Caching finished. Calling back ad load success...");
            this.c();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long l = this.a.l();
        com.applovin.impl.sdk.g.a(this.a, this.d);
        com.applovin.impl.sdk.g.a(currentTimeMillis - l, this.a, this.d);
        this.a(this.a);
    }
}
