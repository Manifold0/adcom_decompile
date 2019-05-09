// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class m implements Serializable
{
    private static final long serialVersionUID = 351643298236575728L;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    
    private m(final a a) {
        this.a = a.a;
        this.b = a.b;
        this.c = a.c;
        this.d = a.d;
    }
    
    public String a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public String c() {
        return this.c;
    }
    
    public String d() {
        return this.d;
    }
    
    public static class a
    {
        private String a;
        private String b;
        private String c;
        private String d;
        
        a a(final String a) {
            this.a = a;
            return this;
        }
        
        m a() {
            return new m(this, null);
        }
        
        a b(final String b) {
            this.b = b;
            return this;
        }
        
        a c(final String c) {
            this.c = c;
            return this;
        }
        
        a d(final String d) {
            this.d = d;
            return this;
        }
    }
}
