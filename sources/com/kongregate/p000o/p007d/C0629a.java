package com.kongregate.p000o.p007d;

import com.kongregate.android.internal.util.C0562j;

/* renamed from: com.kongregate.o.d.a */
public class C0629a implements C0628c {
    /* renamed from: a */
    private final C0628c f964a = m981f();

    /* renamed from: a */
    public String mo1251a() {
        return this.f964a == null ? "default" : this.f964a.mo1251a();
    }

    /* renamed from: b */
    public boolean mo1252b() {
        return this.f964a == null || this.f964a.mo1252b();
    }

    /* renamed from: c */
    public boolean mo1253c() {
        return mo1252b() && (this.f964a == null || this.f964a.mo1252b());
    }

    /* renamed from: d */
    public boolean mo1254d() {
        return mo1253c() && (this.f964a == null || this.f964a.mo1254d());
    }

    /* renamed from: e */
    public boolean m986e() {
        return mo1254d();
    }

    /* renamed from: f */
    private C0628c m981f() {
        try {
            C0628c c0628c = (C0628c) Class.forName("com.kongregate.android.internal.config.CustomBuildFeatureSet").newInstance();
            C0562j.m756b("Using custom build feature set");
            return c0628c;
        } catch (ClassNotFoundException e) {
            C0562j.m756b("Using default build feature set");
        } catch (Throwable e2) {
            C0562j.m757b("Using default build feature set due to error: ", e2);
        } catch (Throwable e22) {
            C0562j.m757b("Using default build feature set due to error: ", e22);
        }
        return null;
    }
}
