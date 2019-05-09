package com.applovin.impl.adview;

class bk implements cp {
    /* renamed from: a */
    final /* synthetic */ az f1807a;

    bk(az azVar) {
        this.f1807a = azVar;
    }

    /* renamed from: a */
    public void mo4028a(cn cnVar) {
        this.f1807a.logger.mo4172d("InterActivity", "Clicking through from video button...");
        this.f1807a.clickThroughFromVideo();
    }

    /* renamed from: b */
    public void mo4029b(cn cnVar) {
        this.f1807a.logger.mo4172d("InterActivity", "Closing ad from video button...");
        this.f1807a.dismiss();
    }

    /* renamed from: c */
    public void mo4030c(cn cnVar) {
        this.f1807a.logger.mo4172d("InterActivity", "Skipping video from video button...");
        this.f1807a.skipVideo();
    }
}
