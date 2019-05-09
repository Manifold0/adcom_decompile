package com.tapjoy.internal;

import java.lang.ref.WeakReference;

public final class cd {
    /* renamed from: a */
    public WeakReference f7279a;

    /* renamed from: a */
    public final Object m7315a() {
        WeakReference weakReference = this.f7279a;
        return weakReference != null ? weakReference.get() : null;
    }

    /* renamed from: a */
    public final void m7316a(Object obj) {
        this.f7279a = new WeakReference(obj);
    }
}
