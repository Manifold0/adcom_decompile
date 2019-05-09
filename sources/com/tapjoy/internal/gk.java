package com.tapjoy.internal;

import com.tapjoy.internal.hm.C2978a;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class gk implements ck {
    /* renamed from: a */
    public final gc f7920a;
    /* renamed from: b */
    Set f7921b = null;
    /* renamed from: c */
    private final Map f7922c = Collections.synchronizedMap(new HashMap());
    /* renamed from: d */
    private final Map f7923d = cx.m7344a();

    public gk(gc gcVar) {
        this.f7920a = gcVar;
    }

    /* renamed from: a */
    private void m7966a(cf cfVar, C2978a c2978a) {
        if (cfVar instanceof hm) {
            if (c2978a.f8131b != null) {
                Iterable iterable = c2978a.f8131b;
                synchronized (this) {
                    Set hashSet;
                    if (iterable instanceof Collection) {
                        hashSet = new HashSet(cv.m7342a(iterable));
                    } else {
                        hashSet = cy.m7346a(iterable.iterator());
                    }
                    this.f7921b = hashSet;
                }
            }
            hm hmVar = (hm) cfVar;
            String str = hmVar.f8132c;
            boolean z = hmVar.f8133d;
            this.f7923d.remove(str);
            if (!z) {
                this.f7922c.put(str, c2978a.f8130a);
            }
            gj gjVar = c2978a.f8130a;
            gd gdVar = this.f7920a.f7864p;
            if (gjVar instanceof gi) {
                fz.m7813a("No content for \"{}\"", str);
                gdVar.mo6136a(str);
                return;
            }
            fz.m7813a("New content for \"{}\" is ready", str);
            if (z) {
                gjVar.mo6290a(gdVar, new ez());
                return;
            } else {
                gdVar.mo6139b(str);
                return;
            }
        }
        throw new IllegalStateException(cfVar.getClass().getName());
    }

    /* renamed from: a */
    public final void mo6294a(cf cfVar) {
        m7966a(cfVar, new C2978a(new gi(), null));
    }
}
