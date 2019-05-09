package com.applovin.impl.adview;

class bh implements ao {
    /* renamed from: a */
    final /* synthetic */ az f1802a;

    bh(az azVar) {
        this.f1802a = azVar;
    }

    /* renamed from: a */
    public void mo4026a() {
        if (this.f1802a.f1528H == null) {
            return;
        }
        if (this.f1802a.shouldContinueFullLengthVideoCountdown()) {
            this.f1802a.f1528H.setProgress((int) (((float) this.f1802a.settingsProxy.af()) * (((float) this.f1802a.videoView.getCurrentPosition()) / ((float) this.f1802a.videoView.getDuration()))));
            return;
        }
        this.f1802a.f1528H.setVisibility(8);
    }

    /* renamed from: b */
    public boolean mo4027b() {
        return this.f1802a.shouldContinueFullLengthVideoCountdown();
    }
}
