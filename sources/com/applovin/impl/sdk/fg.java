package com.applovin.impl.sdk;

import java.lang.Thread.UncaughtExceptionHandler;

class fg implements UncaughtExceptionHandler {
    /* renamed from: a */
    final /* synthetic */ ff f2510a;

    fg(ff ffVar) {
        this.f2510a = ffVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.f2510a.f2508a.f2497c.mo4174e("TaskManager", "Caught unhandled exception", th);
    }
}
