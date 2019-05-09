// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public abstract class de implements dh
{
    private final a a;
    private final dg b;
    
    public de() {
        this.a = new a();
        this.b = new dg();
    }
    
    protected final boolean a(final Object o) {
        final boolean a = this.a.a(o, null, 2);
        if (a) {
            this.b.a();
        }
        return a;
    }
    
    protected final boolean a(final Throwable t) {
        final boolean a = this.a.a(null, (Throwable)cs.a(t), 2);
        if (a) {
            this.b.a();
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
        return a;
    }
    
    @Override
    public boolean cancel(final boolean b) {
        if (!this.a.a(null, null, 4)) {
            return false;
        }
        this.b.a();
        return true;
    }
    
    @Override
    public Object get() {
        final a a = this.a;
        a.acquireSharedInterruptibly(-1);
        return a.a();
    }
    
    @Override
    public Object get(final long n, final TimeUnit timeUnit) {
        final a a = this.a;
        if (!a.tryAcquireSharedNanos(-1, timeUnit.toNanos(n))) {
            throw new TimeoutException("Timeout waiting for task.");
        }
        return a.a();
    }
    
    @Override
    public boolean isCancelled() {
        return this.a.c();
    }
    
    @Override
    public boolean isDone() {
        return this.a.b();
    }
    
    static final class a extends AbstractQueuedSynchronizer
    {
        private Object a;
        private Throwable b;
        
        final Object a() {
            final int state = this.getState();
            switch (state) {
                default: {
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
                }
                case 2: {
                    if (this.b != null) {
                        throw new ExecutionException(this.b);
                    }
                    return this.a;
                }
                case 4: {
                    throw new CancellationException("Task was cancelled.");
                }
            }
        }
        
        final boolean a(final Object a, final Throwable b, final int n) {
            final boolean compareAndSetState = this.compareAndSetState(0, 1);
            if (compareAndSetState) {
                this.a = a;
                this.b = b;
                this.releaseShared(n);
            }
            else if (this.getState() == 1) {
                this.acquireShared(-1);
                return compareAndSetState;
            }
            return compareAndSetState;
        }
        
        final boolean b() {
            return (this.getState() & 0x6) != 0x0;
        }
        
        final boolean c() {
            return this.getState() == 4;
        }
        
        @Override
        protected final int tryAcquireShared(final int n) {
            if (this.b()) {
                return 1;
            }
            return -1;
        }
        
        @Override
        protected final boolean tryReleaseShared(final int state) {
            this.setState(state);
            return true;
        }
    }
}
