// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import java.util.Map;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.i;
import java.util.HashMap;
import java.io.File;

class k extends ad<Void>
{
    final j a;
    private final l k;
    private final ai l;
    
    k(final l k, final ai l, final j a, final File file) {
        super("GET", a.c, 2, file);
        this.j = 1;
        this.k = k;
        this.l = l;
        this.a = a;
    }
    
    @Override
    public ae a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("X-Chartboost-App", com.chartboost.sdk.i.k);
        hashMap.put("X-Chartboost-Client", CBUtility.b());
        hashMap.put("X-Chartboost-Reachability", Integer.toString(this.l.a()));
        return new ae(hashMap, null, null);
    }
    
    @Override
    public void a(final CBError cbError, final ag ag) {
        this.k.a(this, cbError, ag);
    }
    
    @Override
    public void a(final Void void1, final ag ag) {
        this.k.a(this, null, null);
    }
}
