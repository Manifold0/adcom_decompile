// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.support.annotation.UiThread;
import android.support.annotation.Nullable;
import android.content.Context;
import java.lang.ref.WeakReference;

final class f extends MoatAnalytics implements b
{
    private boolean \u02bb;
    private String \u02bc;
    private MoatOptions \u02bd;
    WeakReference<Context> \u02ca;
    boolean \u02cb;
    boolean \u02ce;
    boolean \u02cf;
    @Nullable
    com.moat.analytics.mobile.cha.a \u0971;
    
    f() {
        this.\u02ce = false;
        this.\u02cf = false;
        this.\u02cb = false;
        this.\u02bb = false;
    }
    
    @UiThread
    private void \u02cf() {
        if (this.\u0971 == null) {
            (this.\u0971 = new com.moat.analytics.mobile.cha.a(com.moat.analytics.mobile.cha.c.\u02cf(), com.moat.analytics.mobile.cha.a.d.\u02cf)).\u02ca(this.\u02bc);
            com.moat.analytics.mobile.cha.a.\u02cf(3, "Analytics", this, "Preparing native display tracking with partner code " + this.\u02bc);
            com.moat.analytics.mobile.cha.a.\u02ca("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.\u02bc);
        }
    }
    
    @UiThread
    @Override
    public final void prepareNativeDisplayTracking(final String \u02bc) {
        this.\u02bc = \u02bc;
        if (t.\u02cf().\u02ce == a.\u0971) {
            return;
        }
        try {
            this.\u02cf();
        }
        catch (Exception ex) {
            o.\u02ce(ex);
        }
    }
    
    @Override
    public final void start(final Application application) {
        this.start(new MoatOptions(), application);
    }
    
    @Override
    public final void start(final MoatOptions \u02bd, final Application application) {
        try {
            if (this.\u02bb) {
                com.moat.analytics.mobile.cha.a.\u02cf(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.\u02bd = \u02bd;
            t.\u02cf().\u02ce();
            this.\u02cb = \u02bd.disableLocationServices;
            if (application == null) {
                throw new o("Moat Analytics SDK didn't start, application was null");
            }
        }
        catch (Exception ex) {
            o.\u02ce(ex);
            return;
        }
        if (\u02bd.loggingEnabled && r.\u02cb(application.getApplicationContext())) {
            this.\u02ce = true;
        }
        this.\u02ca = new WeakReference<Context>(application.getApplicationContext());
        this.\u02bb = true;
        this.\u02cf = \u02bd.autoTrackGMAInterstitials;
        com.moat.analytics.mobile.cha.c.\u0971(application);
        t.\u02cf().\u02ca((t.b)this);
        if (!\u02bd.disableAdIdCollection) {
            r.\u02ce(application);
        }
        com.moat.analytics.mobile.cha.a.\u02ca("[SUCCESS] ", "Moat Analytics SDK Version 2.4.1 started");
    }
    
    final boolean \u02ca() {
        return this.\u02bb;
    }
    
    final boolean \u02cb() {
        return this.\u02bd != null && this.\u02bd.disableLocationServices;
    }
    
    @Override
    public final void \u02ce() throws o {
        o.\u0971();
        n.\u02cf();
        if (this.\u02bc == null) {
            return;
        }
        try {
            this.\u02cf();
        }
        catch (Exception ex) {
            o.\u02ce(ex);
        }
    }
}
