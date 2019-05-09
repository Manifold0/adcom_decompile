package com.applovin.impl.adview;

import java.util.concurrent.TimeUnit;

class bg implements ao {
    /* renamed from: a */
    final /* synthetic */ long f1800a;
    /* renamed from: b */
    final /* synthetic */ az f1801b;

    bg(az azVar, long j) {
        this.f1801b = azVar;
        this.f1800a = j;
    }

    /* renamed from: a */
    public void mo4026a() {
        if (this.f1801b.f1523C != null) {
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds(this.f1800a - ((long) this.f1801b.videoView.getCurrentPosition()));
            if (toSeconds <= 0) {
                this.f1801b.f1523C.setVisibility(8);
                this.f1801b.f1546p = true;
            } else if (this.f1801b.m1745r()) {
                this.f1801b.f1523C.m1987a((int) toSeconds);
            }
        }
    }

    /* renamed from: b */
    public boolean mo4027b() {
        return this.f1801b.m1745r();
    }
}
