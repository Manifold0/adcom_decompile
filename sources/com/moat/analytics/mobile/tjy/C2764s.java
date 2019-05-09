package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.s */
class C2764s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2759n f6734a;

    C2764s(C2759n c2759n) {
        this.f6734a = c2759n;
    }

    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new C2765t(this));
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }
}
