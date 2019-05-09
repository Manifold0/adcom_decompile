package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class ad<T> {
    /* renamed from: b */
    public final String f2981b;
    /* renamed from: c */
    public final String f2982c;
    /* renamed from: d */
    public final int f2983d;
    /* renamed from: e */
    public final AtomicInteger f2984e = new AtomicInteger();
    /* renamed from: f */
    public final File f2985f;
    /* renamed from: g */
    public long f2986g;
    /* renamed from: h */
    public long f2987h;
    /* renamed from: i */
    public long f2988i;
    /* renamed from: j */
    public int f2989j;

    public ad(String str, String str2, int i, File file) {
        this.f2981b = str;
        this.f2982c = str2;
        this.f2983d = i;
        this.f2985f = file;
        this.f2986g = 0;
        this.f2987h = 0;
        this.f2988i = 0;
        this.f2989j = 0;
    }

    /* renamed from: a */
    public ae mo4280a() {
        return new ae(null, null, null);
    }

    /* renamed from: a */
    public af<T> mo4281a(ag agVar) {
        return af.m3370a(null);
    }

    /* renamed from: a */
    public void mo4283a(T t, ag agVar) {
    }

    /* renamed from: a */
    public void mo4282a(CBError cBError, ag agVar) {
    }

    /* renamed from: b */
    public boolean m3368b() {
        return this.f2984e.compareAndSet(0, -1);
    }
}
