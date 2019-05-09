package com.applovin.impl.sdk;

import android.app.AlertDialog;
import com.applovin.impl.adview.az;

public class bm {
    /* renamed from: a */
    private final AppLovinSdkImpl f2153a;
    /* renamed from: b */
    private final az f2154b;
    /* renamed from: c */
    private AlertDialog f2155c;

    public bm(az azVar, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2153a = appLovinSdkImpl;
        this.f2154b = azVar;
    }

    /* renamed from: a */
    public void m2378a() {
        this.f2154b.runOnUiThread(new bn(this));
    }

    /* renamed from: b */
    public void m2379b() {
        this.f2154b.runOnUiThread(new bo(this));
    }

    /* renamed from: c */
    public void m2380c() {
        this.f2154b.runOnUiThread(new br(this));
    }

    /* renamed from: d */
    public boolean m2381d() {
        return this.f2155c != null ? this.f2155c.isShowing() : false;
    }
}
