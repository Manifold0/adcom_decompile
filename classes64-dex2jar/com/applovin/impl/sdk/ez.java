// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

class ez extends ex
{
    private final int a;
    private final AppLovinNativeAdLoadListener b;
    
    public ez(final String s, final int a, final AppLovinSdkImpl appLovinSdkImpl, final AppLovinNativeAdLoadListener b) {
        super(n.b(s, appLovinSdkImpl), null, appLovinSdkImpl);
        this.a = a;
        this.b = b;
    }
    
    @Override
    protected dx a(final JSONObject jsonObject) {
        return new fq(jsonObject, this.d, this.b);
    }
    
    @Override
    protected void a(final int n) {
        if (this.b != null) {
            this.b.onNativeAdsFailedToLoad(n);
        }
    }
    
    @Override
    protected String b(final Map<String, String> map) {
        return ag.b("nad", map, this.d);
    }
    
    @Override
    protected String c(final Map<String, String> map) {
        return ag.d("nad", map, this.d);
    }
    
    protected void e(final Map<String, String> map) {
        map.put("slot_count", Integer.toString(this.a));
    }
}
