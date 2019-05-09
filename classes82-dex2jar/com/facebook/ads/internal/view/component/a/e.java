// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.c.o;
import com.facebook.ads.internal.w.b.w;
import android.view.View;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.s.c;
import android.content.Context;

public class e
{
    private final Context a;
    private final c b;
    private final com.facebook.ads.internal.view.a.a c;
    private final k d;
    private final View e;
    private final com.facebook.ads.internal.x.a f;
    private final w g;
    private final int h;
    private final int i;
    @Nullable
    private final o j;
    @Nullable
    private final View k;
    
    private e(final a a) {
        this.a = a.a;
        this.b = a.b;
        this.c = a.c;
        this.d = a.d;
        this.e = a.e;
        this.f = a.f;
        this.g = a.g;
        this.h = a.h;
        this.i = a.i;
        this.j = a.j;
        this.k = a.k;
    }
    
    Context a() {
        return this.a;
    }
    
    c b() {
        return this.b;
    }
    
    com.facebook.ads.internal.view.a.a c() {
        return this.c;
    }
    
    View d() {
        return this.e;
    }
    
    com.facebook.ads.internal.x.a e() {
        return this.f;
    }
    
    w f() {
        return this.g;
    }
    
    k g() {
        return this.d;
    }
    
    o h() {
        return this.j;
    }
    
    View i() {
        return this.k;
    }
    
    int j() {
        return this.h;
    }
    
    int k() {
        return this.i;
    }
    
    public static class a
    {
        private final Context a;
        private final c b;
        private final com.facebook.ads.internal.view.a.a c;
        private final k d;
        private final View e;
        private final com.facebook.ads.internal.x.a f;
        private final w g;
        private int h;
        private int i;
        @Nullable
        private o j;
        @Nullable
        private View k;
        
        public a(final Context a, final c b, final com.facebook.ads.internal.view.a.a c, final k d, final View e, final com.facebook.ads.internal.x.a f, final w g) {
            this.h = 0;
            this.i = 1;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
        }
        
        public a a(final int h) {
            this.h = h;
            return this;
        }
        
        public a a(final View k) {
            this.k = k;
            return this;
        }
        
        public a a(final o j) {
            this.j = j;
            return this;
        }
        
        public e a() {
            return new e(this, null);
        }
        
        public a b(final int i) {
            this.i = i;
            return this;
        }
    }
}
