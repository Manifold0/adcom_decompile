// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;

class ey extends fs<JSONObject>
{
    final /* synthetic */ ex a;
    
    ey(final ex a, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.a.b(n);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        if (n == 200) {
            bu.b(jsonObject, "fetch_ad_latency_millis", this.h.a(), this.d);
            bu.b(jsonObject, "fetch_ad_response_size", this.h.b(), this.d);
            this.a.b(jsonObject);
            return;
        }
        this.a.b(n);
    }
}
