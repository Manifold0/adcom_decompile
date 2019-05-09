// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

public class fc extends dx
{
    private final JSONObject a;
    private final JSONObject b;
    private final AppLovinAdLoadListener h;
    
    fc(final JSONObject a, final JSONObject b, final AppLovinSdkImpl appLovinSdkImpl, final AppLovinAdLoadListener h) {
        super("TaskLoadAdapterAd", appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No ad object specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No response specified");
        }
        this.a = a;
        this.b = b;
        this.h = h;
    }
    
    @Override
    public void run() {
        try {
            this.d.getMediationService().a(new ck(this.a, this.b, this.d), this.h);
        }
        catch (Throwable t) {
            this.e.e(this.c, "Unable to process adapter ad", t);
            if (this.h != null) {
                this.h.failedToReceiveAd(-5001);
            }
        }
    }
}
