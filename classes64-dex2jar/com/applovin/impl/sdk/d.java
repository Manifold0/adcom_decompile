// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

class d extends fs<JSONObject>
{
    final /* synthetic */ c a;
    
    d(final c a, final String s, final JSONObject jsonObject, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, jsonObject, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.e.e("AdEventStatsManager", "Failed to submitted ad stats: " + n);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final int n) {
        this.e.d("AdEventStatsManager", "Ad stats submitted: " + n);
    }
}
