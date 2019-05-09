// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.File;
import java.util.HashMap;
import com.chartboost.sdk.Libraries.h;
import java.util.Map;
import com.chartboost.sdk.Libraries.f;

public class al
{
    private final f a;
    private final Map<String, h.a> b;
    
    public al(final f a) {
        this.a = a;
        this.b = new HashMap<String, h.a>();
    }
    
    private boolean b(final String s) {
        return this.a.b(String.format("%s%s", s, ".png"));
    }
    
    public h.a a(final String s) {
        if (!this.b(s)) {
            if (this.b.containsKey(s)) {
                this.b.remove(s);
            }
            return null;
        }
        if (this.b.containsKey(s)) {
            return this.b.get(s);
        }
        final h.a a = new h.a(s, new File(this.a.d().d, String.format("%s%s", s, ".png")), this.a);
        this.b.put(s, a);
        return a;
    }
}
