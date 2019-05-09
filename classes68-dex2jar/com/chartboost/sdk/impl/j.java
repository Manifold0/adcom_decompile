// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.Libraries.i;
import java.util.concurrent.atomic.AtomicInteger;

class j implements Comparable<j>
{
    final int a;
    final String b;
    final String c;
    final String d;
    final AtomicInteger e;
    final AtomicInteger f;
    private final i g;
    private final AtomicReference<h> h;
    private final long i;
    
    j(final i g, final int a, final String b, final String c, final String d, final AtomicInteger e, final AtomicReference<h> h, final long i, final AtomicInteger f) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.h = h;
        this.i = i;
        this.f = f;
        e.incrementAndGet();
    }
    
    public int a(final j j) {
        return this.a - j.a;
    }
    
    void a(final Executor executor, final boolean b) {
        if (this.e.decrementAndGet() == 0 || !b) {
            final h h = this.h.getAndSet(null);
            if (h != null) {
                executor.execute(new com.chartboost.sdk.impl.i(h, b, (int)TimeUnit.NANOSECONDS.toMillis(this.g.b() - this.i), this.f.get()));
            }
        }
    }
}
