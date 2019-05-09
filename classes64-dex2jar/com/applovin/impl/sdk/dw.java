// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class dw extends fs<JSONObject>
{
    final /* synthetic */ af a;
    final /* synthetic */ dv b;
    
    dw(final dv b, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl, final af a) {
        this.b = b;
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.a.a(n);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        this.a.a(jsonObject, n);
    }
}
