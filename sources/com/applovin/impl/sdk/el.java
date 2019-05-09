package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;

public class el extends ej {
    /* renamed from: a */
    private final C1286m f2466a;
    /* renamed from: b */
    private boolean f2467b;

    public el(C1286m c1286m, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheAppLovinAd", c1286m, appLovinAdLoadListener, appLovinSdkImpl);
        this.f2466a = c1286m;
    }

    /* renamed from: d */
    private void m2769d() {
        this.e.mo4172d(this.c, "Caching HTML resources...");
        this.f2466a.m3023a(m2761b(this.f2466a.mo4000a(), this.f2466a.m1802O()));
        this.e.mo4172d(this.c, "Finish caching non-video resources for ad #" + this.f2466a.getAdIdNumber());
        this.e.mo4172d(this.c, "Ad updated with cachedHTML = " + this.f2466a.mo4000a());
    }

    /* renamed from: e */
    private void m2770e() {
        Uri a = m2755a(this.f2466a.m3027e());
        if (a != null) {
            this.f2466a.m3025c();
            this.f2466a.m3022a(a);
        }
    }

    /* renamed from: a */
    public void m2771a(boolean z) {
        this.f2467b = z;
    }

    public void run() {
        if (this.f2466a.mo4001b()) {
            this.e.mo4172d(this.c, "Begin caching for streaming ad #" + this.f2466a.getAdIdNumber() + "...");
            m2762b();
            if (this.f2467b) {
                this.e.mo4172d(this.c, "Calling back ad load immediately");
                m2765c();
            }
            m2769d();
            if (!this.f2467b) {
                this.e.mo4172d(this.c, "Calling back ad load AFTER caching endcard");
                m2765c();
            }
            m2770e();
        } else {
            this.e.mo4172d(this.c, "Begin processing for non-streaming ad #" + this.f2466a.getAdIdNumber() + "...");
            m2762b();
            m2769d();
            m2770e();
            this.e.mo4172d(this.c, "Caching finished. Calling back ad load success...");
            m2765c();
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f2466a.mo3994l();
        C1280g.m2907a(this.f2466a, this.d);
        C1280g.m2905a(currentTimeMillis, this.f2466a, this.d);
        m2758a(this.f2466a);
    }
}
