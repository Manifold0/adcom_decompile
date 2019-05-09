package com.tapjoy.internal;

import java.io.InterruptedIOException;

public class ig {
    /* renamed from: a */
    public static final ig f8214a = new C29851();
    /* renamed from: b */
    private boolean f8215b;
    /* renamed from: c */
    private long f8216c;

    /* renamed from: com.tapjoy.internal.ig$1 */
    static class C29851 extends ig {
        C29851() {
        }

        /* renamed from: a */
        public final void mo6368a() {
        }
    }

    /* renamed from: a */
    public void mo6368a() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.f8215b && this.f8216c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
