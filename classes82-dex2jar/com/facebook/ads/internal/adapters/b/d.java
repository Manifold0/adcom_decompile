// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class d implements Serializable
{
    private static final long serialVersionUID = -268645651038092386L;
    private final String a;
    private final int b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final int g;
    private final int h;
    @Nullable
    private final n i;
    private String j;
    
    private d(final a a) {
        this.a = a.a;
        this.b = a.b;
        this.c = a.c;
        this.d = a.d;
        this.e = a.e;
        this.f = a.f;
        this.g = a.g;
        this.h = a.h;
        this.i = a.i;
    }
    
    public String a() {
        return this.a;
    }
    
    void a(final String j) {
        this.j = j;
    }
    
    public String b() {
        return this.j;
    }
    
    public int c() {
        return this.b;
    }
    
    public int d() {
        return this.c;
    }
    
    public boolean e() {
        return this.d;
    }
    
    public boolean f() {
        return this.e;
    }
    
    public String g() {
        return this.f;
    }
    
    public int h() {
        return this.g;
    }
    
    public int i() {
        return this.h;
    }
    
    @Nullable
    public n j() {
        return this.i;
    }
    
    static class a
    {
        private String a;
        private int b;
        private int c;
        private boolean d;
        private boolean e;
        private String f;
        private int g;
        private int h;
        private n i;
        
        a a(final int b) {
            this.b = b;
            return this;
        }
        
        a a(@Nullable final n i) {
            this.i = i;
            return this;
        }
        
        a a(final String a) {
            this.a = a;
            return this;
        }
        
        a a(final boolean d) {
            this.d = d;
            return this;
        }
        
        d a() {
            return new d(this, null);
        }
        
        a b(final int c) {
            this.c = c;
            return this;
        }
        
        a b(final String f) {
            this.f = f;
            return this;
        }
        
        a b(final boolean e) {
            this.e = e;
            return this;
        }
        
        a c(final int g) {
            this.g = g;
            return this;
        }
        
        a d(final int h) {
            this.h = h;
            return this;
        }
    }
}
