package com.facebook.ads.internal.p032d;

import android.os.Bundle;
import com.facebook.ads.internal.p025w.p026b.C1910r;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnitConstants.String;

/* renamed from: com.facebook.ads.internal.d.d */
public class C1990d implements C1910r<Bundle> {
    /* renamed from: a */
    private C1989c f4390a;
    /* renamed from: b */
    private final C1989c f4391b;
    /* renamed from: c */
    private final C1907b f4392c;
    /* renamed from: d */
    private boolean f4393d = false;
    /* renamed from: e */
    private boolean f4394e = false;
    /* renamed from: f */
    private boolean f4395f = false;

    public C1990d(C1907b c1907b) {
        this.f4392c = c1907b;
        this.f4391b = new C1989c(c1907b.f4056b);
        this.f4390a = new C1989c(c1907b.f4056b);
    }

    public C1990d(C1907b c1907b, Bundle bundle) {
        this.f4392c = c1907b;
        this.f4391b = (C1989c) bundle.getSerializable("testStats");
        this.f4390a = (C1989c) bundle.getSerializable("viewableStats");
        this.f4393d = bundle.getBoolean(ParametersKeys.VIDEO_STATUS_ENDED);
        this.f4394e = bundle.getBoolean("passed");
        this.f4395f = bundle.getBoolean(String.VIDEO_COMPLETE);
    }

    /* renamed from: b */
    private void m4767b() {
        this.f4395f = true;
        this.f4393d = true;
        this.f4392c.mo5396a(this.f4395f, this.f4394e, this.f4394e ? this.f4390a : this.f4391b);
    }

    /* renamed from: a */
    public void m4768a() {
        if (!this.f4393d) {
            this.f4390a.m4763b();
        }
    }

    /* renamed from: a */
    public void m4769a(double d, double d2) {
        if (!this.f4393d) {
            this.f4391b.m4762a(d, d2);
            this.f4390a.m4762a(d, d2);
            double h = this.f4392c.f4059e ? this.f4390a.m4765c().m4760h() : this.f4390a.m4765c().m4759g();
            if (this.f4392c.f4057c >= 0.0d && this.f4391b.m4765c().m4758f() > this.f4392c.f4057c && h == 0.0d) {
                m4767b();
            } else if (h >= this.f4392c.f4058d) {
                this.f4394e = true;
                m4767b();
            }
        }
    }

    /* renamed from: g */
    public Bundle mo5398g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewableStats", this.f4390a);
        bundle.putSerializable("testStats", this.f4391b);
        bundle.putBoolean(ParametersKeys.VIDEO_STATUS_ENDED, this.f4393d);
        bundle.putBoolean("passed", this.f4394e);
        bundle.putBoolean(String.VIDEO_COMPLETE, this.f4395f);
        return bundle;
    }
}
