package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.o */
class C2760o implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2759n f6730a;

    C2760o(C2759n c2759n) {
        this.f6730a = c2759n;
    }

    public void run() {
        try {
            this.f6730a.m6943e();
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
