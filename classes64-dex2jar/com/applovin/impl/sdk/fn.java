// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.impl.a.h;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.a.g;
import org.json.JSONObject;

final class fn extends fl
{
    private final JSONObject a;
    
    fn(final g g, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super(g, appLovinAdLoadListener, appLovinSdkImpl);
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified.");
        }
        this.a = g.c();
    }
    
    @Override
    public void run() {
        this.e.d(this.c, "Processing SDK JSON response...");
        final String a = bu.a(this.a, "xml", (String)null, this.d);
        if (AppLovinSdkUtils.isValidString(a)) {
            if (a.length() < this.d.get(ea.dt)) {
                try {
                    this.a(gg.a(a, this.d));
                    return;
                }
                catch (Throwable t) {
                    this.e.e(this.c, "Unable to parse VAST response", t);
                    this.a(h.b);
                    return;
                }
            }
            this.e.e(this.c, "VAST response is over max length");
            this.a(h.b);
            return;
        }
        this.e.e(this.c, "No VAST response received.");
        this.a(h.f);
    }
}
