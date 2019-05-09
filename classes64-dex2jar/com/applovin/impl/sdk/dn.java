// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class dn
{
    private static dn d;
    private final Map<an, String> a;
    private final Map<an, Map<String, String>> b;
    private final Object c;
    
    private dn() {
        this.a = new HashMap<an, String>(1);
        this.b = new HashMap<an, Map<String, String>>(1);
        this.c = new Object();
    }
    
    public static dn a() {
        synchronized (dn.class) {
            if (dn.d == null) {
                dn.d = new dn();
            }
            return dn.d;
        }
    }
    
    public Map<String, String> a(final an an) {
        synchronized (this.c) {
            return this.b.remove(an);
        }
    }
    
    public void a(final an an, final String s) {
        synchronized (this.c) {
            this.a.put(an, s);
        }
    }
    
    public void a(final an an, final Map<String, String> map) {
        synchronized (this.c) {
            this.b.put(an, map);
        }
    }
    
    public String b(final an an) {
        synchronized (this.c) {
            return this.a.remove(an);
        }
    }
}
