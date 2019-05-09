package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.c */
class C0203c {
    /* renamed from: a */
    protected C0257o f10a = null;
    /* renamed from: b */
    protected C0230f f11b = null;
    /* renamed from: c */
    protected Context f12c = null;
    /* renamed from: d */
    protected String f13d = null;
    /* renamed from: e */
    protected String f14e = "";

    C0203c(String str, C0230f c0230f) {
        this.f14e = str;
        this.f11b = c0230f;
    }

    protected void reportError(String str) {
        if (this.f11b != null) {
            this.f11b.reportError(this.f14e + " Error [" + this.f13d + RequestParameters.RIGHT_BRACKETS, str);
        } else {
            C0243g.Log(6, this.f14e + " Error [" + this.f13d + "]: " + str);
        }
    }

    protected void runOnUiThread(Runnable runnable) {
        if (this.f12c instanceof Activity) {
            ((Activity) this.f12c).runOnUiThread(runnable);
        } else {
            C0243g.Log(5, "Not running " + this.f14e + " from an Activity; Ignoring execution request...");
        }
    }

    protected boolean runOnUiThreadWithSync(final Runnable runnable) {
        boolean z = false;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0203c f185c;

            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    this.f185c.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                } finally {
                    semaphore.release();
                }
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                z = true;
            } else {
                reportError("Timeout waiting for vr state change!");
            }
        } catch (InterruptedException e) {
            reportError("Interrupted while trying to acquire sync lock. " + e.getLocalizedMessage());
        }
        return z;
    }
}
