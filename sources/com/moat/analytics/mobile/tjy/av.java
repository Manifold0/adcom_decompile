package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;

class av implements Runnable {
    /* renamed from: b */
    private static final long f6663b = 90000;
    /* renamed from: a */
    private final aa f6664a;
    /* renamed from: c */
    private final String f6665c;
    /* renamed from: d */
    private final ax f6666d;
    /* renamed from: e */
    private ar f6667e;

    private av(String str, aa aaVar, ax axVar) {
        this.f6667e = ar.OFF;
        this.f6664a = aaVar;
        this.f6666d = axVar;
        this.f6665c = "https://z.moatads.com/" + str + "/android/" + "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7".substring(0, 7) + "/status.json";
    }

    /* renamed from: a */
    private void m6865a() {
        long j = 0;
        while (true) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis < f6663b) {
                try {
                    Thread.sleep((10 + f6663b) - currentTimeMillis);
                } catch (InterruptedException e) {
                }
            }
            j = System.currentTimeMillis();
            ar b = m6866b();
            Handler handler = new Handler(Looper.getMainLooper());
            b.equals(this.f6667e);
            this.f6667e = b;
            handler.post(new aw(this, b));
        }
    }

    /* renamed from: b */
    private ar m6866b() {
        C2749a a = this.f6664a.mo6080a(this.f6665c + "?ts=" + System.currentTimeMillis() + "&v=1.7.10");
        if (!a.m6888c()) {
            return ar.OFF;
        }
        C2766u c2766u = new C2766u((String) a.m6886b());
        as.f6659d = c2766u.m6953a();
        as.f6660e = c2766u.m6955c();
        return c2766u.m6954b() ? ar.ON : ar.OFF;
    }

    public void run() {
        try {
            m6865a();
        } catch (Exception e) {
            this.f6667e = ar.OFF;
            C2747a.m6882a(e);
        }
    }
}
