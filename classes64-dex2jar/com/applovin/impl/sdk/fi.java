// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;

public class fi extends dx
{
    private final JSONObject a;
    private final JSONObject b;
    
    fi(final JSONObject a, final JSONObject b, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No ad object specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No response specified");
        }
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        try {
            this.d.getMediationService().a(new ck(this.a, this.b, this.d));
        }
        catch (Throwable t) {
            this.e.e(this.c, "Unable to prepare adapter ad", t);
        }
    }
}
