// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.io.Serializable;

public class i implements Serializable
{
    private static final long serialVersionUID = -4041915335826065133L;
    private final String a;
    private final String b;
    
    i(final String s, final String s2) {
        this.a = a(s);
        this.b = a(s2);
    }
    
    private static String a(final String s) {
        String s2 = s;
        if ("null".equalsIgnoreCase(s)) {
            s2 = "";
        }
        return s2;
    }
    
    public String a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
}
