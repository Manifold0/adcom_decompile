// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.k;

import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.w.b.o;

public class a extends d
{
    public a(final String s, final String s2) {
        final double b = o.b();
        final String c = o.c();
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("key", s);
        hashMap.put("value", s2);
        super(b, c, hashMap);
    }
    
    @Override
    public String a() {
        return "client_response";
    }
}
