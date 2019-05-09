package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

class aw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ar f6668a;
    /* renamed from: b */
    final /* synthetic */ av f6669b;

    aw(av avVar, ar arVar) {
        this.f6669b = avVar;
        this.f6668a = arVar;
    }

    public void run() {
        try {
            this.f6669b.f6666d.mo6108a(this.f6668a);
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
