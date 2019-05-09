// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

public enum j
{
    a(true, false), 
    b(true, true);
    
    private boolean c;
    private boolean d;
    
    private j(final boolean c, final boolean d) {
        this.c = c;
        this.d = d;
    }
    
    public boolean a() {
        return this.c;
    }
    
    public boolean b() {
        return this.d;
    }
    
    public String c() {
        return this.toString();
    }
}
