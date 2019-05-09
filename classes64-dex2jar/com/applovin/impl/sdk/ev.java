// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class ev extends fs<JSONObject>
{
    final /* synthetic */ eu a;
    
    ev(final eu a, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.e.e(this.a(), "Unable to fetch basic SDK settings: server returned " + n);
        this.d.a(false);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        ag.a(jsonObject, this.d);
        this.e.i(this.a(), "Executing initialize SDK...");
        this.d.getTaskManager().a(new fb(this.d));
        ag.b(jsonObject, this.d);
        this.e.i(this.a(), "Finished executing initialize SDK");
    }
}
