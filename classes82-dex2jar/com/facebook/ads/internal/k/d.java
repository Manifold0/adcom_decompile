// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.k;

import java.util.Map;

public class d
{
    private double a;
    private double b;
    private String c;
    private Map<String, String> d;
    
    public d(final double a, final String c, final Map<String, String> d) {
        this.a = a;
        this.c = c;
        this.d = d;
        this.b = System.currentTimeMillis() / 1000.0;
    }
    
    public String a() {
        return "debug";
    }
    
    public double b() {
        return this.b;
    }
    
    public double c() {
        return this.a;
    }
    
    public String d() {
        return this.c;
    }
    
    public Map<String, String> e() {
        return this.d;
    }
}
