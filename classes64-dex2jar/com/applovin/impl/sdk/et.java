// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class et extends fs<JSONObject>
{
    final /* synthetic */ es a;
    
    et(final es a, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.a.h.onPostbackFailure(this.a.a, n);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        this.a.h.onPostbackSuccess(this.a.a);
    }
}
