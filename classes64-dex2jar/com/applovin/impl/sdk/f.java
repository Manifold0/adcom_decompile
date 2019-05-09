// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import java.util.LinkedHashMap;

class f extends LinkedHashMap<String, e>
{
    final /* synthetic */ c a;
    
    private f(final c a) {
        this.a = a;
    }
    
    @Override
    protected boolean removeEldestEntry(final Map.Entry<String, e> entry) {
        return this.size() > this.a.a.get(ea.dK);
    }
}
