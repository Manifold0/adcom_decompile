// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

final class hz implements hv
{
    public final hu a;
    public final ie b;
    boolean c;
    
    hz(final ie b) {
        this.a = new hu();
        if (b == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = b;
    }
    
    private hv b() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        final hu a = this.a;
        final long b = a.b;
        long n;
        if (b == 0L) {
            n = 0L;
        }
        else {
            final ib g = a.a.g;
            n = b;
            if (g.c < 8192) {
                n = b;
                if (g.e) {
                    n = b - (g.c - g.b);
                }
            }
        }
        if (n > 0L) {
            this.b.a(this.a, n);
        }
        return this;
    }
    
    @Override
    public final hv a() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        final long b = this.a.b;
        if (b > 0L) {
            this.b.a(this.a, b);
        }
        return this;
    }
    
    @Override
    public final void a(final hu hu, final long n) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(hu, n);
        this.b();
    }
    
    @Override
    public final hv b(final hx hx) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(hx);
        return this.b();
    }
    
    @Override
    public final hv b(final String s) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(s);
        return this.b();
    }
    
    @Override
    public final void close() {
        if (!this.c) {
            Throwable t2;
            final Throwable t = t2 = null;
            while (true) {
                try {
                    if (this.a.b > 0L) {
                        this.b.a(this.a, this.a.b);
                        t2 = t;
                    }
                    while (true) {
                        try {
                            this.b.close();
                            final Throwable t3 = t2;
                            this.c = true;
                            if (t3 != null) {
                                ih.a(t3);
                            }
                        }
                        catch (Throwable t4) {
                            Throwable t3 = t2;
                            if (t2 == null) {
                                t3 = t4;
                            }
                            continue;
                        }
                        break;
                    }
                }
                catch (Throwable t2) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public final hv d(final int n) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(n);
        return this.b();
    }
    
    @Override
    public final hv e(final int n) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(n);
        return this.b();
    }
    
    @Override
    public final hv f(final long n) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.e(n);
        return this.b();
    }
    
    @Override
    public final void flush() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0L) {
            this.b.a(this.a, this.a.b);
        }
        this.b.flush();
    }
    
    @Override
    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
