// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public final class dg
{
    private static final Logger a;
    private final Queue b;
    private boolean c;
    
    static {
        a = Logger.getLogger(dg.class.getName());
    }
    
    public dg() {
        this.b = new LinkedList();
        this.c = false;
    }
    
    public final void a() {
        Object b = this.b;
        synchronized (b) {
            if (this.c) {
                return;
            }
            this.c = true;
            // monitorexit(b)
            while (!this.b.isEmpty()) {
                b = this.b.poll();
                try {
                    ((a)b).b.execute(((a)b).a);
                }
                catch (RuntimeException ex) {
                    dg.a.log(Level.SEVERE, "RuntimeException while executing runnable " + ((a)b).a + " with executor " + ((a)b).b, ex);
                }
            }
        }
    }
    
    static final class a
    {
        final Runnable a;
        final Executor b;
    }
}
