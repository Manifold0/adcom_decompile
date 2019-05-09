// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.f;

import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import java.io.Serializable;

public class c implements Serializable
{
    private static final long serialVersionUID = -3209129042070173126L;
    private final int a;
    @Nullable
    private final String b;
    private final String c;
    private final List<c> d;
    private c e;
    
    c(final int a, @Nullable final String b, final String c) {
        this.d = new ArrayList<c>();
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    c(final String s) {
        this(0, null, s);
    }
    
    public int a() {
        return this.a;
    }
    
    public void a(final c c) {
        c.e = this;
        this.d.add(c);
    }
    
    public String b() {
        return this.b;
    }
    
    @Nullable
    public String c() {
        return this.c;
    }
    
    public List<c> d() {
        return this.d;
    }
    
    public c e() {
        return this.e;
    }
}
