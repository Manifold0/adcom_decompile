// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;
import java.util.Hashtable;
import android.content.Context;
import java.util.Set;
import java.util.Map;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJEarnedCurrencyListener;
import android.opengl.GLSurfaceView;
import android.app.Activity;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;

public abstract class du
{
    private static final du b;
    private static du c;
    protected volatile boolean a;
    
    static {
        fa.a();
        es.a();
        du.c = (b = new dw());
    }
    
    du() {
        this.a = false;
    }
    
    public static du a() {
        return du.c;
    }
    
    public abstract TJPlacement a(final String p0, final TJPlacementListener p1);
    
    public abstract void a(final float p0);
    
    public abstract void a(final int p0);
    
    public abstract void a(final int p0, final TJAwardCurrencyListener p1);
    
    public abstract void a(final int p0, final TJSpendCurrencyListener p1);
    
    public abstract void a(final int p0, final String p1);
    
    public abstract void a(final Activity p0);
    
    public abstract void a(final GLSurfaceView p0);
    
    public abstract void a(final TJEarnedCurrencyListener p0);
    
    public abstract void a(final TJGetCurrencyBalanceListener p0);
    
    public abstract void a(final TJVideoListener p0);
    
    public abstract void a(final String p0);
    
    public abstract void a(final String p0, final long p1);
    
    public abstract void a(final String p0, final TJSetUserIDListener p1);
    
    public abstract void a(final String p0, final String p1);
    
    public abstract void a(final String p0, final String p1, final double p2, final String p3);
    
    public abstract void a(final String p0, final String p1, final long p2);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3, final long p4);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3, final String p4, final long p5);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3, final String p4, final long p5, final String p6, final long p7);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3, final String p4, final long p5, final String p6, final long p7, final String p8, final long p9);
    
    public abstract void a(final String p0, final String p1, final String p2, final String p3, final Map p4);
    
    public abstract void a(final Set p0);
    
    public abstract void a(final boolean p0);
    
    public abstract boolean a(final Context p0, final String p1);
    
    public abstract boolean a(final Context p0, final String p1, final Hashtable p2, final TJConnectListener p3);
    
    public abstract String b();
    
    public abstract void b(final int p0);
    
    public abstract void b(final Activity p0);
    
    public abstract void b(final String p0);
    
    public abstract void b(final String p0, final String p1, final String p2, final String p3);
    
    public abstract void b(final boolean p0);
    
    public abstract float c();
    
    public abstract void c(final Activity p0);
    
    public abstract void c(final String p0);
    
    public abstract void c(final boolean p0);
    
    public abstract void d();
    
    public abstract void d(final String p0);
    
    public abstract void e();
    
    public abstract void e(final String p0);
    
    public abstract Set f();
    
    public abstract void f(final String p0);
    
    public abstract String g(final String p0);
    
    public abstract void g();
    
    public abstract void h(final String p0);
    
    public abstract boolean h();
    
    public abstract boolean i();
}
