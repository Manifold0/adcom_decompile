// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

public abstract class l
{
    protected String a;
    protected j b;
    protected String c;
    protected byte[] d;
    
    public l(String a, final p p2) {
        this.a = "";
        if (a != null) {
            this.a = a;
        }
        if (p2 != null) {
            a = p2.a();
            this.a = this.a + "?" + a;
        }
    }
    
    public String a() {
        return this.a;
    }
    
    public j b() {
        return this.b;
    }
    
    public String c() {
        return this.c;
    }
    
    public byte[] d() {
        return this.d;
    }
}
