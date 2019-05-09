package com.tapjoy.internal;

import com.tapjoy.internal.fi.C2926a;

public final class ez {
    /* renamed from: a */
    public et f7707a;
    /* renamed from: b */
    public volatile C2926a f7708b;
    /* renamed from: c */
    public int f7709c;
    /* renamed from: d */
    public volatile C2926a f7710d;
    /* renamed from: e */
    public volatile C2926a f7711e;

    /* renamed from: a */
    public final void m7689a() {
        m7690a(16);
        C2926a c2926a = this.f7710d;
        if (c2926a != null) {
            this.f7710d = null;
            c2926a.m7739b().m7742c();
        }
    }

    /* renamed from: a */
    public final synchronized void m7690a(int i) {
        C2926a c2926a = this.f7708b;
        if (c2926a != null && this.f7709c < i) {
            this.f7709c |= i;
            c2926a.m7737a("state", Integer.valueOf(this.f7709c)).m7739b().m7742c();
        }
    }
}
