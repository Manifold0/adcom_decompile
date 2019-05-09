package com.tapjoy.internal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public final class dg {
    /* renamed from: a */
    private static final Logger f7326a = Logger.getLogger(dg.class.getName());
    /* renamed from: b */
    private final Queue f7327b = new LinkedList();
    /* renamed from: c */
    private boolean f7328c = false;

    /* renamed from: com.tapjoy.internal.dg$a */
    static class C2860a {
        /* renamed from: a */
        final Runnable f7324a;
        /* renamed from: b */
        final Executor f7325b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final void m7384a() {
        /*
        r6 = this;
        r1 = r6.f7327b;
        monitor-enter(r1);
        r0 = r6.f7328c;	 Catch:{ all -> 0x004b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = 1;
        r6.f7328c = r0;	 Catch:{ all -> 0x004b }
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
    L_0x000d:
        r0 = r6.f7327b;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0008;
    L_0x0015:
        r0 = r6.f7327b;
        r0 = r0.poll();
        r0 = (com.tapjoy.internal.dg.C2860a) r0;
        r1 = r0.f7325b;	 Catch:{ RuntimeException -> 0x0025 }
        r2 = r0.f7324a;	 Catch:{ RuntimeException -> 0x0025 }
        r1.execute(r2);	 Catch:{ RuntimeException -> 0x0025 }
        goto L_0x000d;
    L_0x0025:
        r1 = move-exception;
        r2 = f7326a;
        r3 = java.util.logging.Level.SEVERE;
        r4 = new java.lang.StringBuilder;
        r5 = "RuntimeException while executing runnable ";
        r4.<init>(r5);
        r5 = r0.f7324a;
        r4 = r4.append(r5);
        r5 = " with executor ";
        r4 = r4.append(r5);
        r0 = r0.f7325b;
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.log(r3, r0, r1);
        goto L_0x000d;
    L_0x004b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dg.a():void");
    }
}
