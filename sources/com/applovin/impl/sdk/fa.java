package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Map;

class fa extends ex {
    /* renamed from: a */
    private final String f2491a;

    fa(String str, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(C1287n.m3037a("adtoken_zone", appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
        this.f2491a = str;
    }

    /* renamed from: a */
    void mo4166a(Map<String, String> map) {
        map.put("adtoken", gd.m2953c(this.f2491a));
    }
}
