package com.facebook.ads.internal.p025w.p026b;

import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.w.b.y */
public abstract class C1839y<T> implements Runnable {
    /* renamed from: a */
    private final WeakReference<T> f3832a;

    public C1839y(T t) {
        this.f3832a = new WeakReference(t);
    }

    /* renamed from: a */
    public T m4138a() {
        return this.f3832a.get();
    }
}
