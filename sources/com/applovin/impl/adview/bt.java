package com.applovin.impl.adview;

class bt implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f1818a;
    /* renamed from: b */
    final /* synthetic */ int f1819b;
    /* renamed from: c */
    final /* synthetic */ bs f1820c;

    bt(bs bsVar, int i, int i2) {
        this.f1820c = bsVar;
        this.f1818a = i;
        this.f1819b = i2;
    }

    public void run() {
        this.f1820c.f1817a.f1816a.logger.mo4173e("InterActivity", "Media player error (" + this.f1818a + "," + this.f1819b + ").");
        this.f1820c.f1817a.f1816a.handleMediaError();
    }
}
