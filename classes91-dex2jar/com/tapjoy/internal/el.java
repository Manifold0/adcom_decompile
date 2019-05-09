// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;

public final class el
{
    public static final el a;
    public final long b;
    public long c;
    
    static {
        a = new el(-1L);
    }
    
    public el() {
        this.b = 3600000L;
        try {
            this.c = SystemClock.elapsedRealtime() - 3600000L;
        }
        catch (NullPointerException ex) {
            this.c = -1L;
        }
    }
    
    public el(final long b) {
        this.b = b;
        this.c = SystemClock.elapsedRealtime();
    }
    
    public final boolean a() {
        try {
            return SystemClock.elapsedRealtime() - this.c > this.b;
        }
        catch (NullPointerException ex) {
            return true;
        }
    }
    
    public final boolean a(final long n) {
        try {
            return SystemClock.elapsedRealtime() - this.c + n > this.b;
        }
        catch (NullPointerException ex) {
            return true;
        }
    }
}
