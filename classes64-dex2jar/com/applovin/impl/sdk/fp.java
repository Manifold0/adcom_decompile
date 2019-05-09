// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

class fp extends dx
{
    private final JSONObject a;
    private final JSONObject b;
    private final AppLovinAdLoadListener h;
    
    fp(final JSONObject a, final JSONObject b, final AppLovinAdLoadListener h, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderAppLovinAd", appLovinSdkImpl);
        this.a = a;
        this.b = b;
        this.h = h;
    }
    
    private fe a(final String s) {
        if ("main".equalsIgnoreCase(s)) {
            return fe.a;
        }
        return fe.b;
    }
    
    @Override
    public void run() {
        this.e.d(this.c, "Rendering ad...");
        final m m = new m(this.a, this.b, this.d);
        final boolean booleanValue = bu.a(this.a, "vs_cache_immediately", false, this.d);
        final boolean booleanValue2 = bu.a(this.a, "vs_load_immediately", true, this.d);
        final String a = bu.a(this.a, "vs_ad_cache_priority", "background", this.d);
        this.e.d(this.c, "Creating cache task...");
        final el el = new el(m, this.h, this.d);
        el.a(booleanValue2);
        if (m.b() && !booleanValue) {
            this.d.getTaskManager().a(el, this.a(a));
            return;
        }
        this.d.getTaskManager().a(el);
    }
}
