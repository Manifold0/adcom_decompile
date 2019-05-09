package com.applovin.impl.adview;

class bw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f1823a;
    /* renamed from: b */
    final /* synthetic */ int f1824b;
    /* renamed from: c */
    final /* synthetic */ bv f1825c;

    bw(bv bvVar, int i, int i2) {
        this.f1825c = bvVar;
        this.f1823a = i;
        this.f1824b = i2;
    }

    public void run() {
        this.f1825c.f1822a.logger.mo4173e("InterActivity", "Video view error (" + this.f1823a + "," + this.f1824b + ").");
        this.f1825c.f1822a.handleMediaError();
    }
}
