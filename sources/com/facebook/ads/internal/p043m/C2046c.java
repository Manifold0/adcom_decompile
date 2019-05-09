package com.facebook.ads.internal.p043m;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.m.c */
public class C2046c {
    /* renamed from: a */
    private List<C2044a> f4572a = new ArrayList();
    /* renamed from: b */
    private int f4573b = 0;
    /* renamed from: c */
    private C2047d f4574c;
    @Nullable
    /* renamed from: d */
    private String f4575d;
    @Nullable
    /* renamed from: e */
    private String f4576e;

    public C2046c(C2047d c2047d, @Nullable String str, @Nullable String str2) {
        this.f4574c = c2047d;
        this.f4575d = str;
        this.f4576e = str2;
    }

    /* renamed from: a */
    public C2047d m4970a() {
        return this.f4574c;
    }

    /* renamed from: a */
    public void m4971a(C2044a c2044a) {
        this.f4572a.add(c2044a);
    }

    @Nullable
    /* renamed from: b */
    public String m4972b() {
        return this.f4575d;
    }

    @Nullable
    /* renamed from: c */
    public String m4973c() {
        return this.f4576e;
    }

    /* renamed from: d */
    public int m4974d() {
        return this.f4572a.size();
    }

    /* renamed from: e */
    public C2044a m4975e() {
        if (this.f4573b >= this.f4572a.size()) {
            return null;
        }
        this.f4573b++;
        return (C2044a) this.f4572a.get(this.f4573b - 1);
    }

    @Nullable
    /* renamed from: f */
    public String m4976f() {
        return (this.f4573b <= 0 || this.f4573b > this.f4572a.size()) ? null : ((C2044a) this.f4572a.get(this.f4573b - 1)).m4966c().optString("ct");
    }

    /* renamed from: g */
    public boolean m4977g() {
        return this.f4574c == null || C2597v.m6666a() > this.f4574c.m4980a() + ((long) this.f4574c.m4991l());
    }

    /* renamed from: h */
    public long m4978h() {
        return this.f4574c != null ? this.f4574c.m4980a() + ((long) this.f4574c.m4991l()) : -1;
    }
}
