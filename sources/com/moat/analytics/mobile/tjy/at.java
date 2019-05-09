package com.moat.analytics.mobile.tjy;

import java.util.concurrent.ThreadFactory;

class at implements ThreadFactory {
    /* renamed from: a */
    final /* synthetic */ as f6661a;

    at(as asVar) {
        this.f6661a = asVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "MoatStatus");
        thread.setDaemon(true);
        return thread;
    }
}
