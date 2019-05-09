package com.moat.analytics.mobile.tjy;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

class as implements ap {
    /* renamed from: a */
    private static final AtomicReference f6656a = new AtomicReference();
    /* renamed from: b */
    private static final Queue f6657b = new ConcurrentLinkedQueue();
    /* renamed from: c */
    private static volatile ar f6658c = ar.OFF;
    /* renamed from: d */
    private static volatile boolean f6659d = false;
    /* renamed from: e */
    private static volatile int f6660e = 200;

    as(aa aaVar) {
        if (f6656a.get() == null) {
            if (f6656a.compareAndSet(null, Executors.newSingleThreadExecutor(new at(this)))) {
                ((ExecutorService) f6656a.get()).submit(new av("TJY", aaVar, new au(this), null));
            }
        }
    }

    /* renamed from: g */
    private void m6857g() {
        synchronized (f6657b) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f6657b.iterator();
            while (it.hasNext()) {
                aq aqVar = (aq) it.next();
                if (aqVar.mo6111c()) {
                    it.remove();
                } else if (currentTimeMillis - aqVar.mo6112d() >= 300000) {
                    it.remove();
                }
            }
            if (f6657b.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f6657b.remove();
                }
            }
        }
    }

    /* renamed from: a */
    public ar mo6103a() {
        return f6658c;
    }

    /* renamed from: a */
    public void mo6104a(aq aqVar) {
        m6857g();
        f6657b.add(aqVar);
    }

    /* renamed from: b */
    public boolean mo6105b() {
        return f6659d;
    }

    /* renamed from: c */
    public int mo6106c() {
        return f6660e;
    }
}
