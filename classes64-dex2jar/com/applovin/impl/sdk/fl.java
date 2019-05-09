// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.impl.a.n;
import com.applovin.impl.a.h;
import org.json.JSONObject;
import com.applovin.impl.a.g;
import com.applovin.sdk.AppLovinAdLoadListener;

abstract class fl extends dx
{
    private final AppLovinAdLoadListener a;
    private final fm b;
    
    fl(final g g, final AppLovinAdLoadListener a, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessVastResponse", appLovinSdkImpl);
        if (g == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.a = a;
        this.b = (fm)g;
    }
    
    public static fl a(final gf gf, final g g, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        return new fo(gf, g, appLovinAdLoadListener, appLovinSdkImpl);
    }
    
    public static fl a(final JSONObject jsonObject, final JSONObject jsonObject2, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        return new fn(new fm(jsonObject, jsonObject2, appLovinSdkImpl), appLovinAdLoadListener, appLovinSdkImpl);
    }
    
    void a(final h h) {
        this.e.e(this.c, "Failed to process VAST response due to VAST error code " + h);
        n.a(this.b, this.a, h, -6, this.d);
    }
    
    protected void a(final gf gf) {
        final int a = this.b.a();
        this.e.d(this.c, "Finished parsing XML at depth " + a);
        this.b.a(gf);
        if (n.a(gf)) {
            final int intValue = this.d.get(ea.dq);
            if (a < intValue) {
                this.e.d(this.c, "VAST response is wrapper. Resolving...");
                this.d.getTaskManager().a(new fw(this.b, this.a, this.d));
                return;
            }
            this.e.e(this.c, "Reached beyond max wrapper depth of " + intValue);
            this.a(h.e);
        }
        else {
            if (n.b(gf)) {
                this.e.d(this.c, "VAST response is inline. Rendering ad...");
                this.d.getTaskManager().a(new fr(this.b, this.a, this.d));
                return;
            }
            this.e.e(this.c, "VAST response is an error");
            this.a(h.f);
        }
    }
}
