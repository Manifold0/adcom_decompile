// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.a.g;

final class fo extends fl
{
    private final gf a;
    
    fo(final gf a, final g g, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super(g, appLovinAdLoadListener, appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No response specified.");
        }
        if (g == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified.");
        }
        this.a = a;
    }
    
    @Override
    public void run() {
        this.e.d(this.c, "Processing VAST Wrapper response...");
        this.a(this.a);
    }
}
