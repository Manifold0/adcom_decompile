// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import java.util.HashMap;
import java.util.Map;
import com.facebook.ads.internal.adapters.b.l;

public class b
{
    private final int a;
    private final int b;
    private final l c;
    
    b(final int a, final int b, final l c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public Map<String, String> a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("cardind", this.a + "");
        hashMap.put("cardcnt", this.b + "");
        return hashMap;
    }
    
    public int b() {
        return this.a;
    }
    
    public l c() {
        return this.c;
    }
}
