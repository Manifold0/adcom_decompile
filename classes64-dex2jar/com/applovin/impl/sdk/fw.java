// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.impl.a.n;
import com.applovin.impl.a.h;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.a.g;

class fw extends dx
{
    private g a;
    private final AppLovinAdLoadListener b;
    
    fw(final g a, final AppLovinAdLoadListener b, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskResolveVastWrapper", appLovinSdkImpl);
        this.b = b;
        this.a = a;
    }
    
    private void a(final int n) {
        this.e.e(this.c, "Failed to resolve VAST wrapper due to error code " + n);
        if (n == -103) {
            gd.a(this.b, this.a.f(), n, this.d);
            return;
        }
        h h;
        if (n == -102) {
            h = com.applovin.impl.a.h.d;
        }
        else {
            h = com.applovin.impl.a.h.c;
        }
        n.a(this.a, this.b, h, n, this.d);
    }
    
    @Override
    public void run() {
        final String a = n.a(this.a);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.e.d(this.c, "Resolving VAST ad with depth " + this.a.a() + " at " + a);
            try {
                final fx fx = new fx(this, "GET", gf.a, "RepeatResolveVastWrapper", this.d);
                fx.a(a);
                fx.b(this.d.get(ea.ds));
                fx.c(this.d.get(ea.dr));
                this.d.getTaskManager().a(fx);
                return;
            }
            catch (Throwable t) {
                this.e.e(this.c, "Unable to resolve VAST wrapper", t);
                this.a(-1);
                return;
            }
        }
        this.e.e(this.c, "Resolving VAST failed. Could not find resolution URL");
        this.a(-1);
    }
}
