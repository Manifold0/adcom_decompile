package com.applovin.impl.sdk;

import java.util.concurrent.ThreadFactory;

class ff implements ThreadFactory {
    /* renamed from: a */
    final /* synthetic */ fd f2508a;
    /* renamed from: b */
    private final String f2509b;

    public ff(fd fdVar, String str) {
        this.f2508a = fdVar;
        this.f2509b = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AppLovinSdk:" + this.f2509b + ":" + gd.m2937a(this.f2508a.f2496b.getSdkKey()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new fg(this));
        return thread;
    }
}
