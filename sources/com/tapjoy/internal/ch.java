package com.tapjoy.internal;

public final class ch implements Runnable {
    /* renamed from: a */
    private final cf f7284a;
    /* renamed from: b */
    private final ck f7285b;

    protected ch(cf cfVar, ck ckVar) {
        this.f7284a = cfVar;
        this.f7285b = ckVar;
    }

    public final void run() {
        try {
            Object f = this.f7284a.mo6337f();
            if (this.f7285b != null && !(this.f7285b instanceof cl)) {
                this.f7285b.mo6295a(this.f7284a, f);
            }
        } catch (Throwable th) {
            if (this.f7285b != null && !(this.f7285b instanceof cl)) {
                this.f7285b.mo6294a(this.f7284a);
            }
        }
    }
}
