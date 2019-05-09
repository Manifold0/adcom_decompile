// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class gn
{
    protected static a a;
    private static gn b;
    
    protected static void a(final gn b) {
        synchronized (gn.class) {
            gn.b = b;
            final a a = gn.a;
            if (a != null) {
                gn.a = null;
                b.a(a);
            }
        }
    }
    
    public static void a(final String s, final String s2) {
        synchronized (gn.class) {
            final a a = new a(s, s2);
            if (gn.b != null) {
                gn.a = null;
                gn.b.a(a);
            }
            else {
                gn.a = a;
            }
        }
    }
    
    public static boolean c() {
        if (gn.b == null || !gn.b.b()) {
            final a a = gn.a;
            if (a == null || a.d.a()) {
                return false;
            }
        }
        return true;
    }
    
    public abstract void a(final a p0);
    
    public abstract boolean b();
    
    public static final class a
    {
        public final String a;
        public final String b;
        public final long c;
        public final el d;
        
        public a(final String a, final String b) {
            this.a = a;
            this.b = b;
            this.c = SystemClock.elapsedRealtime();
            this.d = new el(60000L);
        }
    }
}
