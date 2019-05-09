package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class hp implements Runnable {
    /* renamed from: a */
    private final long f7831a = 300;

    /* renamed from: a */
    public abstract boolean mo6293a();

    public void run() {
        long elapsedRealtime = SystemClock.elapsedRealtime() + this.f7831a;
        while (!mo6293a() && elapsedRealtime - SystemClock.elapsedRealtime() > 0) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
