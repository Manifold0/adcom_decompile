// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.k;

import java.util.HashMap;
import java.util.Map;

public class b
{
    private final String a;
    private final Map<String, String> b;
    private final String c;
    
    public b(final String s, final Map<String, String> map) {
        this(s, map, false);
    }
    
    public b(String s, final Map<String, String> b, final boolean b2) {
        this.a = s;
        this.b = b;
        if (b2) {
            s = "1";
        }
        else {
            s = "0";
        }
        this.c = s;
    }
    
    public Map<String, String> a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("stacktrace", this.a);
        hashMap.put("caught_exception", this.c);
        hashMap.putAll((Map<?, ?>)this.b);
        return hashMap;
    }
}
