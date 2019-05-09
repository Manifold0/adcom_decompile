// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import com.applovin.sdk.AppLovinAdLoadListener;

class fa extends ex
{
    private final String a;
    
    fa(final String a, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super(n.a("adtoken_zone", appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
        this.a = a;
    }
    
    @Override
    void a(final Map<String, String> map) {
        map.put("adtoken", gd.c(this.a));
    }
}
