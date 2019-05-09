// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class e implements Serializable
{
    private static final long serialVersionUID = 5306126965868117466L;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final a g;
    
    private e(final b b) {
        this.a = b.a;
        this.b = b.b;
        this.c = b.c;
        this.d = b.d;
        this.e = b.e;
        this.f = b.f;
        this.g = b.g;
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
    
    public String e() {
        return this.e;
    }
    
    public a f() {
        return this.g;
    }
    
    public String g() {
        return this.f;
    }
    
    public enum a
    {
        a("contextual_app"), 
        b("page_post");
        
        private final String c;
        
        private a(final String c) {
            this.c = c;
        }
        
        public static a a(final String s) {
            switch (s) {
                default: {
                    return a.b;
                }
                case "contextual_app": {
                    return a.a;
                }
            }
        }
    }
    
    public static class b
    {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private a g;
        
        b a(final String a) {
            this.a = a;
            return this;
        }
        
        e a() {
            return new e(this, null);
        }
        
        b b(final String b) {
            this.b = b;
            return this;
        }
        
        b c(final String c) {
            this.c = c;
            return this;
        }
        
        b d(final String d) {
            this.d = d;
            return this;
        }
        
        b e(final String e) {
            this.e = e;
            return this;
        }
        
        b f(final String f) {
            this.f = f;
            return this;
        }
        
        b g(final String s) {
            this.g = com.facebook.ads.internal.adapters.b.e.a.a(s);
            return this;
        }
    }
}
