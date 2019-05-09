// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.support.annotation.VisibleForTesting;
import android.os.Handler;

public class e
{
    private final Handler a;
    private final a b;
    private int c;
    private boolean d;
    private boolean e;
    
    public e(final int n, final a a) {
        this(n, a, new Handler());
    }
    
    @VisibleForTesting
    e(final int c, final a b, final Handler a) {
        this.d = false;
        this.c = c;
        this.b = b;
        this.a = a;
    }
    
    static /* synthetic */ void a(final e e) {
        --e.c;
        e.b.a(e.c);
        if (e.c == 0 && !e.e) {
            e.e = true;
            e.b.a();
            e.d = false;
        }
    }
    
    public boolean a() {
        if (this.d() && !this.e) {
            this.b.a();
        }
        if (this.d() || this.c()) {
            return false;
        }
        this.d = true;
        this.b.a(this.c);
        this.a.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                if (com.facebook.ads.internal.w.b.e.this.c()) {
                    com.facebook.ads.internal.w.b.e.a(com.facebook.ads.internal.w.b.e.this);
                    com.facebook.ads.internal.w.b.e.this.a.postDelayed((Runnable)this, 1000L);
                }
            }
        }, 1000L);
        return true;
    }
    
    public boolean b() {
        if (!this.c()) {
            return false;
        }
        this.d = false;
        return true;
    }
    
    public boolean c() {
        return this.d;
    }
    
    public boolean d() {
        return this.c <= 0;
    }
    
    public int e() {
        return this.c;
    }
    
    public interface a
    {
        void a();
        
        void a(final int p0);
    }
}
