// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public class gd implements fr
{
    private static final gd a;
    private final fr b;
    private final bf c;
    
    static {
        a = new gd() {
            @Override
            public final void a(final String s) {
            }
            
            @Override
            public final void a(final String s, final fo fo) {
            }
            
            @Override
            public final void a(final String s, final String s2, final fo fo) {
            }
            
            @Override
            public final void b(final String s) {
            }
            
            @Override
            public final void c(final String s) {
            }
            
            @Override
            public final void d(final String s) {
            }
        };
    }
    
    private gd() {
        this.b = null;
        this.c = null;
    }
    
    private gd(final fr b) {
        this.b = b;
        final Looper myLooper = Looper.myLooper();
        Handler a;
        if (myLooper != null) {
            cs.a(myLooper);
            if (myLooper == Looper.getMainLooper()) {
                a = x.a();
            }
            else {
                a = new Handler(myLooper);
            }
        }
        else {
            a = null;
        }
        if (a != null) {
            this.c = x.a(a);
            a.getLooper();
            return;
        }
        if (Thread.currentThread() == fu.b()) {
            this.c = fu.a;
            return;
        }
        this.c = x.a(x.a());
    }
    
    public static gd a(final fr fr) {
        int n;
        if (!(fr instanceof gd)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            throw new IllegalArgumentException();
        }
        if (fr != null) {
            return new gd(fr);
        }
        return gd.a;
    }
    
    @Override
    public void a(final String s) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.a(s);
            }
        });
    }
    
    @Override
    public void a(final String s, final fo fo) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.a(s, fo);
            }
        });
    }
    
    @Override
    public void a(final String s, final String s2, final fo fo) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.a(s, s2, fo);
            }
        });
    }
    
    @Override
    public void b(final String s) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.b(s);
            }
        });
    }
    
    @Override
    public void c(final String s) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.c(s);
            }
        });
    }
    
    @Override
    public void d(final String s) {
        this.c.a(new Runnable() {
            @Override
            public final void run() {
                gd.this.b.d(s);
            }
        });
    }
}
