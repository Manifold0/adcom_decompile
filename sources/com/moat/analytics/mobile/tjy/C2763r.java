package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.r */
class C2763r implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2762q f6733a;

    C2763r(C2762q c2762q) {
        this.f6733a = c2762q;
    }

    public void run() {
        try {
            this.f6733a.f6732a.m6937b();
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
