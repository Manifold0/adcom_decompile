// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdLoadListener;
import android.app.Activity;
import java.util.Map;
import com.applovin.sdk.AppLovinAd;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import java.util.concurrent.atomic.AtomicBoolean;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.mediation.AppLovinMediationAdapter;

class cp
{
    private final String a;
    private final AppLovinMediationAdapter b;
    private final AppLovinSdkImpl c;
    private final AppLovinLogger d;
    private cm e;
    private final AtomicBoolean f;
    
    cp(final String a, final AppLovinMediationAdapter b, final AppLovinSdkImpl c) {
        if (a == null) {
            throw new IllegalArgumentException("No implementation name specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No implementation specified");
        }
        if (c == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = c.getLogger();
        this.e = new cm(a, c);
        this.f = new AtomicBoolean(true);
    }
    
    private void a(final int n, final cw cw) {
        if (cw.c.compareAndSet(false, true) && cw.b != null) {
            cw.b.failedToReceiveAd(n);
        }
    }
    
    private void a(final AppLovinMediatedAdInfo appLovinMediatedAdInfo, final cw cw) {
        if (cw.c.compareAndSet(false, true) && cw.b != null) {
            cw.b.adReceived(new ck(cw.a, true, appLovinMediatedAdInfo));
        }
    }
    
    private void a(final String s, final Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("No operation specified");
        }
        try {
            this.d.d("MediationAdapterWrapper", "Running implementation operation '" + s + "'...");
            runnable.run();
        }
        catch (Throwable t) {
            this.d.e("MediationAdapterWrapper", "Unable to implementation operation run " + s + ", marking " + this + " as disabled", t);
            this.a("fail_" + s);
        }
    }
    
    private void b(final ck ck) {
        final Map<String, String> e = ck.e();
        if (e != null) {
            this.e.b(e);
        }
    }
    
    public String a() {
        return this.a;
    }
    
    void a(final ck ck) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (!this.f.get()) {
            this.d.w("MediationAdapterWrapper", "Mediation implementation '" + this.f() + "' was disabled due to earlier failures. Preparing ads with this implementation is disabled.");
            return;
        }
        if (!this.b.isReady()) {
            this.d.userError("MediationAdapterWrapper", "Mediation implementation '" + this.f() + "' is not ready.");
            return;
        }
        this.a("ad_prepare", new ct(this, ck));
    }
    
    void a(final ck ck, final Activity activity, final h h) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (!ck.a()) {
            throw new IllegalArgumentException("Mediated ad is not ready");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (h == null) {
            throw new IllegalArgumentException("No listeners specified");
        }
        this.a("ad_render", new cu(this, h, ck, activity));
    }
    
    void a(final ck ck, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (!this.f.get()) {
            this.d.userError("MediationAdapterWrapper", "Mediation implementation '" + this.f() + "' was disabled due to earlier failures. Loading ads with this implementation is disabled.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(-5103);
            }
        }
        else {
            if (this.b.isReady()) {
                this.a("ad_load", new cr(this, ck, new cw(ck, appLovinAdLoadListener)));
                return;
            }
            this.d.userError("MediationAdapterWrapper", "Mediation implementation '" + this.f() + "' is not ready.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(-5104);
            }
        }
    }
    
    void a(final String s) {
        this.d.i("MediationAdapterWrapper", "Marking " + this.f() + " as disabled due to: " + s);
        this.f.set(false);
    }
    
    void a(final Map<String, String> map) {
        this.a("init", new cq(this, map));
    }
    
    boolean b() {
        return this.f.get();
    }
    
    boolean c() {
        return this.b() && this.b.isReady();
    }
    
    AppLovinMediationAdapter d() {
        return this.b;
    }
    
    String e() {
        if (this.b != null) {
            try {
                return this.b.getVersion();
            }
            catch (Throwable t) {
                this.d.e("MediationAdapterWrapper", "Unable to get implementation version, marking " + this + " as disabled", t);
                this.a("fail_version");
            }
        }
        return null;
    }
    
    String f() {
        return this.b.getClass().getName();
    }
    
    AppLovinMediationAdapterConfig g() {
        return this.e;
    }
    
    @Override
    public String toString() {
        return "[MediationAdapterWrapper implementation: " + this.f() + "]";
    }
}
