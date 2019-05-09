// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.d;

import java.io.Serializable;

public class c implements Serializable
{
    private a a;
    private a b;
    
    public c() {
        this(0.5, 0.5);
    }
    
    public c(final double n) {
        this(n, 0.5);
    }
    
    public c(final double n, final double n2) {
        this.a = new a(n);
        this.b = new a(n2);
        this.a();
    }
    
    void a() {
        this.a.a();
        this.b.a();
    }
    
    void a(final double n, final double n2) {
        this.a.a(n, n2);
    }
    
    void b() {
        this.a.b();
        this.b.b();
    }
    
    void b(final double n, final double n2) {
        this.b.a(n, n2);
    }
    
    public a c() {
        return this.a;
    }
    
    public a d() {
        return this.b;
    }
    
    public static class a implements Serializable
    {
        private double a;
        private double b;
        private double c;
        private double d;
        private double e;
        private double f;
        private double g;
        private int h;
        private double i;
        private double j;
        private double k;
        
        public a(final double e) {
            this.e = e;
        }
        
        public void a() {
            this.a = 0.0;
            this.c = 0.0;
            this.d = 0.0;
            this.f = 0.0;
            this.h = 0;
            this.i = 0.0;
            this.j = 1.0;
            this.k = 0.0;
        }
        
        public void a(final double n, final double c) {
            ++this.h;
            this.i += n;
            this.c = c;
            this.k += c * n;
            this.a = this.k / this.i;
            this.j = Math.min(this.j, c);
            this.f = Math.max(this.f, c);
            if (c >= this.e) {
                this.d += n;
                this.b += n;
                this.g = Math.max(this.g, this.b);
                return;
            }
            this.b = 0.0;
        }
        
        public void b() {
            this.b = 0.0;
        }
        
        public double c() {
            if (this.h == 0) {
                return 0.0;
            }
            return this.j;
        }
        
        public double d() {
            return this.a;
        }
        
        public double e() {
            return this.f;
        }
        
        public double f() {
            return this.i;
        }
        
        public double g() {
            return this.d;
        }
        
        public double h() {
            return this.g;
        }
    }
}
