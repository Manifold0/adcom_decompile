// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import java.util.Map;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class ad<T>
{
    public final String b;
    public final String c;
    public final int d;
    public final AtomicInteger e;
    public final File f;
    public long g;
    public long h;
    public long i;
    public int j;
    
    public ad(final String b, final String c, final int d, final File f) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = new AtomicInteger();
        this.f = f;
        this.g = 0L;
        this.h = 0L;
        this.i = 0L;
        this.j = 0;
    }
    
    public ae a() {
        return new ae(null, null, null);
    }
    
    public af<T> a(final ag ag) {
        return af.a((T)null);
    }
    
    public void a(final CBError cbError, final ag ag) {
    }
    
    public void a(final T t, final ag ag) {
    }
    
    public boolean b() {
        return this.e.compareAndSet(0, -1);
    }
}
