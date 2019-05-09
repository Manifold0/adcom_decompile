// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;
import org.json.JSONObject;

abstract class fs<T> extends dx implements af<T>
{
    private final String a;
    private final T b;
    protected ae h;
    private final af<T> i;
    private String j;
    private String k;
    private JSONObject l;
    private int m;
    private boolean n;
    private int o;
    private long p;
    private ec<String> q;
    private ec<String> r;
    
    fs(final String a, final T b, final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        super(s, appLovinSdkImpl);
        this.n = true;
        this.o = 1;
        this.q = null;
        this.r = null;
        this.a = a;
        this.b = b;
        this.m = appLovinSdkImpl.get(ea.w);
        this.p = appLovinSdkImpl.get(ea.t);
        this.h = new ae();
        this.i = (af<T>)new ft(this, s, appLovinSdkImpl);
    }
    
    private <T> void c(final ec<T> ec) {
        if (ec != null) {
            final ed settingsManager = this.d.getSettingsManager();
            settingsManager.a(ec, ec.c());
            settingsManager.a();
        }
    }
    
    @Override
    public void a(final int n) {
    }
    
    public void a(final long p) {
        this.p = p;
    }
    
    public void a(final ec<String> q) {
        this.q = q;
    }
    
    @Override
    public void a(final T t, final int n) {
    }
    
    public void a(final String j) {
        this.j = j;
    }
    
    public void a(final JSONObject l) {
        this.l = l;
    }
    
    public void a(final boolean n) {
        this.n = n;
    }
    
    public void b(final int m) {
        this.m = m;
    }
    
    public void b(final ec<String> r) {
        this.r = r;
    }
    
    public void b(final String k) {
        this.k = k;
    }
    
    public void c(final int o) {
        this.o = o;
    }
    
    @Override
    public void run() {
        final ad connectionManager = this.d.getConnectionManager();
        if (!this.d.e() && !this.d.isEnabled()) {
            this.e.e(this.a(), "AppLovin SDK is disabled: please check your connection");
            this.e.e("AppLovinSdk", "AppLovin SDK is disabled: please check your connection");
            this.a(-22);
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.j) && this.j.length() >= 4) {
            String a;
            if (TextUtils.isEmpty((CharSequence)(a = this.a))) {
                if (this.l == null) {
                    a = "GET";
                }
                else {
                    a = "POST";
                }
            }
            connectionManager.a(this.j, a, this.m, this.l, this.b, this.n, this.h, this.i);
            return;
        }
        this.e.e(this.a(), "Task has an invalid or null request endpoint.");
        this.a(-900);
    }
}
