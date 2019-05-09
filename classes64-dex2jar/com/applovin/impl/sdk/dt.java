// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.LinkedList;
import java.util.Queue;

class dt
{
    private int a;
    private final Queue<cj> b;
    private final Object c;
    
    dt(final int n) {
        this.a(n);
        this.b = new LinkedList<cj>();
        this.c = new Object();
    }
    
    int a() {
        synchronized (this.c) {
            return this.b.size();
        }
    }
    
    void a(int a) {
        if (a > 25) {
            a = 25;
        }
        this.a = a;
    }
    
    void a(final cj cj) {
        synchronized (this.c) {
            if (this.a() <= 25) {
                this.b.offer(cj);
            }
        }
    }
    
    int b() {
        return this.a;
    }
    
    boolean c() {
        while (true) {
            synchronized (this.c) {
                if (this.a() >= this.a) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean d() {
        while (true) {
            synchronized (this.c) {
                if (this.a() == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    cj e() {
        while (true) {
            try {
                synchronized (this.c) {
                    if (!this.d()) {
                        return this.b.poll();
                    }
                }
            }
            catch (Exception ex) {
                return null;
            }
            return null;
        }
    }
    
    cj f() {
        synchronized (this.c) {
            return this.b.peek();
        }
    }
}
