package com.applovin.impl.adview;

class an implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ap f1771a;
    /* renamed from: b */
    final /* synthetic */ int f1772b;
    /* renamed from: c */
    final /* synthetic */ am f1773c;

    an(am amVar, ap apVar, int i) {
        this.f1773c = amVar;
        this.f1771a = apVar;
        this.f1772b = i;
    }

    public void run() {
        ao b = this.f1771a.m2024c();
        if (!b.mo4027b()) {
            this.f1773c.f1767a.mo4172d("CountdownManager", "Ending countdown for " + this.f1771a.m2019a());
        } else if (this.f1773c.f1770d.get() == this.f1772b) {
            try {
                b.mo4026a();
            } catch (Throwable th) {
                this.f1773c.f1767a.mo4174e("CountdownManager", "Encountered error on countdown step for: " + this.f1771a.m2019a(), th);
            }
            this.f1773c.m2011a(this.f1771a, this.f1772b);
        } else {
            this.f1773c.f1767a.mo4178w("CountdownManager", "Killing duplicate countdown from previous generation: " + this.f1771a.m2019a());
        }
    }
}
