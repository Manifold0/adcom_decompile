package com.tapjoy.internal;

import com.tapjoy.internal.dn.C2873a;

public abstract class dj extends dn {
    /* renamed from: a */
    public abstract dq mo6268a(int i);

    /* renamed from: a */
    public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
        dpVar.m7470c(((dq) obj).mo6269a());
    }

    protected dj(Class cls) {
        super(dk.VARINT, cls);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo6214a(C2874do c2874do) {
        int d = c2874do.m7459d();
        dq a = mo6268a(d);
        if (a != null) {
            return a;
        }
        throw new C2873a(d, this.a);
    }
}
