// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;
import com.applovin.impl.a.g;

final class fm extends g
{
    fm(final JSONObject jsonObject, final JSONObject jsonObject2, final AppLovinSdkImpl appLovinSdkImpl) {
        super(jsonObject, jsonObject2, appLovinSdkImpl);
    }
    
    void a(final gf gf) {
        if (gf == null) {
            throw new IllegalArgumentException("No aggregated vast response specified");
        }
        this.a.add(gf);
    }
}
