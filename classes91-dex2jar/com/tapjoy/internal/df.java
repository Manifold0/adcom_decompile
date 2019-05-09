// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class df implements di
{
    private final ReentrantLock a;
    private final a b;
    private final a c;
    private di.a d;
    private boolean e;
    
    public df() {
        this.a = new ReentrantLock();
        this.b = new a((byte)0);
        this.c = new a((byte)0);
        this.d = di.a.a;
        this.e = false;
    }
    
    private dh g() {
        this.a.lock();
    Block_6_Outer:
        while (true) {
            try {
                if (this.d == di.a.a) {
                    this.d = di.a.e;
                    this.b.a(di.a.e);
                    this.c.a(di.a.e);
                }
                else {
                    if (this.d != di.a.b) {
                        break Block_6_Outer;
                    }
                    this.e = true;
                    this.b.a(di.a.d);
                }
                this.a.unlock();
                return this.c;
            }
            catch (Throwable t) {
                this.a(t);
                this.a.unlock();
                return this.c;
                while (true) {
                    this.d = di.a.d;
                    this.b();
                    continue Block_6_Outer;
                    continue;
                }
            }
            // iftrue(Label_0046:, this.d != a.c)
            finally {
                this.a.unlock();
            }
            break;
        }
    }
    
    protected abstract void a();
    
    protected final void a(final Throwable t) {
        while (true) {
            cs.a(t);
            this.a.lock();
            while (true) {
                Label_0096: {
                    try {
                        if (this.d == di.a.b) {
                            this.b.a(t);
                            this.c.a(new Exception("Service failed to start.", t));
                        }
                        else {
                            if (this.d != di.a.d) {
                                break Label_0096;
                            }
                            this.c.a(t);
                        }
                        this.d = di.a.f;
                        return;
                    }
                    finally {
                        this.a.unlock();
                    }
                }
                if (this.d == di.a.c) {
                    this.c.a(new Exception("Service failed while running", t));
                    continue;
                }
                if (this.d == di.a.a || this.d == di.a.e) {
                    break;
                }
                continue;
            }
        }
        throw new IllegalStateException("Failed while in state:" + this.d, t);
    }
    
    protected abstract void b();
    
    protected final void c() {
        this.a.lock();
        try {
            if (this.d != di.a.b) {
                final IllegalStateException ex = new IllegalStateException("Cannot notifyStarted() when the service is " + this.d);
                this.a(ex);
                throw ex;
            }
        }
        finally {
            this.a.unlock();
        }
        this.d = di.a.c;
        if (this.e) {
            this.g();
        }
        else {
            this.b.a(di.a.c);
        }
        this.a.unlock();
    }
    
    protected final void d() {
        this.a.lock();
        try {
            if (this.d != di.a.d && this.d != di.a.c) {
                final IllegalStateException ex = new IllegalStateException("Cannot notifyStopped() when the service is " + this.d);
                this.a(ex);
                throw ex;
            }
        }
        finally {
            this.a.unlock();
        }
        this.d = di.a.e;
        this.c.a(di.a.e);
        this.a.unlock();
    }
    
    @Override
    public final dh e() {
        this.a.lock();
        try {
            if (this.d == di.a.a) {
                this.d = di.a.b;
                this.a();
            }
            this.a.unlock();
            return this.b;
        }
        catch (Throwable t) {
            this.a(t);
            this.a.unlock();
            return this.b;
        }
        finally {
            this.a.unlock();
        }
    }
    
    @Override
    public final di.a f() {
        this.a.lock();
        try {
            if (this.e && this.d == di.a.b) {
                return di.a.d;
            }
            return this.d;
        }
        finally {
            this.a.unlock();
        }
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.f() + "]";
    }
    
    final class a extends de
    {
        private a() {
        }
        
        private di.a a(final long n, final TimeUnit timeUnit) {
            try {
                return (di.a)super.get(n, timeUnit);
            }
            catch (TimeoutException ex) {
                throw new TimeoutException(df.this.toString());
            }
        }
    }
}
