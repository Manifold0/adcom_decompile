// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.c;

import java.util.concurrent.Executors;
import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;

public class f
{
    static final int a;
    static final ExecutorService b;
    private static volatile boolean c;
    private final Bitmap d;
    private Bitmap e;
    private final a f;
    
    static {
        a = Runtime.getRuntime().availableProcessors();
        b = Executors.newFixedThreadPool(f.a);
        f.c = true;
    }
    
    public f(final Bitmap d) {
        this.d = d;
        this.f = new d();
    }
    
    public Bitmap a() {
        return this.e;
    }
    
    public Bitmap a(final int n) {
        return this.e = this.f.a(this.d, (float)n);
    }
}
