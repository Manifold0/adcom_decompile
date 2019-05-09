package com.facebook.ads.internal.p051s;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.s.h */
public class C2091h {
    /* renamed from: a */
    private boolean f4767a;
    /* renamed from: b */
    private int f4768b;
    /* renamed from: c */
    private int f4769c;
    /* renamed from: d */
    private int f4770d;
    /* renamed from: e */
    private int f4771e;
    /* renamed from: f */
    private int f4772f;

    /* renamed from: a */
    public Map<String, String> m5210a() {
        Map hashMap = new HashMap();
        hashMap.put("is_timeout", Boolean.toString(this.f4767a));
        hashMap.put("ad_count", Integer.toString(this.f4768b));
        hashMap.put("default_ad_index", Integer.toString(this.f4769c));
        hashMap.put("selected_ad_index", Integer.toString(this.f4770d));
        hashMap.put("elapsed_time_from_timer_ms", Integer.toString(this.f4771e));
        hashMap.put("countdown_time_ms", Integer.toString(this.f4772f));
        return hashMap;
    }

    /* renamed from: a */
    public void m5211a(int i) {
        this.f4768b = i;
    }

    /* renamed from: a */
    public void m5212a(boolean z) {
        this.f4767a = z;
    }

    /* renamed from: b */
    public void m5213b(int i) {
        this.f4769c = i;
    }

    /* renamed from: c */
    public void m5214c(int i) {
        this.f4770d = i;
    }

    /* renamed from: d */
    public void m5215d(int i) {
        this.f4771e = i;
    }

    /* renamed from: e */
    public void m5216e(int i) {
        this.f4772f = i;
    }
}
