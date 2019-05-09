package com.tapjoy.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public abstract class de implements dh {
    /* renamed from: a */
    private final C2858a f7321a = new C2858a();
    /* renamed from: b */
    private final dg f7322b = new dg();

    /* renamed from: com.tapjoy.internal.de$a */
    static final class C2858a extends AbstractQueuedSynchronizer {
        /* renamed from: a */
        private Object f7319a;
        /* renamed from: b */
        private Throwable f7320b;

        C2858a() {
        }

        protected final int tryAcquireShared(int ignored) {
            if (m7379b()) {
                return 1;
            }
            return -1;
        }

        protected final boolean tryReleaseShared(int finalState) {
            setState(finalState);
            return true;
        }

        /* renamed from: a */
        final Object m7377a() {
            int state = getState();
            switch (state) {
                case 2:
                    if (this.f7320b == null) {
                        return this.f7319a;
                    }
                    throw new ExecutionException(this.f7320b);
                case 4:
                    throw new CancellationException("Task was cancelled.");
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        /* renamed from: b */
        final boolean m7379b() {
            return (getState() & 6) != 0;
        }

        /* renamed from: c */
        final boolean m7380c() {
            return getState() == 4;
        }

        /* renamed from: a */
        final boolean m7378a(Object obj, Throwable th, int i) {
            boolean compareAndSetState = compareAndSetState(0, 1);
            if (compareAndSetState) {
                this.f7319a = obj;
                this.f7320b = th;
                releaseShared(i);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return compareAndSetState;
        }
    }

    public Object get(long timeout, TimeUnit unit) {
        C2858a c2858a = this.f7321a;
        if (c2858a.tryAcquireSharedNanos(-1, unit.toNanos(timeout))) {
            return c2858a.m7377a();
        }
        throw new TimeoutException("Timeout waiting for task.");
    }

    public Object get() {
        C2858a c2858a = this.f7321a;
        c2858a.acquireSharedInterruptibly(-1);
        return c2858a.m7377a();
    }

    public boolean isDone() {
        return this.f7321a.m7379b();
    }

    public boolean isCancelled() {
        return this.f7321a.m7380c();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!this.f7321a.m7378a(null, null, 4)) {
            return false;
        }
        this.f7322b.m7384a();
        return true;
    }

    /* renamed from: a */
    protected final boolean m7381a(Object obj) {
        boolean a = this.f7321a.m7378a(obj, null, 2);
        if (a) {
            this.f7322b.m7384a();
        }
        return a;
    }

    /* renamed from: a */
    protected final boolean m7382a(Throwable th) {
        boolean a = this.f7321a.m7378a(null, (Throwable) cs.m7336a(th), 2);
        if (a) {
            this.f7322b.m7384a();
        }
        if (!(th instanceof Error)) {
            return a;
        }
        throw ((Error) th);
    }
}
