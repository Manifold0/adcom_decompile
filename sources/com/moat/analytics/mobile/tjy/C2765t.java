package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.t */
class C2765t implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2764s f6735a;

    C2765t(C2764s c2764s) {
        this.f6735a = c2764s;
    }

    public void run() {
        try {
            this.f6735a.f6734a.m6939c();
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
