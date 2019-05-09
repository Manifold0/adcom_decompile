// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.InterruptedIOException;

public class ig
{
    public static final ig a;
    private boolean b;
    private long c;
    
    static {
        a = new ig() {
            @Override
            public final void a() {
            }
        };
    }
    
    public void a() {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.b && this.c - System.nanoTime() <= 0L) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
