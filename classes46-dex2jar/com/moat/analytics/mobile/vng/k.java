// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Application;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.support.annotation.Nullable;

class k extends MoatAnalytics implements b
{
    boolean a;
    boolean b;
    boolean c;
    @Nullable
    g d;
    WeakReference<Context> e;
    private boolean f;
    private String g;
    
    k() {
        this.a = false;
        this.b = false;
        this.c = false;
        this.f = false;
    }
    
    private void a(final MoatOptions moatOptions, final Application application) {
        if (this.f) {
            p.a(3, "Analytics", this, "Moat SDK has already been started.");
            return;
        }
        w.a().b();
        if (moatOptions.loggingEnabled && a(application.getApplicationContext())) {
            this.a = true;
        }
        this.c = moatOptions.disableLocationServices;
        if (application == null) {
            p.a("[ERROR] ", "Moat Analytics SDK didn't start, application was null");
            return;
        }
        this.e = new WeakReference<Context>(application.getApplicationContext());
        this.f = true;
        this.b = moatOptions.autoTrackGMAInterstitials;
        com.moat.analytics.mobile.vng.a.a(application);
        w.a().a((w.b)this);
        if (!moatOptions.disableAdIdCollection) {
            s.a((Context)application);
        }
        p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.2.0 started");
    }
    
    private static boolean a(final Context context) {
        return (context.getApplicationInfo().flags & 0x2) != 0x0;
    }
    
    private void d() {
        if (this.d == null) {
            (this.d = new g((Context)com.moat.analytics.mobile.vng.a.a(), com.moat.analytics.mobile.vng.g.a.a)).a(this.g);
            p.a(3, "Analytics", this, "Preparing native display tracking with partner code " + this.g);
            p.a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.g);
        }
    }
    
    boolean a() {
        return this.f;
    }
    
    @Override
    public void b() {
        o.a();
        if (this.g == null) {
            return;
        }
        try {
            this.d();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    @Override
    public void c() {
    }
    
    @Override
    public void prepareNativeDisplayTracking(final String g) {
        this.g = g;
        if (w.a().a == w.d.a) {
            return;
        }
        try {
            this.d();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    @Override
    public void start(final Application application) {
        this.start(new MoatOptions(), application);
    }
    
    @Override
    public void start(final MoatOptions moatOptions, final Application application) {
        try {
            this.a(moatOptions, application);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
}
