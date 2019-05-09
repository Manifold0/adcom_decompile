package com.facebook.ads.internal.adapters.p030b;

import android.support.annotation.Nullable;
import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.adapters.b.d */
public class C1869d implements Serializable {
    private static final long serialVersionUID = -268645651038092386L;
    /* renamed from: a */
    private final String f3914a;
    /* renamed from: b */
    private final int f3915b;
    /* renamed from: c */
    private final int f3916c;
    /* renamed from: d */
    private final boolean f3917d;
    /* renamed from: e */
    private final boolean f3918e;
    /* renamed from: f */
    private final String f3919f;
    /* renamed from: g */
    private final int f3920g;
    /* renamed from: h */
    private final int f3921h;
    @Nullable
    /* renamed from: i */
    private final C1884n f3922i;
    /* renamed from: j */
    private String f3923j;

    /* renamed from: com.facebook.ads.internal.adapters.b.d$a */
    static class C1868a {
        /* renamed from: a */
        private String f3905a;
        /* renamed from: b */
        private int f3906b;
        /* renamed from: c */
        private int f3907c;
        /* renamed from: d */
        private boolean f3908d;
        /* renamed from: e */
        private boolean f3909e;
        /* renamed from: f */
        private String f3910f;
        /* renamed from: g */
        private int f3911g;
        /* renamed from: h */
        private int f3912h;
        /* renamed from: i */
        private C1884n f3913i;

        C1868a() {
        }

        /* renamed from: a */
        C1868a m4228a(int i) {
            this.f3906b = i;
            return this;
        }

        /* renamed from: a */
        C1868a m4229a(@Nullable C1884n c1884n) {
            this.f3913i = c1884n;
            return this;
        }

        /* renamed from: a */
        C1868a m4230a(String str) {
            this.f3905a = str;
            return this;
        }

        /* renamed from: a */
        C1868a m4231a(boolean z) {
            this.f3908d = z;
            return this;
        }

        /* renamed from: a */
        C1869d m4232a() {
            return new C1869d();
        }

        /* renamed from: b */
        C1868a m4233b(int i) {
            this.f3907c = i;
            return this;
        }

        /* renamed from: b */
        C1868a m4234b(String str) {
            this.f3910f = str;
            return this;
        }

        /* renamed from: b */
        C1868a m4235b(boolean z) {
            this.f3909e = z;
            return this;
        }

        /* renamed from: c */
        C1868a m4236c(int i) {
            this.f3911g = i;
            return this;
        }

        /* renamed from: d */
        C1868a m4237d(int i) {
            this.f3912h = i;
            return this;
        }
    }

    private C1869d(C1868a c1868a) {
        this.f3914a = c1868a.f3905a;
        this.f3915b = c1868a.f3906b;
        this.f3916c = c1868a.f3907c;
        this.f3917d = c1868a.f3908d;
        this.f3918e = c1868a.f3909e;
        this.f3919f = c1868a.f3910f;
        this.f3920g = c1868a.f3911g;
        this.f3921h = c1868a.f3912h;
        this.f3922i = c1868a.f3913i;
    }

    /* renamed from: a */
    public String m4238a() {
        return this.f3914a;
    }

    /* renamed from: a */
    void m4239a(String str) {
        this.f3923j = str;
    }

    /* renamed from: b */
    public String m4240b() {
        return this.f3923j;
    }

    /* renamed from: c */
    public int m4241c() {
        return this.f3915b;
    }

    /* renamed from: d */
    public int m4242d() {
        return this.f3916c;
    }

    /* renamed from: e */
    public boolean m4243e() {
        return this.f3917d;
    }

    /* renamed from: f */
    public boolean m4244f() {
        return this.f3918e;
    }

    /* renamed from: g */
    public String m4245g() {
        return this.f3919f;
    }

    /* renamed from: h */
    public int m4246h() {
        return this.f3920g;
    }

    /* renamed from: i */
    public int m4247i() {
        return this.f3921h;
    }

    @Nullable
    /* renamed from: j */
    public C1884n m4248j() {
        return this.f3922i;
    }
}
