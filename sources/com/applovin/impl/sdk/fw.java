package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1234g;
import com.applovin.impl.p016a.C1235h;
import com.applovin.impl.p016a.C1241n;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;

class fw extends dx {
    /* renamed from: a */
    private C1234g f2539a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f2540b;

    fw(C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskResolveVastWrapper", appLovinSdkImpl);
        this.f2540b = appLovinAdLoadListener;
        this.f2539a = c1234g;
    }

    /* renamed from: a */
    private void m2889a(int i) {
        this.e.mo4173e(this.c, "Failed to resolve VAST wrapper due to error code " + i);
        if (i == AppLovinErrorCodes.NO_NETWORK) {
            gd.m2943a(this.f2540b, this.f2539a.m1885f(), i, this.d);
        } else {
            C1241n.m1910a(this.f2539a, this.f2540b, i == -102 ? C1235h.TIMED_OUT : C1235h.GENERAL_WRAPPER_ERROR, i, this.d);
        }
    }

    public void run() {
        String a = C1241n.m1906a(this.f2539a);
        if (AppLovinSdkUtils.isValidString(a)) {
            this.e.mo4172d(this.c, "Resolving VAST ad with depth " + this.f2539a.m1880a() + " at " + a);
            try {
                dx fxVar = new fx(this, "GET", gf.f2570a, "RepeatResolveVastWrapper", this.d);
                fxVar.m2497a(a);
                fxVar.m2500b(((Integer) this.d.get(ea.ds)).intValue());
                fxVar.m2503c(((Integer) this.d.get(ea.dr)).intValue());
                this.d.getTaskManager().m2854a(fxVar);
                return;
            } catch (Throwable th) {
                this.e.mo4174e(this.c, "Unable to resolve VAST wrapper", th);
                m2889a(-1);
                return;
            }
        }
        this.e.mo4173e(this.c, "Resolving VAST failed. Could not find resolution URL");
        m2889a(-1);
    }
}
