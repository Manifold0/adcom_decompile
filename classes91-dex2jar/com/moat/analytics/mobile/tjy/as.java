// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

class as implements ap
{
    private static final AtomicReference a;
    private static final Queue b;
    private static volatile ar c;
    private static volatile boolean d;
    private static volatile int e;
    
    static {
        a = new AtomicReference();
        b = new ConcurrentLinkedQueue();
        as.c = ar.a;
        as.d = false;
        as.e = 200;
    }
    
    as(final aa aa) {
        if (as.a.get() == null && as.a.compareAndSet(null, Executors.newSingleThreadExecutor(new at(this)))) {
            as.a.get().submit(new av("TJY", aa, new au(this), null));
        }
    }
    
    private void g() {
        while (true) {
            while (true) {
                final long currentTimeMillis;
                final Iterator iterator;
                aq aq = null;
                Label_0072: {
                    synchronized (as.b) {
                        currentTimeMillis = System.currentTimeMillis();
                        iterator = as.b.iterator();
                        while (iterator.hasNext()) {
                            aq = iterator.next();
                            if (!aq.c()) {
                                break Label_0072;
                            }
                            iterator.remove();
                        }
                        break;
                    }
                }
                if (currentTimeMillis - aq.d() >= 300000L) {
                    iterator.remove();
                    continue;
                }
                continue;
            }
        }
        if (as.b.size() >= 15) {
            for (int i = 0; i < 5; ++i) {
                as.b.remove();
            }
        }
    }
    // monitorexit(queue)
    
    @Override
    public ar a() {
        return as.c;
    }
    
    @Override
    public void a(final aq aq) {
        this.g();
        as.b.add(aq);
    }
    
    @Override
    public boolean b() {
        return as.d;
    }
    
    @Override
    public int c() {
        return as.e;
    }
}
