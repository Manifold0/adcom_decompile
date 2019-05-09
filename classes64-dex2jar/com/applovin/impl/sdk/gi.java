// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;

class gi extends gf
{
    gi(final String s, final Map<String, String> map, final gf gf) {
        super(s, map, gf);
    }
    
    void a(final gf gf) {
        if (gf == null) {
            throw new IllegalArgumentException("None specified.");
        }
        this.c.add(gf);
    }
    
    void d(final String b) {
        this.b = b;
    }
}
