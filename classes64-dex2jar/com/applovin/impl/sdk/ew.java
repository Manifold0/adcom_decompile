// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Collection;
import java.util.Map;
import java.util.Collections;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.List;

class ew extends ex
{
    private final List<String> a;
    
    ew(final List<String> list, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super(n.a(a(list), appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
        this.a = Collections.unmodifiableList((List<? extends String>)list);
    }
    
    private static String a(final List<String> list) {
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        throw new IllegalArgumentException("No zone identifiers specified");
    }
    
    @Override
    void a(final Map<String, String> map) {
        map.put("zone_ids", gd.c(aa.a(this.a, this.a.size())));
    }
}
