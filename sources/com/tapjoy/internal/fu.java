package com.tapjoy.internal;

import android.app.Activity;
import android.opengl.GLSurfaceView;

public final class fu {
    /* renamed from: a */
    public static final bf f7795a = new C29291();
    /* renamed from: b */
    private static Activity f7796b;
    /* renamed from: c */
    private static final cd f7797c = new cd();
    /* renamed from: d */
    private static final cd f7798d = new cd();

    /* renamed from: com.tapjoy.internal.fu$1 */
    static class C29291 implements bf {
        C29291() {
        }

        /* renamed from: a */
        public final boolean mo6287a(Runnable runnable) {
            GLSurfaceView gLSurfaceView = (GLSurfaceView) fu.f7797c.m7315a();
            if (gLSurfaceView == null) {
                return false;
            }
            gLSurfaceView.queueEvent(runnable);
            return true;
        }
    }

    /* renamed from: com.tapjoy.internal.fu$2 */
    static class C29302 implements Runnable {
        C29302() {
        }

        public final void run() {
            Thread currentThread = Thread.currentThread();
            new Object[1][0] = currentThread;
            fu.f7798d.m7316a(currentThread);
        }
    }

    /* renamed from: a */
    static void m7789a(GLSurfaceView gLSurfaceView) {
        new Object[1][0] = gLSurfaceView;
        f7797c.m7316a(gLSurfaceView);
        gLSurfaceView.queueEvent(new C29302());
    }

    /* renamed from: a */
    public static Activity m7788a() {
        Activity activity = f7796b;
        if (activity == null) {
            return C2854d.m7347a();
        }
        return activity;
    }

    /* renamed from: b */
    public static Thread m7790b() {
        return (Thread) f7798d.m7315a();
    }
}
