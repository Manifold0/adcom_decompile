package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class ew extends ex {
    /* renamed from: a */
    private final List<String> f2486a;

    ew(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(C1287n.m3037a(m2828a((List) list), appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
        this.f2486a = Collections.unmodifiableList(list);
    }

    /* renamed from: a */
    private static String m2828a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return (String) list.get(0);
        }
        throw new IllegalArgumentException("No zone identifiers specified");
    }

    /* renamed from: a */
    void mo4166a(Map<String, String> map) {
        map.put("zone_ids", gd.m2953c(aa.m2191a(this.f2486a, this.f2486a.size())));
    }
}
