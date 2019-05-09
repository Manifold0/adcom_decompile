package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

class dt {
    /* renamed from: a */
    private int f2351a;
    /* renamed from: b */
    private final Queue<cj> f2352b = new LinkedList();
    /* renamed from: c */
    private final Object f2353c = new Object();

    dt(int i) {
        m2620a(i);
    }

    /* renamed from: a */
    int m2619a() {
        int size;
        synchronized (this.f2353c) {
            size = this.f2352b.size();
        }
        return size;
    }

    /* renamed from: a */
    void m2620a(int i) {
        if (i > 25) {
            i = 25;
        }
        this.f2351a = i;
    }

    /* renamed from: a */
    void m2621a(cj cjVar) {
        synchronized (this.f2353c) {
            if (m2619a() <= 25) {
                this.f2352b.offer(cjVar);
            }
        }
    }

    /* renamed from: b */
    int m2622b() {
        return this.f2351a;
    }

    /* renamed from: c */
    boolean m2623c() {
        boolean z;
        synchronized (this.f2353c) {
            z = m2619a() >= this.f2351a;
        }
        return z;
    }

    /* renamed from: d */
    boolean m2624d() {
        boolean z;
        synchronized (this.f2353c) {
            z = m2619a() == 0;
        }
        return z;
    }

    /* renamed from: e */
    cj m2625e() {
        try {
            cj cjVar;
            synchronized (this.f2353c) {
                cjVar = !m2624d() ? (cj) this.f2352b.poll() : null;
            }
            return cjVar;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: f */
    cj m2626f() {
        cj cjVar;
        synchronized (this.f2353c) {
            cjVar = (cj) this.f2352b.peek();
        }
        return cjVar;
    }
}
