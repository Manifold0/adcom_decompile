// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class j implements Serializable
{
    private static final long serialVersionUID = -2102939945352398575L;
    private final byte[] a;
    private final String b;
    private final List<String> c;
    private String d;
    
    j(final byte[] a, final String b, final List<String> c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public String a() {
        return this.d;
    }
    
    void a(final String d) {
        this.d = d;
    }
    
    public byte[] b() {
        return this.a;
    }
    
    public String c() {
        return this.b;
    }
    
    public List<String> d() {
        return Collections.unmodifiableList((List<? extends String>)this.c);
    }
}
