// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.x;

import java.util.HashMap;
import java.util.Map;

public class b
{
    private c a;
    private float b;
    private Map<String, String> c;
    
    public b(final c c) {
        this(c, 0.0f);
    }
    
    public b(final c c, final float n) {
        this(c, n, null);
    }
    
    public b(final c a, final float b, final Map<String, String> c) {
        this.a = a;
        this.b = b;
        if (c != null) {
            this.c = c;
            return;
        }
        this.c = new HashMap<String, String>();
    }
    
    public boolean a() {
        return this.a == com.facebook.ads.internal.x.c.b;
    }
    
    public int b() {
        return this.a.a();
    }
    
    public float c() {
        return this.b;
    }
    
    public Map<String, String> d() {
        return this.c;
    }
}
