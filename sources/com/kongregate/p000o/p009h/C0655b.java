package com.kongregate.p000o.p009h;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* renamed from: com.kongregate.o.h.b */
public class C0655b {
    /* renamed from: a */
    public static final C0655b f1057a = new C0655b(new ArrayList(0));
    /* renamed from: b */
    public final int f1058b;
    /* renamed from: c */
    private final ArrayList<C0656c> f1059c = new ArrayList(this.f1058b);

    public C0655b(Collection<C0656c> collection) {
        this.f1058b = collection.size();
        for (C0656c add : collection) {
            this.f1059c.add(add);
        }
    }

    /* renamed from: a */
    public C0656c m1136a(int i) {
        if (i < 0 || i >= this.f1058b) {
            return null;
        }
        return (C0656c) this.f1059c.get(i);
    }

    /* renamed from: a */
    public Collection<C0656c> m1137a() {
        return Collections.unmodifiableCollection(this.f1059c);
    }
}
