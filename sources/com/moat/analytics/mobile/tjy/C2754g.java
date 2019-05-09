package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.g */
class C2754g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2743f f6713a;

    C2754g(C2743f c2743f) {
        this.f6713a = c2743f;
    }

    public void run() {
        try {
            this.f6713a.m6819a("Shutting down.");
            this.f6713a.f6641l.m6805b();
            this.f6713a.f6641l = null;
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
