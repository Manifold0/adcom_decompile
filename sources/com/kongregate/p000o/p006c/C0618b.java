package com.kongregate.p000o.p006c;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.kongregate.o.c.b */
public class C0618b implements ThreadFactory {
    /* renamed from: a */
    private static final AtomicLong f951a = new AtomicLong(0);
    /* renamed from: b */
    private final AtomicLong f952b = new AtomicLong(0);
    /* renamed from: c */
    private final String f953c;

    public C0618b(String str) {
        this.f953c = str + "-" + f951a.incrementAndGet() + "-";
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f953c + "-" + this.f952b.incrementAndGet());
    }
}
