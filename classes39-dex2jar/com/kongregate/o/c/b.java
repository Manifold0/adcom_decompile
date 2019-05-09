// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadFactory;

public class b implements ThreadFactory
{
    private static final AtomicLong a;
    private final AtomicLong b;
    private final String c;
    
    static {
        a = new AtomicLong(0L);
    }
    
    public b(final String s) {
        this.b = new AtomicLong(0L);
        this.c = s + "-" + com.kongregate.o.c.b.a.incrementAndGet() + "-";
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, this.c + "-" + this.b.incrementAndGet());
    }
}
