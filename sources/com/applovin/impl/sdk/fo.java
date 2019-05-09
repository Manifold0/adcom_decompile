package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1234g;
import com.applovin.sdk.AppLovinAdLoadListener;

final class fo extends fl {
    /* renamed from: a */
    private final gf f2526a;

    fo(gf gfVar, C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(c1234g, appLovinAdLoadListener, appLovinSdkImpl);
        if (gfVar == null) {
            throw new IllegalArgumentException("No response specified.");
        } else if (c1234g == null) {
            throw new IllegalArgumentException("No context specified.");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified.");
        } else {
            this.f2526a = gfVar;
        }
    }

    public void run() {
        this.e.mo4172d(this.c, "Processing VAST Wrapper response...");
        m2872a(this.f2526a);
    }
}
