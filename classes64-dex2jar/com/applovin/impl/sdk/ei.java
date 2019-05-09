// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class ei extends fs<JSONObject>
{
    final /* synthetic */ eh a;
    
    ei(final eh a, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        ag.a(n, this.d);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        this.a.a(jsonObject);
    }
}
