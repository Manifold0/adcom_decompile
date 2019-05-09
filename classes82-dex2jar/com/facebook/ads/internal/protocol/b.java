// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

public class b extends Exception
{
    private final AdErrorType a;
    private final String b;
    
    public b(final AdErrorType adErrorType, final String s) {
        this(adErrorType, s, null);
    }
    
    public b(final AdErrorType a, final String b, final Throwable t) {
        super(b, t);
        this.a = a;
        this.b = b;
    }
    
    public AdErrorType a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
}
