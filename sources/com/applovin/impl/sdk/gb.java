package com.applovin.impl.sdk;

import java.util.TimerTask;

class gb extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ ga f2563a;

    gb(ga gaVar) {
        this.f2563a = gaVar;
    }

    public void run() {
        try {
            this.f2563a.f2560e.run();
            synchronized (this.f2563a.f2562g) {
                this.f2563a.f2557b = null;
            }
        } catch (Throwable th) {
            synchronized (this.f2563a.f2562g) {
                this.f2563a.f2557b = null;
            }
        }
    }
}
